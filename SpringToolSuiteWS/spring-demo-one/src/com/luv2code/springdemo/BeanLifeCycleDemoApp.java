package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

  public static void main(String[] args) {
    // load the spring configuration file
    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");

    // retrieve bean from spring container
    Coach theCoach = context.getBean("myCoach", Coach.class);

    System.out.println(theCoach.getDailyWorkout());

    // close the context
    context.close();
  }
}

/*
Special Note about Destroy Lifecycle and Prototype Scope
Section 6, Lecture 49
There is a subtle point you need to be aware of with "prototype" scoped beans.

For "prototype" scoped beans, Spring does not call the destroy method.  Gasp!


---

In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean: the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.



---

This also applies to both XML configuration and Annotation-based configuration.
*/
