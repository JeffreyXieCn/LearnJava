package io.github.jeffreyxiecn.algorithms.recursionanddp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfItemsInContainer {
  private static int n;

  /*
   * I am using Dynamic Programming here to store the result of sub-problems in a 2D array mem to
   * avoid repeated calculation. mem[i][j] will be the number of items in closed containers from
   * starting point i until end point j.
   * The base case is: if i >= j, just return 0.
   * Otherwise, let k1, k2 be the next | starting from i (k1 may equal to i), and m2, m1 be the
   * previous | ending at j (m1 may equal to j),
   *     i...|(k1)...|(k2)...|(m2)...|(m1)...j
   * then mem[i][j] = itemsBetween(k1, k2) + itemsBetween(m2, m1) + mem[k2][m2]
   *
   * Space complexity: O(n^2), n is the length of string s. Because the size of the mem is n*n.
   * Since Java supports different lengths for different rows, it's possible to reduce the size by
   * half (don't allocate space if i >= j).
   *
   * Time complexity: For a given (startIndex, endIndex) range, a portion of the string s will be
   * scanned and all the || pairs within that range will be recorded in mem, this takes O(n) time.
   * Let p be the number of pairs, the complexity for this solution will be O(p*n). But since we
   * used mem array, the actual runtime will be much faster than scanning one character at a time
   *  and counting for each (startIndex, endIndex) range (the brute-force approach)
   */
  public static List<Integer> numberOfItemsInClosedContainers(
      String s, int[] startIndexes, int[] endIndexes) {

    List<Integer> result = new ArrayList<>();
    n = s.length();
    int[][] mem = new int[n][n];
    for (int[] row : mem) {
      Arrays.fill(row, -1); // -1 means unknown
    }

    for (int i = 0; i < startIndexes.length; i++) {
      // System.out.println();
      result.add(countItems(s, startIndexes[i] - 1, endIndexes[i] - 1, mem));
    }

    return result;
  }

  private static int countItems(String s, int i, int j, int[][] mem) {
    // System.out.println("i=" + i + ", j=" + j);
    if (i >= j) {
      return 0;
    }

    if (mem[i][j] != -1) {
      return mem[i][j];
    }

    int k = i; // find the closest | on i's right side
    while (k < n && s.charAt(k) != '|') {
      k++;
    }

    int m = j; // find the closest | on j's left side
    while (m >= 0 && s.charAt(m) != '|') {
      m--;
    }

    int left = 0; // count the stars up to the next |
    k = k + 1;
    while (k < m && s.charAt(k) == '*') {
      left++;
      k++;
    }

    int right = 0; // count the stars up to previous |
    m = m - 1;
    while (m > k && s.charAt(m) == '*') {
      right++;
      m--;
    }

    mem[i][j] = left + right + countItems(s, k, m, mem);

    return mem[i][j];
  }
}
