package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.luv2code.aopdemo.Account;

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
  public void beforeAddAccountAdvice(JoinPoint theJointPoint) {
    System.out.println("\n===============>>>>> Executing @Before advice on method");

    // display the method signature
    MethodSignature methodSign = (MethodSignature) theJointPoint.getSignature();
    System.out.println("Method: " + methodSign);

    // display method arguments
    Object[] args = theJointPoint.getArgs();
    for (Object arg : args) {
      System.out.println(arg);
      if (arg instanceof Account) {
        Account theAccount = (Account) arg;
        System.out.println("account name: " + theAccount.getName());
        System.out.println("account level: " + theAccount.getLevel());
      }
    }
  }

  //  @After("forDaoPackage()")
  //  public void afterAddAccountAdvice() {
  //    System.out.println("\n<<<<<<=============== Executing @After advice on addAccount()");
  //  }
}
