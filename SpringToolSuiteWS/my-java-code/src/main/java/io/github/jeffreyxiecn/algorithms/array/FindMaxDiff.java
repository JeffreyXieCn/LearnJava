package io.github.jeffreyxiecn.algorithms.array;

import java.util.Arrays;

public class FindMaxDiff {
  /*
   *
   * Question2: One int array A[], indicating my weight number every day.
   * Find the max difference weight Max(Aj - Ai). Ai < Aj, i<j,
   * A[]={43, 54, 41, 43, 48, 53, 40}
   * return 53-41=12
   */
  public static int findMaxDiff(int[] arr) {
    int maxDiff = -1;
    int min = arr[0]; // lowest point before i
    int curDiff;
    for (int i = 1; i < arr.length; i++) {
      curDiff = arr[i] - min;
      if (curDiff > maxDiff) {
        maxDiff = curDiff;
      } else if (arr[i] < min) {
        min = arr[i];
      }
    }

    return maxDiff;
  }

  public static int findMaxDiff2(int[] arr) {
    boolean[] mark = new boolean[arr.length];
    Arrays.fill(mark, false);

    int maxDiff = -1;
    for (int i = 0; i < arr.length - 1; i++) {
      if (mark[i]) {
        // there is an earlier element <= arr[i], so the starting point can't be i
        continue;
      }

      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] >= arr[i]) {
          mark[j] = true;
          if (arr[j] - arr[i] > maxDiff) {
            maxDiff = arr[j] - arr[i];
          }
        }
      }
    }

    return maxDiff;
  }
}
