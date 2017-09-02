package cn.fql.fishbone.util;

import cn.fql.fishbone.ErrorInfoEnum;
import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.web.dto.pagination.PagedQueryParam;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by fuquanlin on 2016/9/27.
 */
public class ResultBuilder {

    public static <T> Result<T> success() {
        return build(null);
    }

    public static <T> Result<T> build(T model) {
        Result<T> t = new Result<>();
        t.setSuccess(true);
        t.setModel(model);
        return t;
    }

    public static  Result build(PagedQueryParam param, List rows) {
        Result t = new Result<>();
        t.setSuccess(true);
        t.setModel( PaginationUtil.assable(param,rows));
        return t;
    }

    /**
     * 不带异常信息返回结果
     *
     * @param error
     * @param msg
     * @param defaultMsg
     * @return
     */
    private static <T> Result<T> error(int error, String msg, String defaultMsg){
        Result<T> t = new Result<>();
        t.setSuccess(false);
        t.setErrorCode(error);
        if (StringUtils.isNotBlank(msg)) {
            t.setErrorMsg(msg);
        } else {
            t.setErrorMsg(defaultMsg);
        }
        t.setModel(null);
        return t;
    }

    /**
     * 带异常信息返回结果
     *
     * @param error
     * @param msg
     * @param defaultMsg
     * @param throwableMsg
     * @return
     */
    private static <T> Result<T> error(int error, String msg, String defaultMsg, String throwableMsg){
        Result<T> t = new Result<>();
        t.setSuccess(false);
        t.setErrorCode(error);
        if (StringUtils.isNotBlank(msg)) {
            t.setErrorMsg(msg);
        } else {
            t.setErrorMsg(defaultMsg);
        }
        t.setThrowableMsg(throwableMsg);
        t.setModel(null);
        return t;
    }

    public static <T> Result<T> paramError(String msg) {
        return error(ErrorInfoEnum.PARAM_ERROR.getCode(), msg, ErrorInfoEnum.PARAM_ERROR.getDesc());
    }

    public static <T> Result<T> authenticationError(String msg) {
        return error(ErrorInfoEnum.AUTHENTICATION_ERROR.getCode(), msg, ErrorInfoEnum.AUTHENTICATION_ERROR.getDesc());
    }

    public static <T> Result<T> authorizationError(String msg) {
        return error(ErrorInfoEnum.AUTHORIZATION_ERROR.getCode(), msg, ErrorInfoEnum.AUTHORIZATION_ERROR.getDesc());
    }

    public static <T> Result<T> uncaughtError(String throwableMsg) {
        return error(ErrorInfoEnum.UNCAUGHT_ERROR.getCode(), null, ErrorInfoEnum.UNCAUGHT_ERROR.getDesc(), throwableMsg);
    }
}
