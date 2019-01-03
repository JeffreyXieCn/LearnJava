package com.luv2code.springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {

  private static String[] fortunes = {"Fortune1", "Fortune2", "Fortune3"};
  private Random random;

  public RandomFortuneService() {
    random = new Random();
  }

  @Override
  public String getFortune() {
    return fortunes[random.nextInt(fortunes.length)];
  }
}
