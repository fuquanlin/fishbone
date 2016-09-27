package cn.fql.fishbone.exception;

/**
 * Created by fuquanlin on 2016/9/27.
 */
public class AuthenticationException  extends RuntimeException {

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
