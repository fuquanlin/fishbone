package cn.fql.fishbone;

/**
 * Created by fuquanlin on 2016/5/23.
 */
public enum ErrorInfoEnum {

    SYSTEM_ERROR(1, "系统错误"), PARAM_ERROR(2, "非法参数"),
    AUTHORIZATION_ERROR(3, "系统未授权"),
    UNCAUGHT_ERROR(9999, "未知错误");

    private int code; // 错误码
    private String desc; // 错误提示（显示给用户信息）

    private ErrorInfoEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
