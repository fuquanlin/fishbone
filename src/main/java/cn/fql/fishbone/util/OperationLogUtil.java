package cn.fql.fishbone.util;

import cn.fql.fishbone.model.annotation.Module;
import cn.fql.fishbone.model.annotation.Operation;
import cn.fql.fishbone.model.annotation.SelectOne;
import cn.fql.fishbone.model.domain.common.Base;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by fuquanlin on 2016/9/27.
 */
public class OperationLogUtil {

    private static final Logger LOG = LoggerFactory.getLogger(OperationLogUtil.class);

    public static Module getModule(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getAnnotation(Module.class);
    }

    public static Operation getOperation(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(Operation.class);
    }

    public static Object getObject(Object instance, Object argument, String selectOneName) {
        if (instance == null || argument == null) {
            return null;
        }
        Method selectOneMethod = getSelectObjectMethod(instance,selectOneName);
        if (selectOneMethod == null) {
            return null;
        }

        if (argument.getClass().isArray()) {
            Object[] array = (Object[]) argument;
            return Stream.of(array)
                    .map(arg -> {
                        return selectObject(instance, selectOneMethod, getId(arg));
                    })
                    .toArray();
        }
        Long id = getId(argument);
        return selectObject(instance, selectOneMethod, id);
    }

    private static Long getId(Object argument) {
        if (argument instanceof Base) {
            return ((Base) argument).getId();
        } else if (argument instanceof Long) {
            return (Long) argument;
        }
        return null;
    }

    private static Object selectObject(Object instance, Method selectOneMethod, Long id) {
        try {
            return selectOneMethod.invoke(instance, id);
        } catch (Exception e) {
            LOG.info("selectObject exception: -> {} ", e);
        }
        return null;
    }

    private static Method getSelectObjectMethod(Object instance, String selectOneName) {
        Optional<Method> selectOneMethod = Stream
                .of(instance.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(SelectOne.class) && ("".equals(selectOneName) || selectOneName.equals(method.getAnnotation(SelectOne.class).value())))
                .findFirst();
        if (selectOneMethod.isPresent()) {
            return selectOneMethod.get();
        }
        return null;
    }
}
