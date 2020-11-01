package io.github.jeffreyxiecn.algorithms.recursionanddp;

import java.util.Arrays;

/*
Q Given a matrix with numbers and ability to start at any position and move N,S,E,W to only
positions with strictly higher numbers find the max path length traversed before you can go no
further.

1 2 3 9
5 7 8 9
4 3 2 1

Max path length = 8 [1,2,3,4,5,7,8,9]
*/
public class LongestPathInMatrix {
  private static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  private static int m;
  private static int n;

  public static int findLongestPath(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }

    m = matrix.length;
    n = matrix[0].length;
    int[][] mem = new int[m][n];
    for (int[] row : mem) {
      Arrays.fill(row, 0);
    }

    int max = 0;
    int temp;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        temp = dp(matrix, i, j, mem);
        if (temp > max) {
          max = temp;
        }
      }
    }

    return max;
  }

  private static int dp(int[][] matrix, int i, int j, int[][] mem) {
    if (mem[i][j] != 0) {
      return mem[i][j];
    }

    int max = 1;
    for (int[] dir : directions) {
      int r = i + dir[0];
      int c = j + dir[1];
      if (r >= 0 && r < m && c >= 0 && c < n && matrix[r][c] > matrix[i][j]) {
        int path = 1 + dp(matrix, r, c, mem);
        if (path > max) {
          max = path;
        }
      }
    }

    mem[i][j] = max;
    return max;
  }
}
