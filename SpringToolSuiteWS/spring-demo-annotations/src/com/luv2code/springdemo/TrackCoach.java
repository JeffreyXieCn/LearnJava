package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
  @Autowired
  // @Qualifier("randomFortuneService")
  @Qualifier("fileRandomFortuneService")
  private FortuneService fortuneService;

  @Value("${foo.email}")
  private String email;

  @Value("${foo.team}")
  private String team;

  public String getEmail() {
    return email;
  }

  public String getTeam() {
    return team;
  }

  //  @Autowired
  //  public void setFortuneService(FortuneService fortuneService) {
  //    System.out.println(">> TrackCoach: setFortuneService");
  //    this.fortuneService = fortuneService;
  //  }

  //  @Autowired
  //  public void doSomeCrazyStuff(FortuneService fortuneService) {
  //    System.out.println(">> TrackCoach: doSomeCrazyStuff");
  //    this.fortuneService = fortuneService;
  //  }

  // define a default constructor
  public TrackCoach() {
    System.out.println(">> TrackCoach: inside default constructor");
  }

  @Override
  public String getDailyWorkout() {
    return "Run a hard 5K";
  }

  @Override
  public String getDailyFortune() {
    return fortuneService.getFortune();
  }
}
