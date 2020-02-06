package com.slava.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Before("execution(* com.slava.service.*.* (..))")
    public void beforeServiceMethodInvocation(JoinPoint joinPoint) {
        System.out.println("Invocation of method " + joinPoint.getSignature());
    }

    @Around("execution(* com.slava.service.*.* (..))")
    public Object aroundServiceMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Execution of method " + joinPoint.getSignature() + " took "
                + (end - start) + "msec.");
        return result;
    }
}
