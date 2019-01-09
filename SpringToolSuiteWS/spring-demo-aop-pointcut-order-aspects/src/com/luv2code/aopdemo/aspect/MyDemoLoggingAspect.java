package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
  // this is where we add all of our related advices for logging

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
  // @Before("forDaoPackage()")
  @Before("LuvAopExpressions.forDaoPackageNoGetterSetter()")
  public void beforeAddAccountAdvice() {
    System.out.println("\n===============>>>>> Executing @Before advice on method");
  }

  //  @After("forDaoPackage()")
  //  public void afterAddAccountAdvice() {
  //    System.out.println("\n<<<<<<=============== Executing @After advice on addAccount()");
  //  }
}
