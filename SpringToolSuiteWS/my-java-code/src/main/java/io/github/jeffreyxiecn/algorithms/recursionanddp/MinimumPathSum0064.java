package io.github.jeffreyxiecn.algorithms.recursionanddp;

import java.util.Arrays;

public class MinimumPathSum0064 {
  private int m;
  private int n;

  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    m = grid.length;
    n = grid[0].length;

    int[][] min = new int[m][];
    for (int i = 0; i < m; i++) {
      min[i] = new int[n];
      Arrays.fill(min[i], Integer.MAX_VALUE);
    }

    return dp(grid, 0, 0, min);
  }

  private int dp(int[][] grid, int i, int j, int[][] min) {
    if (i == m - 1 && j == n - 1) {
      min[i][j] = grid[i][j];
      return min[i][j];
    }

    if (min[i][j] < Integer.MAX_VALUE) {
      return min[i][j];
    }

    int rRight = i, cRight = j + 1;
    int rDown = i + 1, cDown = j;

    if (cRight < n && rDown < m) {
      min[i][j] =
          Math.min(
              grid[i][j] + dp(grid, rRight, cRight, min), grid[i][j] + dp(grid, rDown, cDown, min));
    } else if (cRight >= n) {
      min[i][j] = grid[i][j] + dp(grid, rDown, cDown, min);
    } else {
      min[i][j] = grid[i][j] + dp(grid, rRight, cRight, min);
    }

    return min[i][j];
  }

  public int minPathSum2(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int m = grid.length, n = grid[0].length;
    int[] dp = new int[n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (j == 0) {
          dp[j] = dp[j]; // 只能从上侧走到该位置
        } else if (i == 0) {
          dp[j] = dp[j - 1]; // 只能从左侧走到该位置
        } else {
          dp[j] = Math.min(dp[j - 1], dp[j]);
        }
        dp[j] += grid[i][j];
      }
    }
    return dp[n - 1];
  }
}
