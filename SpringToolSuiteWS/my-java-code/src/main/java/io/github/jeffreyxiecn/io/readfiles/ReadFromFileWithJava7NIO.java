package io.github.jeffreyxiecn.io.readfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFromFileWithJava7NIO {

  public static void main(String[] args) throws IOException {
    Path path = Paths.get("src/main/resources/readfiles/input.txt");
    List<String> allLines = Files.readAllLines(path);
    allLines.forEach(System.out::println);
  }
}
