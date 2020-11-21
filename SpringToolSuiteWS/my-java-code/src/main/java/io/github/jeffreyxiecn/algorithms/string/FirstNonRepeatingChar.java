package io.github.jeffreyxiecn.algorithms.string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FirstNonRepeatingChar {
  /**
   * Given a string, find first non-repeating character in it by doing only one traversal of it.
   *
   * <pre>
   * For example:
   * Input string is: ABCDBAGHC
   * Output: The first non-repeating character in the string is: D
   * </pre>
   *
   * @param str the input
   * @return
   */
  public static char firstNonRepeatingChar(String str) {
    LinkedHashMap<Character, Integer> charCount = new LinkedHashMap<>();
    for (int i = 0; i < str.length(); i++) {
      char curChar = str.charAt(i);
      Integer count = charCount.get(curChar);
      if (count == null) {
        charCount.put(curChar, 1);
      } else {
        charCount.put(curChar, count + 1);
      }
    }

    Iterator<Entry<Character, Integer>> iterator = charCount.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<Character, Integer> curEntry = iterator.next();
      if (curEntry.getValue() == 1) {
        return curEntry.getKey();
      }
    }

    return 0;
  }

  /**
   * This method will search and return the first non-repeating character in the input string.
   *
   * @return The first non-repeating character in the input string. Make this comment long enough to
   *     trigger formatting.
   */
  public static char firstNonRepeatingChar2(String s) {
    // By default, LinkedHashMap maintains insertion order
    Map<Character, Boolean> m = new LinkedHashMap<>();
    for (char c : s.toCharArray()) {
      if (m.containsKey(c)) {
        m.put(c, true);
      } else {
        m.put(c, false);
      }
    }

    for (Map.Entry<Character, Boolean> e : m.entrySet()) {
      if (!e.getValue()) {
        return e.getKey();
      }
    }

    return 0;
  }

  public static char firstNonRepeatingChar3(String s) {
    Map<Character, Boolean> m = new HashMap<>();
    char[] arr = s.toCharArray();
    for (char c : arr) {
      m.put(c, m.containsKey(c));
    }

    for (char c : arr) {
      if (!m.get(c)) {
        return c;
      }
    }

    return 0;
  }
}
