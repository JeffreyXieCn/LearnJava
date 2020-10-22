package io.github.jeffreyxiecn.collection;

import java.util.HashMap;
import java.util.Map;

public class BasicCollection {

  public static void main(String[] args) {
    Map<String, Integer> map = new HashMap<>();
    map.put("One", 1);
    map.put("Two", 2);
    map.put("Three", 3);

    System.out.println("Printing the map:" + map);

    Map<String, String> map2 = new HashMap<>();
    map2.put("One", "Un");
    map2.put("Two", "Deux");
    map2.put("Three", "Trois");

    System.out.println("Printing the map2:" + map2);
  }
}
