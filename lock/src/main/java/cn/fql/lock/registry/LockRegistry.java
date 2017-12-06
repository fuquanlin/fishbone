package cn.fql.lock.registry;

import java.util.concurrent.locks.Lock;

/**
 * Created by fuquanlin on 06/12/2017.
 */
public interface LockRegistry {

    /**
     * Obtains the lock associated with the parameter object.
     * @param lockKey The object with which the lock is associated.
     * @return The associated lock.
     */
    Lock obtain(Object lockKey);
}
