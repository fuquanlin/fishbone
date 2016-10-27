package cn.fql.fishbone.web.controller.advise;

import cn.fql.fishbone.exception.AuthorizationException;
import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.util.ResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fuquanlin on 2016/9/27.
 */
@ControllerAdvice
public class ExceptionHandlingAdvise {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingAdvise.class);

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Result<?> handleError(Throwable e) {
        String msg = e.getMessage();
        LOGGER.info("handle error ：msg->{}", msg);

        if (e instanceof AuthorizationException) {
            return ResultBuilder.authorizationError(msg);
        } else if (e instanceof IllegalArgumentException) {
            return ResultBuilder.paramError(msg);
        }else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) e;
            String errorMsg = manve
                    .getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .collect(
                            StringBuffer::new,
                            (sb, error) -> sb.append(error.getField())
                                    .append(":")
                                    .append(error.getDefaultMessage())
                                    .append(" "), StringBuffer::append)
                    .toString();
            return ResultBuilder.paramError(errorMsg);
        }

        LOGGER.error("uncaught error", e);
        return ResultBuilder.uncaughtError(msg);
    }
}

