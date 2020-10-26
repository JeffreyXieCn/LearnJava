package io.github.jeffreyxiecn.algorithms.recursionanddp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MinimumPathSum0064Test {

  private static MinimumPathSum0064 sut;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    sut = new MinimumPathSum0064();
  }

  @Test
  void testMinPathSum() {
    int[][] input = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    int expected = 7;
    int actual = sut.minPathSum(input);
    assertEquals(expected, actual);
  }

  @Test
  void testMinPathSum2() {
    int[][] input = {
      {1, 3, 7, 2, 4}, {6, 2, 1, 5, 3}, {7, 8, 10, 11, 20}, {33, 100, 2, 1, 1}, {3, 2, 17, 70, 102}
    };
    int expected = 123;
    int actual = sut.minPathSum(input);
    assertEquals(expected, actual);
  }
}
