package io.github.jeffreyxiecn.mock;

import java.util.concurrent.TimeUnit;

public class GameFactory {
  public int[][] loadGameBoardFromDB() {
    try {
      //simulate an expensive DB operation to load the game board
      System.out.println("simulate an expensive DB operation to load the game board...");
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    return new int[][] {{0, 0}, {0, 0}};
  }
}
