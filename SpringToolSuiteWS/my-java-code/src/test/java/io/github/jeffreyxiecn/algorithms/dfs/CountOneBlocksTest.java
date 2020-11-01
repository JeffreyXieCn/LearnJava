package io.github.jeffreyxiecn.algorithms.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CountOneBlocksTest {

  @Test
  void test1() {
    int[][] input = {
      {0, 1, 0, 0, 0, 1, 0, 0},
      {0, 1, 1, 1, 1, 1, 0, 0}
    };
    int expect = 1;
    int actual = CountOneBlocks.countOneBlocks(input);

    assertEquals(expect, actual);
  }

  @Test
  void test2() {
    int[][] input = {
      {0, 1, 0, 0, 0, 1, 1, 0},
      {0, 1, 1, 1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0, 1, 1, 0},
      {0, 0, 0, 0, 0, 1, 1, 0},
      {0, 0, 0, 0, 1, 1, 1, 0}
    };

    int expect = 2;
    int actual = CountOneBlocks.countOneBlocks(input);

    assertEquals(expect, actual);
  }

  @Test
  void test3() {
    int[][] input = {
      {0, 1, 0, 1, 0, 1, 1, 0},
      {0, 1, 1, 1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0, 1, 1, 1},
      {0, 1, 0, 1, 1, 1, 1, 0},
      {0, 0, 1, 1, 0, 1, 1, 1},
      {0, 1, 1, 0, 1, 1, 0, 1}
    };

    int expect = 1;
    int actual = CountOneBlocks.countOneBlocks(input);

    assertEquals(expect, actual);
  }

  @Test
  void test4() {
    int[][] input = {{0, 1, 0, 0, 0, 1, 0, 0}};

    int expect = 2;
    int actual = CountOneBlocks.countOneBlocks(input);

    assertEquals(expect, actual);
  }

  @Test
  void test5() {
    int[][] input = {{0}, {1}, {0}, {0}, {1}, {1}, {0}, {1}};

    int expect = 3;
    int actual = CountOneBlocks.countOneBlocks(input);

    assertEquals(expect, actual);
  }
}
