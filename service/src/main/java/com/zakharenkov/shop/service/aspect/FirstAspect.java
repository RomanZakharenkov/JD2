package com.zakharenkov.shop.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FirstAspect {

//    @Pointcut("execution(public * *..service.service.*.*(..))")
//    public void addLogging() {
//    }

    @Around("execution(public * *..service.service.*.*(..))")
//    @Around("execution(public Optional<Product> getById(Long))")
    public Object aroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before ...");
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            System.out.println("catch exception");
            throw ex;
        } finally {
            System.out.println("finally ...");
        }
        System.out.println("last step");

        return result;
    }
}
