package com.zakharenkov.shop.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class FirstAspect {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Around("execution(public * *..service.service.*.*(..))")
    public Object aroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        LOGGER.info("Method" + name + " started");
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            LOGGER.info("Method " + name + "processing Exception ");
            throw ex;
        }
        System.out.println("last step");
        LOGGER.info("Method " + name + " done");
        return result;
    }
}
