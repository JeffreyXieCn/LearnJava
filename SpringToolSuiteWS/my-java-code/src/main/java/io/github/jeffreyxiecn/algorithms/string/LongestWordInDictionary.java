package io.github.jeffreyxiecn.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
// import javafx.util.Pair;
import org.apache.commons.lang3.tuple.MutablePair;

// https://techdevguide.withgoogle.com/resources/find-longest-word-in-dictionary-that-subsequence-of-given-string/#code-challenge
public class LongestWordInDictionary {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String s = "abppplee";
    Set<String> dict = new HashSet<>(Arrays.asList("able", "ale", "apple", "bale", "kangaroo"));
    System.out.println(findLongestWordInDict(s, dict));

    System.out.println(findLongestWordInDict2(s, dict));

    System.out.println(findLongestWordInDict3(s, dict));
  }

  public static String findLongestWordInDict(String s, Set<String> dict) {
    // Time complexity: O(dict.size() * s.length())

    String result = null;
    for (String word : dict) {
      int start = 0;
      boolean found = true;
      for (char c : word.toCharArray()) {
        int idx = s.indexOf(c, start);
        if (idx == -1) {
          found = false;
          break;
        }
        start = idx + 1;
      }
      if (found) {
        if (result == null) {
          result = word;
        } else {
          if (word.length() > result.length()) {
            result = word;
          }
        }
      }
    }

    return result;
  }

  // Preprocess s to build the letter to indices, and look up the index with binary search
  public static String findLongestWordInDict2(String s, Set<String> dict) {
    char[] array = s.toCharArray();
    Map<Character, ArrayList<Integer>> map = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
      if (map.containsKey(array[i])) {
        map.get(array[i]).add(i);
      } else {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(i);
        map.put(array[i], list);
      }
    }

    System.out.println(map.toString());

    String result = null;
    for (String word : dict) {
      int lastIndex = -1;
      boolean found = true;
      for (char c : word.toCharArray()) {
        if (map.containsKey(c)) {
          lastIndex = smallestNumberBiggerThan(map.get(c), lastIndex);
          if (lastIndex == -1) {
            found = false;
            break;
          }
        } else {
          found = false;
          break;
        }
      }
      if (found) {
        if (result == null) {
          result = word;
        } else {
          if (word.length() > result.length()) {
            result = word;
          }
        }
      }
    }

    return result;
  }

  private static int smallestNumberBiggerThan(List<Integer> list, int target) {
    int begin = 0;
    int end = list.size() - 1;
    while (begin <= end) {
      int middle = (begin + end) / 2;
      if (list.get(middle) < target) {
        begin = middle + 1;
      } else {
        end = middle - 1;
      }
    }

    if (begin <= list.size() - 1) {
      return begin;
    }

    return -1;
  }

  // process the words simultaneously
  public static String findLongestWordInDict3(String s, Set<String> dict) {
    // For each Character in this map, String[Integer] = Character
    Map<Character, List<MutablePair<String, Integer>>> map = new HashMap<>();
    List<MutablePair<String, Integer>> list;
    // so initially it is grouped by first letter
    for (String word : dict) {
      addToMap(map, word.charAt(0), new MutablePair<>(word, 0));
    }

    System.out.println(map);

    String result = null;
    for (char c : s.toCharArray()) {
      if (map.containsKey(c)) {
        list = map.get(c);
        for (Iterator<MutablePair<String, Integer>> iterator = list.iterator();
            iterator.hasNext(); ) {
          MutablePair<String, Integer> pair = iterator.next();
          pair.setValue(pair.getValue() + 1);
          String word = pair.getKey();
          if (pair.getValue() == word.length()) {
            // this word is found
            iterator.remove();
            if (result == null) {
              result = word;
            } else {
              if (word.length() > result.length()) {
                result = word;
              }
            }
          } else {
            char nextChar = word.charAt(pair.getValue());
            if (nextChar != c) {
              // move this pair from this mapping to the mapping for nextChar
              iterator.remove();
              addToMap(map, nextChar, pair);
            }
          }
        }
      }

      System.out.println(map);
    }

    return result;
  }

  private static void addToMap(
      Map<Character, List<MutablePair<String, Integer>>> map,
      char key,
      MutablePair<String, Integer> pair) {
    List<MutablePair<String, Integer>> targetList;
    if (map.containsKey(key)) {
      targetList = map.get(key);
    } else {
      targetList = new ArrayList<>();
      map.put(key, targetList);
    }
    targetList.add(pair);
  }
}
