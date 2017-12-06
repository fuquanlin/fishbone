package cn.fql.lock.service.impl;

import cn.fql.lock.LockCallBack;
import cn.fql.lock.registry.LockRegistry;
import cn.fql.lock.service.LockService;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;

/**
 * Created by fuquanlin on 06/12/2017.
 */
public class LockServiceImpl implements LockService {

    private static ThreadPoolExecutor executors;

    private LockRegistry lockRegistry;

    public LockServiceImpl(LockRegistry lockRegistry,LockThreadPoolFactory lockThreadPoolFactory) {
        this.lockRegistry = lockRegistry;
        executors = lockThreadPoolFactory.createThreadPoolExecutor();
    }

    @Override
    public Lock obtainLock(String lockKey) throws Exception {
        return lockRegistry.obtain(lockKey);
    }

    @Override
    public <Result> void lockProcess(LockCallBack<Result> callBack, String lockKey, long lockTimeSeconds) throws Exception {
        getFuture(callBack,lockKey,lockTimeSeconds);
    }

    @Override
    public <Result> Result lockProcessWithResult(LockCallBack<Result> callBack, String lockKey, long lockTimeSeconds) throws Exception {
        Future<Result> future = getFuture(callBack, lockKey, lockTimeSeconds);
        Result result = future.get();
        return result;
    }

    private <Result> Future<Result> getFuture(LockCallBack<Result> callBack, String lockKey, long lockTimeSeconds) {
        LockContext lockContext = new LockContext(callBack, lockKey, lockTimeSeconds);
        return lockContext.execute();
    }

    private class LockContext<Result> {
        // 记录trylock是否成功
        public boolean tryLockedSucc;
        // 当前锁
        public Lock lock;

        public boolean unLockImmediately;

        public LockFutureCallableAdapter<Result> lockCallable;

        public LockContext(LockCallBack<Result> callBack, String lockKey, long lockTimeSeconds) {
            this.lock = lockRegistry.obtain(lockKey);
            this.unLockImmediately = lockTimeSeconds <= 0;
            Callable<Result> callbale = () -> {
                return lockProcess(callBack, lock, unLockImmediately);
            };
            lockCallable = new LockFutureCallableAdapter(callbale, this.lock, lockTimeSeconds, this);
        }

        public Future<Result> execute() {
            return (Future<Result>) executors.submit(lockCallable);
        }

        private Result lockProcess(LockCallBack<Result> callBack, Lock currentLock, boolean unLockImmediately) {
            this.tryLockedSucc = currentLock.tryLock();
            if (this.tryLockedSucc == false) {
                // 锁争抢失败，直接返回null
                return null;
            }
            try {
                return callBack.process();
            } finally {
                if (unLockImmediately) {
                    currentLock.unlock();
                }
            }
        }
    }


    class LockFutureCallableAdapter<Result> implements Callable<Result> {

        private Lock lock;

        private long timeInSeconds;
        private LockContext<Result> lockContext;

        private Callable<Result> callable;

        public LockFutureCallableAdapter(Callable<Result> callable, Lock lock, long timeInSeconds,
                                         LockContext<Result> lockContext) {
            this.callable = callable;
            this.lock = lock;
            this.timeInSeconds = timeInSeconds;
            this.lockContext = lockContext;
        }

        @Override
        public Result call() throws Exception {
            try {
                return callable.call();
            } finally {
                done();
            }
        }

        private void done() {
            try {
                // 锁获取成功
                if (lockContext.tryLockedSucc) {
                    // 这里需要优化，当时lock里面用的是thread.currentthread
                    if (timeInSeconds > 0) {
                        Thread.sleep(timeInSeconds * 1000);
                    }
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                // fail do nothing
            }
        }

    }

    public interface LockThreadPoolFactory {
        ThreadPoolExecutor createThreadPoolExecutor();
    }
}
