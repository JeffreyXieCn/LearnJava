package io.github.jeffreyxiecn.algorithms.string;

import java.util.Arrays;

public class ValidAnagram0242 {
  public boolean isAnagram(String s, String t) {
    int[] count = new int[26];
    Arrays.fill(count, 0);

    for (char c : s.toCharArray()) {
      count[c - 'a']++;
    }

    for (char c : t.toCharArray()) {
      count[c - 'a']--;
    }

    for (int val : count) {
      if (val != 0) {
        return false;
      }
    }

    return true;
  }
}
