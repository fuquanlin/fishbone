package cn.fql.lock.service;

import cn.fql.lock.LockCallBack;

import java.util.concurrent.locks.Lock;

/**
 * Created by fuquanlin on 06/12/2017.
 */
public interface LockService {
    /**
     *
     * @param lockKey
     * @return
     * @throws Exception
     */
    public Lock obtainLock(String lockKey) throws Exception;

    /**
     * @param callBack
     * @param lockKey
     * @param lockTimeSeconds
     *            小于等于0表示不锁定
     * @return
     * @throws InterruptedException
     */
    public <Result> void lockProcess(LockCallBack<Result> callBack, String lockKey, long lockTimeSeconds)
            throws Exception;

    /**
     * 等待执行结果
     *
     * @param callBack
     * @param lockKey
     * @param lockTimeSeconds
     * @return
     * @throws Exception
     */
    public <Result> Result lockProcessWithResult(LockCallBack<Result> callBack, String lockKey,
                                                        long lockTimeSeconds) throws Exception;
}
