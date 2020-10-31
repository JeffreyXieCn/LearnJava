package io.github.jeffreyxiecn.algorithms.binarysearch;

public class FindSmallestLetterGreaterThanTarget0744 {
  public char nextGreatestLetter(char[] letters, char target) {
    int l = 0;
    int h = letters.length - 1;
    while (l <= h) {
      int m = l + (h - l) / 2;
      if (letters[m] <= target) {
        l = m + 1;
      } else {
        h = m - 1;
      }
    }

    if (l >= letters.length) {
      return letters[0];
    } else {
      return letters[l];
    }
  }

  public char nextGreatestLetter2(char[] letters, char target) {
    int n = letters.length;
    int l = 0, h = n - 1;
    while (l <= h) {
      int m = l + (h - l) / 2;
      if (letters[m] <= target) {
        l = m + 1;
      } else {
        h = m - 1;
      }
    }
    return l < n ? letters[l] : letters[0];
  }
}
