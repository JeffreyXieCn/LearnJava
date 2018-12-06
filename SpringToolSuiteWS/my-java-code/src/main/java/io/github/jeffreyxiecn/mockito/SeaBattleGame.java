package io.github.jeffreyxiecn.mockito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeaBattleGame {
  private static final Logger LOG = LoggerFactory.getLogger(SeaBattleGame.class);
  private int[][] gameBoard;

  public SeaBattleGame(String gameFactory) {
    LOG.debug("It takes a long time to build the gameBoard with the gameFactory");
  }

  public void setGameBoard(int[][] board) {
    LOG.debug("Enter method setGameBoard()");
    System.out.println("Enter method setGameBoard()");
    //gameBoard = board;
    gameBoard = new int[][]{
        {1, 0, 0, 0, 0, 0},
        {1, 0, 1, 1, 0, 1},
        {0, 0, 1, 1, 0, 1},
        {0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 0, 0},
      };
  }

  public int countShips() {
    LOG.debug("Enter method countShips()");
    System.out.println("Enter method countShips()");
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
