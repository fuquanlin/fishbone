package cn.fql.fishbone.model.enums;

/**
 * Created by fuquanlin on 2016/9/27.
 */
public interface EnumBase<E> {

    /**
     * 获取枚举代码
     *
     * @return
     */
    public E getCode();

    /**
     * 获取枚举信息
     *
     * @return
     */
    public String getMessage();

}

