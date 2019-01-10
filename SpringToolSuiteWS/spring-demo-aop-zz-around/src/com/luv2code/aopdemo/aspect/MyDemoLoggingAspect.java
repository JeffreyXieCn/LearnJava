package com.luv2code.aopdemo.aspect;

import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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

  // add a new advice for @AfterReturning on the findAccounts method
  @AfterReturning(
      pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
      returning = "result")
  public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

    // print out which method we are advising on
    String method = theJoinPoint.getSignature().toShortString();
    System.out.println("\n=======>>> Executing @AfterReturning on method: " + method);

    // print out the results of the method call
    System.out.println("\n=======>>> result is: " + result);

    // let's post-process the data ... let's modify it :-)
    convertAccountNamesToUpperCase(result);
    System.out.println("\n=======>>> result is: " + result);
  }

  private void convertAccountNamesToUpperCase(List<Account> result) {
    result.forEach(acc -> acc.setName(acc.getName().toUpperCase()));
  }

  @AfterThrowing(
      pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
      throwing = "theExc")
  public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
    // print out which method we are advising on
    String method = theJoinPoint.getSignature().toShortString();
    System.out.println("\n=======>>> Executing @AfterThrowing on method: " + method);

    // log the exception
    System.out.println("\n=======>>> The exception is: " + theExc);
  }

  // Note @After (finally) advice will run before @AfterThrowing / @AfterReturning
  @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
  public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
    // print out which method we are advising on
    String method = theJoinPoint.getSignature().toShortString();
    System.out.println("\n=======>>> Executing @After (finally) on method: " + method);
  }

  @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
  public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
    // print out method we are advising on
    String method = theProceedingJoinPoint.getSignature().toShortString();
    System.out.println("\n=======>>> Executing @Around on method: " + method);

    // get begin timestamp
    long begin = System.currentTimeMillis();

    // now, let's execute the method
    Object result = theProceedingJoinPoint.proceed();

    // get end timestamp
    long end = System.currentTimeMillis();

    // compute duration and display it
    long duration = end - begin;
    System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");
    return result;
  }
}
