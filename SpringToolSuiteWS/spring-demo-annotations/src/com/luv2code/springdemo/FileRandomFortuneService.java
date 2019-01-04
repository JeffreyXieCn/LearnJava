package com.luv2code.springdemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class FileRandomFortuneService implements FortuneService {

  // an array of fortunes read from a file
  private String[] data;

  // create a random number generator
  private Random myRandom = new Random();

  public FileRandomFortuneService() {
    System.out.println(">> FileRandomFortuneService, inside default constructor");
  }

  @PostConstruct
  private void loadFortunesFromFile() throws IOException {
    System.out.println(">> FileRandomFortuneService, loading fortunes from a file");
    Path path = Paths.get("src/MyFortunes.txt");
    Stream<String> streamOfStrings = Files.lines(path);
    // data = (String[]) streamOfStrings.toArray();
    data = streamOfStrings.collect(Collectors.toList()).toArray(new String[0]);
    streamOfStrings.close();
  }

  @Override
  public String getFortune() {
    // pick a random string from the array
    int index = myRandom.nextInt(data.length);
    return data[index];
  }
}
