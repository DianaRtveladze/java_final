package com.exam.DianaRtveladze.services;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @AfterReturning(value = "@annotation(Loggable)", returning = "result")
    public void logMethodExecutionTime(JoinPoint joinPoint, Object result) {
        long executionTime = System.currentTimeMillis() - ((Date) result).getTime();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        //log.info("Method {} in class {} took {} ms to execute", methodName, className, executionTime);
    }
}
