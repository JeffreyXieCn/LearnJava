package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemoApp {

  public static void main(String[] args) {
    // read spring config java class
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(SportConfig.class);

    // get the bean from spring container
    // Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
    Coach theCoach = context.getBean("tennisCoach", Coach.class);

    // call a method on the bean
    System.out.println(theCoach.getDailyWorkout());
    System.out.println(theCoach.getDailyFortune());

    TrackCoach trackCoach = context.getBean("trackCoach", TrackCoach.class);

    // call a method on the bean
    System.out.println(trackCoach.getDailyWorkout());
    System.out.println(trackCoach.getDailyFortune());
    System.out.println(trackCoach.getEmail());
    System.out.println(trackCoach.getTeam());

    // close the context
    context.close();
  }
}
