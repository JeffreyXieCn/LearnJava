package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
  // setup logger
  private Logger myLogger = Logger.getLogger(getClass().getName());

  // set up pointcut declarations
  @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
  private void forControllerPackage() {}

  @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
  private void forServicePackage() {}

  @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
  private void forDaoPackage() {}

  @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
  private void forAppFlow() {}

  // add @Before advice

  @Before("forAppFlow()")
  public void before(JoinPoint theJointPoint) {
    // display method we are calling
    String theMethod = theJointPoint.getSignature().toShortString();
    myLogger.info("=====>> in @Before: calling method: " + theMethod);

    // display the arguments to the method
    Object[] args = theJointPoint.getArgs();
    for (Object tempArg : args) {
      myLogger.info("=====>> arguments: " + tempArg);
    }
  }

  // add @AfterReturning advice
  @AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
  public void afterReturning(JoinPoint theJointPoint, Object theResult) {
    // display method we are returning from
    // String theMethod = theJointPoint.getSignature().toShortString();
    String theMethod = theJointPoint.getSignature().toLongString();
    myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);

    // display data returned
    myLogger.info("=====>> result: " + theResult);
  }
}
