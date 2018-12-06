package io.github.jeffreyxiecn.mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SeaBattleGameTest {

//  @Mock 
//  private SeaBattleGame game;
//  //private SeaBattleGame game = new SeaBattleGame("myGameFactory");
//  
//  @Before
//  public void setUp() {
//    MockitoAnnotations.initMocks(this);
//  }

  @Test
  public void testCountShips() {
    System.out.println("Enter testCountShips()");
    //SeaBattleGame game = Mockito.mock(SeaBattleGame.class);
    SeaBattleGame game = new SeaBattleGame("myGameFactory");
    int[][] board = {
      {1, 0, 0, 0, 0, 0},
      {1, 0, 1, 1, 0, 1},
      {0, 0, 1, 1, 0, 1},
      {0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 0, 0},
    };

    game.setGameBoard(board);
    assertThat(game.countShips(), is(4));
  }
}
