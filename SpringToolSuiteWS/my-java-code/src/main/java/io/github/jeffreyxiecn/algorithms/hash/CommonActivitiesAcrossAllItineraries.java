package io.github.jeffreyxiecn.algorithms.hash;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommonActivitiesAcrossAllItineraries {
  public static void main(String[] args) {
    System.out.println("Hello Java");

    List<String> input = Arrays.asList("ACAABCAACAA", "ATAACA", "IGACAAIGAIGAIGAIGAIGA");
    String expect = "ACA";
    String actual = popular_sequence(input, 3);
    System.out.println(actual);
    System.out.println(expect.equals(actual));

    expect = "AC";
    actual = popular_sequence(input, 2);
    System.out.println(actual);
    System.out.println(expect.equals(actual));

    input = Arrays.asList("ACAABBCAA", "ACCABCAA");
    expect = "BCAA";
    actual = popular_sequence(input, 4);
    System.out.println(actual);
    System.out.println(expect.equals(actual));
  }

  public static String popular_sequence(List<String> its, int len) {
    // Map<String, Integer> map = new HashMap<>();
    Map<String, Integer> map = new LinkedHashMap<>();
    for (String it : its) {
      if (it.length() < len) {
        continue;
      }

      for (int i = 0; i <= it.length() - len; i++) {
        String act = it.substring(i, i + len);
        int index = it.indexOf(act);
        if (index >= 0 && index < i) {
          continue;
        }

        map.put(act, map.getOrDefault(act, 0) + 1);
      }
    }

    String res = "";
    int max = 0;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() > max) {
        max = entry.getValue();
        res = entry.getKey();
      }
    }

    return res;
  }
}
