package cn.fql.lock;

/**
 * Created by fuquanlin on 06/12/2017.
 */
@FunctionalInterface
public interface LockCallBack<T> {
    T process();
}
