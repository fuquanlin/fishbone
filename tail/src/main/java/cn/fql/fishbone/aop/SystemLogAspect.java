package cn.fql.fishbone.aop;

import cn.fql.fishbone.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by fuquanlin on 2016/10/5.
 */
@Aspect
@Component
@Order(1)
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * cn.fql.fishbone.service..*.*(..))")
    public void systemLog() {

    }

    @Before("systemLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        logger.info("REQUEST : " + joinPoint.getTarget() + " " + joinPoint.getSignature().getName() + " " + JsonUtil.serializeObject(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "systemLog()", returning = "ret")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        logger.info("RESPONSE " + joinPoint.getTarget() + " " + (System.currentTimeMillis() - startTime.get()) + ": " + joinPoint.getSignature().getName() + " " + JsonUtil.serializeObject(ret));
    }
}


