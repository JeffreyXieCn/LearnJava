package io.github.jeffreyxiecn.algorithms.recursionanddp;

import java.util.Arrays;

public class LongestIncreasingSubsequence0300 {
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int[] dp = new int[nums.length]; // dp[i] is the LIS including nums[i]
    dp[0] = 1;

    // time complexity: 1 + 2 + 3 + ... + n-2 = (n^2 - 3*n + 2)/2 = O(n^2)
    for (int i = 1; i < nums.length; i++) {
      int max = 0;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i] && dp[j] > max) {
          max = dp[j];
        }
      }
      dp[i] = max + 1;
    }

    int maxLen = 0;
    for (int k = 0; k < nums.length; k++) {
      if (dp[k] > maxLen) {
        maxLen = dp[k];
      }
    }

    return maxLen;
  }

  public int lengthOfLIS2(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      int max = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          max = Math.max(max, dp[j] + 1);
        }
      }
      dp[i] = max;
    }
    return Arrays.stream(dp).max().orElse(0);
  }
}
