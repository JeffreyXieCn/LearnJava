package io.github.jeffreyxiecn.mock;

public class GameBattleSea {
  private int[][] gameBoard;

  public GameBattleSea(GameFactory factory) {
    gameBoard = factory.loadGameBoardFromDB();
  }

  public int countShips() {
    int count = 0;
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[i].length; j++) {
        if (isBeginningOfShip(i, j)) {
          count++;
        }
      }
    }
    return count;
  }

  private boolean isBeginningOfShip(int i, int j) {
    if (gameBoard[i][j] == 1
        && (i == 0 || gameBoard[i - 1][j] == 0)
        && (j == 0 || gameBoard[i][j - 1] == 0)) {
      return true;
    }

    return false;
  }
}
