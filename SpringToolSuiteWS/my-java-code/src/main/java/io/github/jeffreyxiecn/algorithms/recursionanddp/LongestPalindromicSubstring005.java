package io.github.jeffreyxiecn.algorithms.recursionanddp;

import java.util.Arrays;

public class LongestPalindromicSubstring005 {
  /*
   * Run time complexity is O(n^3), space complexity is O(n)
   */
  public String longestPalindrome1(String s) {
    int[] len = new int[s.length()];
    Arrays.fill(len, 1);
    for (int i = 0; i < s.length() - 1; i++) {
      for (int j = s.length() - 1; j > i && (j - i + 1) > len[i]; j--) {
        if (isPalindrome(s, i, j)) {
          int start = i;
          int end = j;
          while (start < end) {
            int curLen = end - start + 1;
            if (curLen > len[start]) {
              len[start] = curLen;
            }

            if (curLen > len[end]) {
              len[end] = curLen;
            }

            start++;
            end--;
          }

          break;
        }
      }
    }

    int maxLen = 0;
    int index = 0;
    for (int k = 0; k < s.length(); k++) {
      if (len[k] > maxLen) {
        maxLen = len[k];
        index = k;
      }
    }

    return s.substring(index, index + maxLen);
  }

  private boolean isPalindrome(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }

    return true;
  }

  public String longestPalindrome2(String s) {
    int n = s.length();
    int[][] pl = new int[n][n];
    for (int[] row : pl) {
      Arrays.fill(row, -1); // -1:unknown 0:not palindrome 1:palindrome
    }

    int maxLen = 1;
    int index = 0;
    for (int i = 0; i < n - 1; i++) {
      for (int j = n - 1; j > i && ((j - i + 1) > maxLen); j--) {
        if (isPalindrome2(s, i, j, pl) == 1) {
          if (j - i + 1 > maxLen) {
            maxLen = j - i + 1;
            index = i;
          }
          break;
        }
      }
    }

    return s.substring(index, index + maxLen);
  }

  private int isPalindrome2(String s, int i, int j, int[][] pl) {
    if (i == j) {
      pl[i][j] = 1;
      return pl[i][j];
    }

    if (i + 1 == j) {
      pl[i][j] = s.charAt(i) == s.charAt(j) ? 1 : 0;
      return pl[i][j];
    }

    if (pl[i][j] != -1) {
      return pl[i][j];
    }

    pl[i][j] = s.charAt(i) == s.charAt(j) && (isPalindrome2(s, i + 1, j - 1, pl) == 1) ? 1 : 0;

    return pl[i][j];
  }

  private int lo, maxLen;

  /*
   * solve it in O(n^2) time using only constant space
   */
  public String longestPalindrome3(String s) {
    int len = s.length();
    if (len < 2) {
      return s;
    }

    for (int i = 0; i < len - 1; i++) {
      // assume odd length, try to extend palindrome from Central Point i as long as possible
      extendPalindrome(s, i, i);
      // assume even length, try to extend palindrome from Central Point i, i + 1 as long as
      // possible
      extendPalindrome(s, i, i + 1);
    }
    return s.substring(lo, lo + maxLen);
  }

  private void extendPalindrome(String s, int j, int k) {
    while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
      j--;
      k++;
    }
    if (maxLen < k - j - 1) {
      lo = j + 1;
      maxLen = k - j - 1;
    }
  }
}
