package io.github.jeffreyxiecn.algorithms.recursionanddp;

import java.util.Arrays;

public class UniquePaths0062 {
  private int m;
  private int n;

  public int uniquePaths(int m, int n) {
    if (m == 1 || n == 1) {
      return 1;
    }

    int[][] mem = new int[m][];
    for (int i = 0; i < m; i++) {
      mem[i] = new int[n];
      Arrays.fill(mem[i], 0);
    }

    this.m = m;
    this.n = n;

    return dp(0, 0, mem); // Two slow!
  }

  private int dp(int i, int j, int[][] mem) {
    if (i >= m || j >= n) {
      return 0;
    }

    if (mem[i][j] != 0) {
      return mem[i][j];
    }

    if (i == m - 1 && j == n - 1) {
      mem[i][j] = 1;
      return 1;
    }

    return dp(i, j + 1, mem) + dp(i + 1, j, mem);
  }

  public int uniquePaths2(int m, int n) {
    int[] dp = new int[n];
    Arrays.fill(dp, 1);

    for (int i = 1; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (j == 0) {
          dp[j] = 1;
        } else {
          dp[j] = dp[j - 1] + dp[j];
        }
      }
    }

    return dp[n - 1];
  }

  public int uniquePaths3(int m, int n) {
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[j] = dp[j] + dp[j - 1];
      }
    }
    return dp[n - 1];
  }

  public int uniquePaths4(int m, int n) {
    int S = m + n - 2; // 总共的移动次数
    int D = m - 1; // 向下的移动次数
    long ret = 1;
    for (int i = 1; i <= D; i++) {
      ret = ret * (S - D + i) / i;
    }
    return (int) ret;
  }
}
