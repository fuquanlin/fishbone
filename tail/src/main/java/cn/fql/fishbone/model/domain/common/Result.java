package cn.fql.fishbone.model.domain.common;

/**
 * Created by fuquanlin on 2016/9/27.
 */
public class Result<T>{

    private T model;
    private boolean success = true;
    private int errorCode;
    private String errorMsg;
    private String throwableMsg;

    public Result() {

    }

    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Result(boolean success, int errorCode, String errorMsg, String throwableMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.throwableMsg = throwableMsg;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        if (success) {
            this.setErrorCode(0);
            this.setErrorMsg(null);
        }
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getThrowableMsg() {
        return throwableMsg;
    }

    public void setThrowableMsg(String throwableMsg) {
        this.throwableMsg = throwableMsg;
    }
}
