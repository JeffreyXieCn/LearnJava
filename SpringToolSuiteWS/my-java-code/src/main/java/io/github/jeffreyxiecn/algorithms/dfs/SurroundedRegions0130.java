package io.github.jeffreyxiecn.algorithms.dfs;

import java.util.Arrays;

public class SurroundedRegions0130 {
  private static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public static void solve(char[][] board) {
    if (board == null || board.length == 0) {
      return;
    }

    boolean[][] mark = new boolean[board.length][];
    for (int i = 0; i < mark.length; i++) {
      mark[i] = new boolean[board[i].length];
      Arrays.fill(mark[i], false);
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (i > 0 && i < board.length - 1 && j > 0 && j < board[i].length - 1) {
          continue;
        }

        if (board[i][j] == 'O' && !mark[i][j]) {
          // a non-visited 'O' is found on the border
          dfs(board, i, j, mark);
        }
      }
    }

    // now all the 'O' that's not marked should be flipped
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 'O' && !mark[i][j]) {
          board[i][j] = 'X';
        }
      }
    }
  }

  private static void dfs(char[][] board, int i, int j, boolean[][] mark) {
    mark[i][j] = true;
    for (int[] dir : directions) {
      int r = i + dir[0];
      int c = j + dir[1];
      if (r >= 0
          && r < board.length
          && c >= 0
          && c < board[r].length
          && board[r][c] == 'O'
          && !mark[r][c]) {
        dfs(board, r, c, mark);
      }
    }
  }
}
