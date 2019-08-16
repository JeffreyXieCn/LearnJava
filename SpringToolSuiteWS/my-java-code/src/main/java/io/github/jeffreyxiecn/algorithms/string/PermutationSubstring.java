package io.github.jeffreyxiecn.algorithms.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Example: Given a smaller strings and a bigger string b, design an algorithm to find all
 * permutations of the shorter string within the longer one. Print the location of each permutation.
 *
 * @author Jeffrey XIE
 */
public class PermutationSubstring {

  public static void main(String[] args) {
    // findPermutationOfSubstring("", "");
    findPermutationOfSubstring("abcd", "abxcdbyacbdaczzzacdamam");
    findPermutationOfSubstring2("abcd", "abxcdbyacbdaczzzacdamam");
    findPermutationOfSubstring3("abcd", "abxcdbyacbdaczzzacdamam");

    findPermutationOfSubstring("abbc", "cbabadcbbabbcbabaabccbabc");
    findPermutationOfSubstring2("abbc", "cbabadcbbabbcbabaabccbabc");
    findPermutationOfSubstring3("abbc", "cbabadcbbabbcbabaabccbabc");
  }

  // sliding window approach, assume the characters in s are unique
  public static void findPermutationOfSubstring(String s, String b) {
    int a = 'a';
    int A = 'A';
    int z = 'z';
    int Z = 'Z';
    System.out.println(a); // 97
    System.out.println(A); // 65
    System.out.println(z); // 122
    System.out.println(Z); // 90

    // default -1, 0 means a char in s but not matched yet, 1 means a char in s and matched
    byte[] flags = new byte[256];
    Arrays.fill(flags, (byte) -1);
    for (char ch : s.toCharArray()) {
      flags[ch] = 0;
    }

    byte[] tempFlags = new byte[flags.length];
    int matchCount;
    int i = 0;
    while (i <= b.length() - s.length()) {
      System.arraycopy(flags, 0, tempFlags, 0, flags.length);
      matchCount = 0;
      for (int j = 0; j < s.length(); j++) {
        if (tempFlags[b.charAt(i + j)] == 0) {
          // match it
          tempFlags[b.charAt(i + j)] = 1;
          matchCount++;
        } else if (tempFlags[b.charAt(i + j)] == 1) {
          // already matched
          i++;
          break;
        } else {
          // not exist in s, start next scan from j + 1
          i = i + j + 1;
          break;
        }
      }

      if (matchCount == s.length()) {
        System.out.println(
            "Found with findPermutationOfSubstring: " + b.substring(i, i + s.length()));
        i++;
      }
    }
  }

  // sliding window approach, the characters in s don't need to be unique
  public static void findPermutationOfSubstring2(String s, String b) {
    byte[] counters = new byte[256];
    Arrays.fill(counters, (byte) 0);
    for (char ch : s.toCharArray()) {
      counters[ch]++;
    }

    byte[] tempCounters = new byte[counters.length];
    int i = 0;
    while (i <= b.length() - s.length()) {
      Arrays.fill(tempCounters, (byte) 0);
      int j;
      for (j = 0; j < s.length(); j++) {
        if (counters[b.charAt(i + j)] == 0) {
          // not exist in s, start next scan from j + 1
          i = i + j + 1;
          break;
        }
        tempCounters[b.charAt(i + j)]++;
      }

      if (j >= s.length()) {
        if (Arrays.equals(counters, tempCounters)) {
          System.out.println(
              "Found with findPermutationOfSubstring2: " + b.substring(i, i + s.length()));
        }
        i++;
      }
    }
  }

  // sliding window approach, the characters in s don't need to be unique, use HashMap
  public static void findPermutationOfSubstring3(String s, String b) {
    Map<Character, Integer> map = new HashMap<>();
    int counter;
    for (char ch : s.toCharArray()) {
      counter = map.getOrDefault(ch, 0);
      counter++;
      map.put(ch, counter);
    }

    System.out.println("Map:" + map);

    Map<Character, Integer> tempMap;
    char curChar;
    int i = 0;
    while (i <= b.length() - s.length()) {
      tempMap = new HashMap<>(map);
      for (int j = 0; j < s.length(); j++) {
        curChar = b.charAt(i + j);
        if (!map.containsKey(curChar)) {
          // not exist in s, start next scan from j + 1
          i = i + j + 1;
          break;
        }

        // decrease the counter in temMap, if it's zero, remove it from the map
        if (tempMap.containsKey(curChar)) {
          counter = tempMap.get(curChar);
          counter--;
          if (counter == 0) {
            tempMap.remove(curChar);
          } else {
            tempMap.put(curChar, counter);
          }
        } else {
          // curChar appeared more times in current sliding window than in s
          i++;
          break;
        }
      }

      if (tempMap.isEmpty()) { // each character and frequency is matched
        System.out.println(
            "Found with findPermutationOfSubstring3: " + b.substring(i, i + s.length()));
        i++;
      }
    }
  }
}
