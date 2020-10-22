package io.github.jeffreyxiecn.algorithms.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow0417 {
  private static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  private int m, n;

  public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return new ArrayList<>();
    }

    m = matrix.length;
    n = matrix[0].length;

    boolean[][] gp = new boolean[m][]; // can reach Pacific
    boolean[][] ga = new boolean[m][]; // can reach Atlantic
    for (int i = 0; i < m; i++) {
      gp[i] = new boolean[n];
      Arrays.fill(gp[i], false);
      ga[i] = new boolean[n];
      Arrays.fill(ga[i], false);
    }

    for (int i = 0; i < n; i++) {
      dfs(matrix, 0, i, gp);
      dfs(matrix, m - 1, i, ga);
    }

    for (int j = 0; j < m; j++) {
      dfs(matrix, j, 0, gp);
      dfs(matrix, j, n - 1, ga);
    }

    List<List<Integer>> list = new ArrayList<>();
    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        if (gp[r][c] && ga[r][c]) {
          list.add(Arrays.asList(r, c));
        }
      }
    }

    return list;
  }

  private void dfs(int[][] matrix, int i, int j, boolean[][] go) {
    go[i][j] = true;
    for (int[] dir : directions) {
      int r = i + dir[0];
      int c = j + dir[1];
      if (r >= 0 && r < m && c >= 0 && c < n && !go[r][c] && matrix[r][c] >= matrix[i][j]) {
        dfs(matrix, r, c, go);
      }
    }
  }
}
