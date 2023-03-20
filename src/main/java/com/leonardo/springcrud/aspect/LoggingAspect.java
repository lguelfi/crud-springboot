package com.leonardo.springcrud.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.leonardo.springcrud.controllers.*.*(..))")
    private void forControllerPackage() {
        
    }

    @Pointcut("execution(* com.leonardo.springcrud.services.*.*(..))")
    private void forServicePackage() {
        
    }

    @Pointcut("forControllerPackage() || forServicePackage()")
    private void forApp() {
        
    }

    @Before("forApp()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @Before: calling method: " + method);

        Object[] args = joinPoint.getArgs();

        for (Object object : args) {
            logger.info("=====>> argument: " + object);
        }
    }

    @AfterReturning(pointcut = "forApp()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning: calling method: " + method);

        logger.info("=====>> result: " + result);
    }
    
}
