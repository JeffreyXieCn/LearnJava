package io.github.jeffreyxiecn.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicStream {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("One", "TWO", "three", "Three");
    Set<String> result = list.stream().map(String::toLowerCase).collect(Collectors.toSet());
    System.out.println("Resulting set is: " + result);
  }
}
