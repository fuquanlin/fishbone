package cn.fql.lock.registry.redis;

import cn.fql.lock.registry.DefaultLockRegistry;
import cn.fql.lock.registry.LockRegistry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by fuquanlin on 06/12/2017.
 */
public class RedisLockRegistry implements LockRegistry {

    private static final Log logger = LogFactory.getLog(LockRegistry.class);

    private static final byte[] hostName;

    private static final long DEFAULT_EXPIRE_AFTER = 60000;

    private final String registryKey;

    private final RedisTemplate<String, RedisLock> redisTemplate;

    private final ThreadLocal<Set<RedisLock>> weakThreadLocks = new ThreadLocal<Set<RedisLock>>();

    private final ThreadLocal<List<RedisLock>> hardThreadLocks = new ThreadLocal<List<RedisLock>>();

    private final long expireAfter;

    private final LockRegistry localRegistry;

    private final LockSerializer lockSerializer = new LockSerializer();

    private boolean useWeakReferences = false;

    static {
        String host;
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            host = "unknownHost";
        }
        hostName = host.getBytes();
    }

    /**
     * Constructs a lock registry with the default (60 second) lock expiration
     * and a default local {@link DefaultLockRegistry}.
     *
     * @param connectionFactory
     *            The connection factory.
     * @param registryKey
     *            The key prefix for locks.
     */
    public RedisLockRegistry(RedisConnectionFactory connectionFactory, String registryKey) {
        this(connectionFactory, registryKey, DEFAULT_EXPIRE_AFTER);
    }

    /**
     * Constructs a lock registry with the supplied lock expiration and a
     * default local {@link DefaultLockRegistry}.
     *
     * @param connectionFactory
     *            The connection factory.
     * @param registryKey
     *            The key prefix for locks.
     * @param expireAfter
     *            The expiration in milliseconds.
     */
    public RedisLockRegistry(RedisConnectionFactory connectionFactory, String registryKey, long expireAfter) {
        this(connectionFactory, registryKey, expireAfter, new DefaultLockRegistry());
    }

    /**
     * Constructs a lock registry with the supplied lock expiration and a custom
     * local {@link LockRegistry}.
     *
     * @param connectionFactory
     *            The connection factory.
     * @param registryKey
     *            The key prefix for locks.
     * @param expireAfter
     *            The expiration in milliseconds.
     * @param localRegistry
     *            The local registry used to reduce wait time,
     *            {@link DefaultLockRegistry} is used by default.
     */
    public RedisLockRegistry(RedisConnectionFactory connectionFactory, String registryKey, long expireAfter,
                             LockRegistry localRegistry) {
        Assert.notNull(connectionFactory, "'connectionFactory' cannot be null");
        Assert.notNull(registryKey, "'registryKey' cannot be null");
        Assert.notNull(localRegistry, "'localRegistry' cannot be null");
        this.redisTemplate = new RedisTemplate<String, RedisLockRegistry.RedisLock>();
        this.redisTemplate.setConnectionFactory(connectionFactory);
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new LockSerializer());
        this.redisTemplate.afterPropertiesSet();
        this.registryKey = registryKey;
        this.expireAfter = expireAfter;
        this.localRegistry = localRegistry;
    }

    /**
     * Change the state of thread local weak references storage for unlocked
     * locks. Thread local weak references are used for lock obtaining
     * optimization - thread will get same {@link RedisLock} object for certain
     * key before actual locking and after unlocking (if variable still exists).
     * <p>
     * While is switched off (by default) every {@link RedisLockRegistry#obtain}
     * call will provide different {@link RedisLock} objects for same unlocked
     * key.
     *
     * @param useWeakReferences
     *            set to true for switch thread local weak references storage
     *            on, false by default
     * @since 4.0.7
     */
    public void setUseWeakReferences(boolean useWeakReferences) {
        this.useWeakReferences = useWeakReferences;
    }

    /**
     * Weak referenced locks, lock is kept here when actual lock is NOT gained.
     * Used for obtaining same lock object within same thread and key. To avoid
     * memory leaks lock objects without actual lock are kept as weak
     * references. After gaining the actual lock, lock object moves from weak
     * reference to hard reference and vise a versa.
     */
    private Collection<RedisLock> getWeakThreadLocks() {
        Set<RedisLock> locks = this.weakThreadLocks.get();
        if (locks == null) {
            locks = Collections.newSetFromMap(new WeakHashMap<RedisLock, Boolean>());
            this.weakThreadLocks.set(locks);
        }
        return locks;
    }

    /**
     * Hard referenced locks, lock is kept here when actual lock is gained.
     */
    private Collection<RedisLock> getHardThreadLocks() {
        List<RedisLock> locks = this.hardThreadLocks.get();
        if (locks == null) {
            locks = new LinkedList<RedisLock>();
            this.hardThreadLocks.set(locks);
        }
        return locks;
    }

    private RedisLock findLock(Collection<RedisLock> locks, Object key) {
        if (locks != null) {
            for (RedisLock lock : locks) {
                if (lock.getLockKey().equals(key)) {
                    return lock;
                }
            }
        }
        return null;
    }

    private void toHardThreadStorage(RedisLock lock) {
        if (this.weakThreadLocks.get() != null) {
            this.weakThreadLocks.get().remove(lock);
        }

        getHardThreadLocks().add(lock);

        // clean up
        if (this.weakThreadLocks.get() != null && this.weakThreadLocks.get().isEmpty()) {
            this.weakThreadLocks.remove();
        }
    }

    private void toWeakThreadStorage(RedisLock lock) {
        // to avoid collection creation on existence check use direct fields
        if (this.hardThreadLocks.get() != null) {
            getHardThreadLocks().remove(lock);
        }

        if (this.useWeakReferences) {
            getWeakThreadLocks().add(lock);
        }

        // clean up
        if (this.hardThreadLocks.get() != null && this.hardThreadLocks.get().isEmpty()) {
            this.hardThreadLocks.remove();
        }
    }

    @Override
    public Lock obtain(Object lockKey) {
        Assert.isInstanceOf(String.class, lockKey);

        // try to find the lock within hard references
        RedisLock lock = findLock(this.hardThreadLocks.get(), lockKey);

		/*
		 * If the lock is locked, check that it matches what's in the store. If
		 * it doesn't, the lock must have expired.
		 */
        if (lock != null && lock.thread != null) {
            RedisLock lockInStore = this.redisTemplate.boundValueOps(this.registryKey + ":" + lockKey).get();
            if (lockInStore == null || !lock.equals(lockInStore)) {
                getHardThreadLocks().remove(lock);
                lock = null;
            }
        }

        if (lock == null) {
            // try to find the lock within weak references
            lock = findLock(this.weakThreadLocks.get(), lockKey);

            if (lock == null) {
                lock = new RedisLock((String) lockKey);

                if (this.useWeakReferences) {
                    getWeakThreadLocks().add(lock);
                }
            }
        }

        return lock;
    }

    public Collection<Lock> listLocks() {
        return this.redisTemplate.execute(new RedisCallback<Collection<Lock>>() {

            @Override
            public Collection<Lock> doInRedis(RedisConnection connection) throws DataAccessException {
                Set<byte[]> keys = connection.keys((registryKey + ":*").getBytes());
                ArrayList<Lock> list = new ArrayList<Lock>(keys.size());
                if (keys.size() > 0) {
                    List<byte[]> locks = connection.mGet(keys.toArray(new byte[keys.size()][]));
                    for (byte[] lock : locks) {
                        list.add(lockSerializer.deserialize(lock));
                    }
                }
                return list;
            }
        });
    }

    private class RedisLock implements Lock {

        private final String lockKey;

        private long lockedAt;

        private Thread thread;

        private String threadName;

        private byte[] lockHost;

        private int reLock;

        private RedisLock(String lockKey) {
            this.lockKey = lockKey;
            this.lockHost = RedisLockRegistry.hostName;
        }

        private String getLockKey() {
            return lockKey;
        }

        @Override
        public void lock() {
            Lock localLock = RedisLockRegistry.this.localRegistry.obtain(lockKey);
            localLock.lock();
            try {
                while (true) {
                    try {
                        while (!this.obtainLock()) {
                            Thread.sleep(100);
                        }
                        break;
                    } catch (InterruptedException e) {
						/*
						 * This method must be uninterruptible so catch and
						 * ignore interrupts and only break out of the while
						 * loop when we get the lock.
						 */
                    }
                }
            } catch (Exception e) {
                localLock.unlock();
                throw new RuntimeException(e);
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            Lock localLock = RedisLockRegistry.this.localRegistry.obtain(lockKey);
            localLock.lockInterruptibly();
            try {
                while (!this.obtainLock()) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException ie) {
                localLock.unlock();
                throw ie;
            } catch (Exception e) {
                localLock.unlock();
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean tryLock() {
            Lock localLock = RedisLockRegistry.this.localRegistry.obtain(lockKey);
            try {
                if (!localLock.tryLock()) {
                    return false;
                }
                boolean obtainedLock = this.obtainLock();
                if (!obtainedLock) {
                    localLock.unlock();
                }
                return obtainedLock;
            } catch (Exception e) {
                localLock.unlock();
                throw new RuntimeException(e);
            }
        }

        private boolean obtainLock() {
            Thread currentThread = Thread.currentThread();
            if (currentThread.equals(this.thread)) {
                this.reLock++;
                return true;
            }

            toHardThreadStorage(this);

			/*
			 * Set these now so they will be persisted if successful.
			 */
            this.lockedAt = System.currentTimeMillis();
            this.threadName = currentThread.getName();

            Boolean success = false;
            try {
                success = RedisLockRegistry.this.redisTemplate.execute(new SessionCallback<Boolean>() {

                    @SuppressWarnings({ "unchecked", "rawtypes" })
                    @Override
                    public Boolean execute(RedisOperations ops) throws DataAccessException {
                        String key = constructLockKey();

                        ops.watch(key); // monitor key

                        if (ops.opsForValue().get(key) != null) {
                            ops.unwatch(); // key already exists, stop
                            // monitoring
                            return false;
                        }

                        ops.multi(); // transaction start

                        // set the value and expire
                        ops.opsForValue().set(key, RedisLock.this, RedisLockRegistry.this.expireAfter,
                                TimeUnit.MILLISECONDS);

                        // exec will contain all operations result or null - if
                        // execution has been aborted due to 'watch'
                        return ops.exec() != null;
                    }

                });

            } finally {

                if (!success) {
                    this.lockedAt = 0;
                    this.threadName = null;
                    toWeakThreadStorage(this);
                } else {
                    this.thread = currentThread;
                    if (logger.isDebugEnabled()) {
                        logger.debug("New lock; " + this.toString());
                    }
                }

            }

            return success;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            Lock localLock = RedisLockRegistry.this.localRegistry.obtain(lockKey);
            if (!localLock.tryLock(time, unit)) {
                return false;
            }
            try {
                long expire = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(time, unit);
                boolean acquired = false;
                while (!(acquired = this.obtainLock()) && System.currentTimeMillis() < expire) {
                    Thread.sleep(100);
                }
                if (!acquired) {
                    localLock.unlock();
                }
                return acquired;
            } catch (Exception e) {
                localLock.unlock();
                throw new RuntimeException(e);
            }
        }

        @Override
        public void unlock() {
            if (!Thread.currentThread().equals(this.thread)) {
                if (this.thread == null) {
                    throw new IllegalStateException("Lock is not locked; " + this.toString());
                }
                throw new IllegalStateException("Lock is owned by " + this.thread.getName() + "; " + this.toString());
            }

            try {
                if (this.reLock-- <= 0) {
                    try {
                        this.assertLockInRedisIsUnchanged();
                        RedisLockRegistry.this.redisTemplate.delete(constructLockKey());
                        if (logger.isDebugEnabled()) {
                            logger.debug("Released lock; " + this.toString());
                        }
                    } finally {
                        this.thread = null;
                        this.reLock = 0;
                        toWeakThreadStorage(this);
                    }
                }
            } finally {
                Lock localLock = RedisLockRegistry.this.localRegistry.obtain(lockKey);
                localLock.unlock();
            }
        }

        private void assertLockInRedisIsUnchanged() {
            RedisLock lockInStore = RedisLockRegistry.this.redisTemplate.boundValueOps(constructLockKey()).get();
            if (lockInStore == null || !this.equals(lockInStore)) {
                throw new IllegalStateException("Lock was released due to expiration; " + this.toString()
                        + (lockInStore == null ? "" : "; lock in store: " + lockInStore.toString()));
            }
        }

        private String constructLockKey() {
            return RedisLockRegistry.this.registryKey + ":" + this.lockKey;
        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException("Conditions are not supported");
        }

        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd@HH:mm:ss.SSS");
            return "RedisLock [lockKey=" + constructLockKey() + ",lockedAt="
                    + dateFormat.format(new Date(this.lockedAt)) + ", thread=" + this.threadName + ", lockHost="
                    + new String(this.lockHost) + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + Arrays.hashCode(lockHost);
            result = prime * result + ((lockKey == null) ? 0 : lockKey.hashCode());
            result = prime * result + (int) (lockedAt ^ (lockedAt >>> 32));
            result = prime * result + ((threadName == null) ? 0 : threadName.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            RedisLock other = (RedisLock) obj;
            if (!getOuterType().equals(other.getOuterType())) {
                return false;
            }
            if (!Arrays.equals(lockHost, other.lockHost)) {
                return false;
            }
            if (!lockKey.equals(other.lockKey)) {
                return false;
            }
            if (lockedAt != other.lockedAt) {
                return false;
            }
            if (threadName == null) {
                if (other.threadName != null) {
                    return false;
                }
            } else if (!threadName.equals(other.threadName)) {
                return false;
            }
            return true;
        }

        private RedisLockRegistry getOuterType() {
            return RedisLockRegistry.this;
        }

    }

    private class LockSerializer implements RedisSerializer<RedisLock> {

        @Override
        public byte[] serialize(RedisLock t) throws SerializationException {
            int hostLength = t.lockHost.length;
            int keyLength = t.lockKey.length();
            int threadNameLength = t.threadName.length();
            byte[] value = new byte[1 + hostLength + 1 + keyLength + 1 + threadNameLength + 8];
            ByteBuffer buff = ByteBuffer.wrap(value);
            buff.put((byte) hostLength).put(t.lockHost).put((byte) keyLength).put(t.lockKey.getBytes())
                    .put((byte) threadNameLength).put(t.threadName.getBytes()).putLong(t.lockedAt);
            return value;
        }

        @Override
        public RedisLock deserialize(byte[] bytes) throws SerializationException {
            if (bytes == null) {
                return null;
            }
            ByteBuffer buff = ByteBuffer.wrap(bytes);
            byte[] host = new byte[buff.get()];
            buff.get(host);
            byte[] lockKey = new byte[buff.get()];
            buff.get(lockKey);
            byte[] threadName = new byte[buff.get()];
            buff.get(threadName);
            long lockedAt = buff.getLong();
            RedisLock lock = new RedisLock(new String(lockKey));
            lock.lockedAt = lockedAt;
            lock.lockHost = host;
            lock.threadName = new String(threadName);
            return lock;
        }

    }


}
