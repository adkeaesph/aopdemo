package com.example.aopdemo.crosscuttingconcerns;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Aspects {

  @Before("@annotation(com.example.aopdemo.annotations.LogMethodName)")
  public void logMethodName(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String params = Arrays.toString(joinPoint.getArgs());
    System.out.println("Method called - "+methodName+", with parameters - "+params);
  }

  @Around("@annotation(com.example.aopdemo.annotations.MethodRunTime)")
  public Object calculateMethodRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    Object object = proceedingJoinPoint.proceed();
    long time = System.currentTimeMillis() - start;
    String methodName = proceedingJoinPoint.getSignature().getName();
    System.out.println("Time taken to run the method '" +methodName+ "' - " +(time/(double)1000)+" seconds");
    return object;
  }

}
