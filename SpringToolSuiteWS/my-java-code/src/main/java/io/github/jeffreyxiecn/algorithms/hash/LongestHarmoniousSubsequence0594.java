package io.github.jeffreyxiecn.algorithms.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestHarmoniousSubsequence0594 {
  public int findLHS(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int ele : nums) {
      map.put(ele, map.getOrDefault(ele, 0) + 1);
    }

    int max = 0;
    int temp;
    for (int key : map.keySet()) {
      if (map.containsKey(key - 1)) {
        temp = map.get(key - 1) + map.get(key);
        if (temp > max) {
          max = temp;
        }
      }

      if (map.containsKey(key + 1)) {
        temp = map.get(key + 1) + map.get(key);
        if (temp > max) {
          max = temp;
        }
      }
    }

    return max;
  }
}
