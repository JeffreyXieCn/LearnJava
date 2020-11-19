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

  public int findLHS2(int[] nums) {
    Map<Integer, Integer> countForNum = new HashMap<>();
    for (int num : nums) {
      countForNum.put(num, countForNum.getOrDefault(num, 0) + 1);
    }
    int longest = 0;
    for (int num : countForNum.keySet()) {
      // no need to consider key num - 1, because if that key exists, (num - 1, num) will be
      // considered when iterating num - 1
      if (countForNum.containsKey(num + 1)) {
        longest = Math.max(longest, countForNum.get(num + 1) + countForNum.get(num));
      }
    }
    return longest;
  }
}
