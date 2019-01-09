package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
  // this is where we add all of our related advices for logging

  // define Pointcut expressions for reuse
  @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
  private void forDaoPackage() {}

  // let's start with an @Before advice

  //  @Before("execution(public void addAccount())")
  // @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
  // @Before("execution(public void add*())")
  // @Before("execution(void add*())")
  // @Before("execution(* add*())")
  // @Before(
  //    "execution(* add*(com.luv2code.aopdemo.Account, ..))") // note must use fully qualified type
  // for method params
  // @Before("execution(* add*(..))")
  @Before("forDaoPackage()")
  public void beforeAddAccountAdvice() {
    System.out.println("\n===============>>>>> Executing @Before advice on method");
  }

  @Before("forDaoPackage()")
  public void performApiAnalytics() {
    System.out.println("\n===============>>>>> Performing API analytics");
  }

  @After("forDaoPackage()")
  public void afterAddAccountAdvice() {
    System.out.println("\n<<<<<<=============== Executing @After advice on addAccount()");
  }
}
