package cn.fql.fishbone.exception;

/**
 * Created by fuquanlin on 2016/9/27.
 */
public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
    }

    public AuthorizationException(String message) {
        super(message);
    }
}