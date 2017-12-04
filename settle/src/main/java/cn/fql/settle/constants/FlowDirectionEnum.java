package cn.fql.settle.constants;

/**
 * Created by fuquanlin on 04/12/2017.
 */
public enum FlowDirectionEnum {
    IN(1, "收入"),
    OUT(2, "支出");

    private Integer code;
    private String name;

    FlowDirectionEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
