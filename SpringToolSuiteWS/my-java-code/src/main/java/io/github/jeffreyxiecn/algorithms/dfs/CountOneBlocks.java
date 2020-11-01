package io.github.jeffreyxiecn.algorithms.dfs;

import java.util.Arrays;

/*
*     int[][] matrix2 = {
     {0, 1, 0, 0, 0, 1, 1, 0},
     {0, 1, 1, 1, 0, 1, 1, 0},
     {0, 1, 0, 1, 0, 1, 1, 0},
     {0, 1, 0, 1, 0, 1, 1, 0},
     {0, 0, 0, 0, 0, 1, 1, 0},
     {0, 0, 0, 0, 1, 1, 1, 0}
   };
*/
public class CountOneBlocks {
  private static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  private static int m;
  private static int n;

  public static int countOneBlocks(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }

    int count = 0;
    m = matrix.length;
    n = matrix[0].length;
    boolean[][] mark = new boolean[m][n];
    for (boolean[] row : mark) {
      Arrays.fill(row, false);
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 1 && !mark[i][j]) {
          dfs(matrix, i, j, mark);
          count++;
        }
      }
    }

    return count;
  }

  private static void dfs(int[][] matrix, int i, int j, boolean[][] mark) {
    mark[i][j] = true;
    for (int[] dir : directions) {
      int r = i + dir[0];
      int c = j + dir[1];
      if (r >= 0 && r < m && c >= 0 && c < n && matrix[r][c] == 1 && !mark[r][c]) {
        dfs(matrix, r, c, mark);
      }
    }
  }
}
