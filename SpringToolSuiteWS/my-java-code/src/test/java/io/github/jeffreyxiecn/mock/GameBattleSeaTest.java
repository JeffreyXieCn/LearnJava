package io.github.jeffreyxiecn.mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameBattleSeaTest {

  @Mock private GameFactory mockGameFactory;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void givenGameBoardOf4Ships_whenCount4Ships_thenCorrect() {
    System.out.println("mockGameFactory has type:" + mockGameFactory.getClass().getCanonicalName());
    int[][] gameBoard = {
      {1, 0, 0, 0, 0, 0},
      {1, 0, 1, 1, 0, 1},
      {1, 0, 1, 1, 0, 1},
      {0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 0, 0},
    };
    
    when(mockGameFactory.loadGameBoardFromDB()).thenReturn(gameBoard);
    GameBattleSea game = new GameBattleSea(mockGameFactory);
    verify(mockGameFactory, times(1)).loadGameBoardFromDB();
    assertThat(game.countShips(), is(4));
  }

  @Test
  public void givenGameBoardOf0Ships_whenCount0Ships_thenCorrect() {
    int[][] gameBoard = {
      {0, 0, 0, 0},
      {0, 0, 0, 0}
    };
    
    when(mockGameFactory.loadGameBoardFromDB()).thenReturn(gameBoard);
    GameBattleSea game = new GameBattleSea(mockGameFactory);

    assertThat(game.countShips(), is(0));
  }

  @Test
  public void givenGameBoardOf1Ship_whenCount1Ship_thenCorrect() {
    int[][] gameBoard = {
      {1, 1, 1, 1},
      {1, 1, 1, 1}
    };
    
    when(mockGameFactory.loadGameBoardFromDB()).thenReturn(gameBoard);
    GameBattleSea game = new GameBattleSea(mockGameFactory);
    assertThat(game.countShips(), is(1));
  }
}
