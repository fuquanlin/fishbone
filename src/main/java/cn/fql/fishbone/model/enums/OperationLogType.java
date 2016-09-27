package cn.fql.fishbone.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by fuquanlin on 2016/9/27.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OperationLogType implements EnumBase<Integer> {

    GET(1, "查看"),//预留，暂时不做任何处理
    CREATE(2, "添加"),
    UPDATE(3, "修改"),
    DELETE(4, "删除"),
    OTHER(5, "其他");//主要针对那么些批处理，只会记录原始值

    private int code;
    private String message;

    OperationLogType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public static OperationLogType getOperationType(Integer code) {
        for (OperationLogType ul : values()) {
            if (ul.getCode().equals(code)) {
                return ul;
            }
        }
        return null;
    }
}
