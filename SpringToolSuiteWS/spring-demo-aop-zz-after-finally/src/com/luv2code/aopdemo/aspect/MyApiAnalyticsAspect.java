package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(7)
public class MyApiAnalyticsAspect {
  // @Before("forDaoPackage()")
  @Before("LuvAopExpressions.forDaoPackageNoGetterSetter()")
  public void performApiAnalytics() {
    System.out.println("\n===============>>>>> Performing API analytics");
  }
}
