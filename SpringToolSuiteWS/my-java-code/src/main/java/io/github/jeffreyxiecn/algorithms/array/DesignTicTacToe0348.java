package io.github.jeffreyxiecn.algorithms.array;

import java.util.Arrays;

public class DesignTicTacToe0348 {
  private int[][] board;
  private int n;
  /** Initialize your data structure here. */
  public DesignTicTacToe0348(int n) {
    this.n = n;
    board = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(
          board[i], 0); // 0: empty, 1: piece placed by player 1, 2: piece placed by player 2
    }
  }

  /**
   * Player {player} makes a move at ({row}, {col}).
   *
   * @param row The row of the board.
   * @param col The column of the board.
   * @param player The player, can be either 1 or 2.
   * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2:
   *     Player 2 wins.
   */
  public int move(int row, int col, int player) {
    board[row][col] = player;
    // check row to see whether player wins
    int i;
    for (i = 0; i < n; i++) {
      if (board[row][i] != player) {
        break;
      }
    }

    if (i >= n) {
      return player;
    }

    // check col to see whether player wins
    for (i = 0; i < n; i++) {
      if (board[i][col] != player) {
        break;
      }
    }

    if (i >= n) {
      return player;
    }

    if (row == col) {
      // check diagonal
      for (i = 0; i < n; i++) {
        if (board[i][i] != player) {
          break;
        }
      }

      if (i >= n) {
        return player;
      }
    }

    if (row + col == n - 1) {
      // check diagnol
      for (i = 0; i < n; i++) {
        if (board[i][n - 1 - i] != player) {
          break;
        }
      }

      if (i >= n) {
        return player;
      }
    }

    return 0;
  }
}

/**
 * Your TicTacToe object will be instantiated and called as such: TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
