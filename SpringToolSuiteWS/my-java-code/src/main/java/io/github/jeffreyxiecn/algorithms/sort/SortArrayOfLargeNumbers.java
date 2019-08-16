package io.github.jeffreyxiecn.algorithms.sort;

import java.util.Arrays;
import java.util.Comparator;

public class SortArrayOfLargeNumbers {

  // Function for sort an array of large numbers represented as strings
  // Time complexity : O(k * n Log n) where k is length of the longest number. Here assumption is
  // that the sort() function uses a O(n Log n) sorting algorithm.
  public static void sort(String[] arr) {
    // cmp will compare two strings first by length, if length is the same, then compare their
    // contents
    Comparator<String> cmp =
        (str1, str2) -> {
          if (str1.length() == str2.length()) {
            return str1.compareTo(str2);

          } else {
            return str1.length() - str2.length();
          }
        };
    Arrays.sort(arr, cmp);
  }
}
