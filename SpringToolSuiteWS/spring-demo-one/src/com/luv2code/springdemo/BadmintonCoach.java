package com.luv2code.springdemo;

public class BadmintonCoach implements Coach {
  private FortuneService fortuneService;
  private String emailAddress;
  private String team;

  public void setEmailAddress(String emailAddress) {
    System.out.println("BadmintonCoach: inside setter method - setEmailAddress");
    this.emailAddress = emailAddress;
  }

  public void setTeam(String team) {
    System.out.println("BadmintonCoach: inside setter method - setTeam");
    this.team = team;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getTeam() {
    return team;
  }

  public void setFortuneService(FortuneService fortuneService) {
    System.out.println("BadmintonCoach: inside setter method - setFortuneService");
    this.fortuneService = fortuneService;
  }

  public BadmintonCoach() {
    System.out.println("BadmintonCoach: inside no-arg constructor");
  }

  @Override
  public String getDailyWorkout() {
    return "Play badminton every Saturday";
  }

  @Override
  public String getDailyFortune() {
    return "Good news! " + fortuneService.getFortune();
  }
}
