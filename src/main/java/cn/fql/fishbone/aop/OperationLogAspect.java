package cn.fql.fishbone.aop;

import cn.fql.fishbone.model.annotation.Module;
import cn.fql.fishbone.model.annotation.Operation;
import cn.fql.fishbone.model.domain.OperationLog;
import cn.fql.fishbone.model.enums.OperationLogType;
import cn.fql.fishbone.service.base.OperationLogService;
import cn.fql.fishbone.util.JsonUtil;
import cn.fql.fishbone.util.OperationLogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by fuquanlin on 2016/9/27.
 */
@Aspect
@Component
public class OperationLogAspect {
    private static final Logger LOG = LoggerFactory.getLogger(OperationLogAspect.class);

    @Autowired
    private OperationLogService operationLogService;

    @Pointcut("@annotation(cn.fql.fishbone.model.annotation.Operation)")
    public void actionMethod() {
        // AOP切点方法，无需任何内容
    }

    @Around(value = "actionMethod() ")
    public Object aroundInvokeActionMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("Before AOP logger");
        Object result = null;
        Object instance = joinPoint.getTarget();
        Object[] arguments = joinPoint.getArgs();
        if (arguments.length > 0) {
            Object before = null;
            Object after = null;
            Operation operation = OperationLogUtil.getOperation(joinPoint);
            if (OperationLogType.CREATE.equals(operation.value())) {
                result = joinPoint.proceed(arguments);
                if (result != null) {
                    after = OperationLogUtil.getObject(instance, result, operation.selectOne());
                } else {
                    after = arguments[0];
                }
            } else if (OperationLogType.UPDATE.equals(operation.value())) {
                before = OperationLogUtil.getObject(instance, arguments[0], operation.selectOne());
                result = joinPoint.proceed(arguments);
                after = OperationLogUtil.getObject(instance, arguments[0], operation.selectOne());
            } else if (OperationLogType.DELETE.equals(operation.value())) {
                before = OperationLogUtil.getObject(instance, arguments[0], operation.selectOne());
                result = joinPoint.proceed(arguments);
            } else if (OperationLogType.OTHER.equals(operation.value())) {
                result = joinPoint.proceed(arguments);
                after = arguments[0];
            }
            createOperationLog(joinPoint, before, after);
        } else {
            result = joinPoint.proceed(arguments);
        }
        LOG.info("After AOP logger");
        return result;
    }


    private void createOperationLog(JoinPoint joinPoint, Object before, Object after) {
        Module module = OperationLogUtil.getModule(joinPoint);
        if (module.ignoreLog()) {
            return;
        }
        Operation operation = OperationLogUtil.getOperation(joinPoint);
        if (operation.ignoreLog()) {
            return;
        }
        OperationLogType operationType = operation.value();
        if (OperationLogType.GET.equals(operationType)) {
            return;
        }

        OperationLog operationLog = new OperationLog();

        //who
        operationLog.setUsername(MDC.get("staffName"));
        //when
        Date now = new Date();
        operationLog.setGmtCreate(now);
        //where
        String moduleName = module.value();
        String description = operation.description();
        operationLog.setModuleName(moduleName);
        operationLog.setOperationType(operationType.getCode());
        operationLog.setOperationDesc(description);
        //do what
        if (before != null) {
            operationLog.setOldValue(JsonUtil.serializeObject(before));
        }
        if (after != null) {
            operationLog.setNewValue(JsonUtil.serializeObject(after));
        }
        operationLogService.createOperationLog(operationLog);
    }
}
