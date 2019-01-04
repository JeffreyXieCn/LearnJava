package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @Component("thatSillyCoach")
// @Scope("prototype")
public class TennisCoach implements Coach {
  private FortuneService fortuneService;

  @Autowired
  public TennisCoach(@Qualifier("happyFortuneService") FortuneService fortuneService) {
    this.fortuneService = fortuneService;
  }

  // define my init method
  @PostConstruct
  public void doMyStartupStuff() {
    System.out.println(">> TennisCoach: inside of doMyStartupStuff");
  }

  // define my destroy method
  @PreDestroy
  public void doMyCleanupStuff() {
    System.out.println(">> TennisCoach: inside of doMyCleanupStuff");
  }

  @Override
  public String getDailyWorkout() {
    return "Practice your backhand volley";
  }

  @Override
  public String getDailyFortune() {
    return fortuneService.getFortune();
  }
}

/*
Question

I have finished the video "Constructor Injection - Writing Code part2".

I have commented the Autowired annotation. But still it worked. How did it work?

    //@Autowired
    public TennisCoach(FortuneService theFortuneService) {
        System.out.println(" theFortuneService " + theFortuneService);
        fortuneService = theFortuneService;
    }
===

Answer

This is a new feature of Spring 4.3.

Here is the snippet from the Spring Docs.

Section 1.9.2: Autowired

As of Spring Framework 4.3, an @Autowired annotation on such a constructor is no longer necessary if the target bean only defines one constructor to begin with. However, if several constructors are available, at least one must be annotated to teach the container which one to use.

I personally prefer to use the @Autowired annotation because it makes the code more readable. But as mentioned, the @Autowired is not required for this scenario.
---

See link to the docs.

https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-autowired-annotation

Resources for this lecture
Constructor Injection - Autowired
*/

/*
Special Note about Destroy Lifecycle and Prototype Scope
Section 9, Lecture 82
Here is a subtle point you need to be aware of with "prototype" scoped beans.

For "prototype" scoped beans, Spring does not call the @PreDestroy method.  Gasp!

I didn't know this either until I dug through the Spring reference manual researching a student's question.

Here is the answer from the Spring reference manual. Section 7.5.2

https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-prototype

---

In contrast to the other scopes, Spring does not manage the complete lifecycle of a
prototype bean: the container instantiates, configures, and otherwise assembles a
prototype object, and hands it to the client, with no further record of that prototype
instance.

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.

To get the Spring container to release resources held by prototype-scoped beans, try using a custom bean post-processor, which holds a reference to beans that need to be cleaned up.

---

This also applies to XML configuration.
*/
