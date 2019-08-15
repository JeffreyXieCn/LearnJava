package io.github.jeffreyxiecn.algorithms.disjointset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CountOneBlocks2Test {

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void whenThereIsOnlyOneBlockScenario1_thenCountOne() {
    int[][] matrix = {
      {0, 1, 0, 0, 0, 1, 0, 0},
      {0, 1, 1, 1, 1, 1, 0, 0}
    };
    int expect = 1;

    CountOneBlocks2 counter = new CountOneBlocks2(matrix);
    int actual = counter.count();
    assertEquals(expect, actual);
  }

  @Test
  void whenThereIsOnlyOneBlockScenario2_thenCountOne() {
    int[][] matrix = {
      {0, 1, 0, 1, 0, 1, 1, 0},
      {0, 1, 1, 1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0, 1, 1, 1},
      {0, 1, 0, 1, 1, 1, 1, 0},
      {0, 0, 1, 1, 0, 1, 1, 1},
      {0, 1, 1, 0, 1, 1, 0, 1}
    };
    int expect = 1;

    CountOneBlocks2 counter = new CountOneBlocks2(matrix);
    int actual = counter.count();
    assertEquals(expect, actual);
  }

  @Test
  void whenThereAreTwoBlocks_thenCountTwo() {
    int[][] matrix = {
      {0, 1, 0, 0, 0, 1, 1, 0},
      {0, 1, 1, 1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0, 1, 1, 0},
      {0, 0, 0, 0, 0, 1, 1, 0},
      {0, 0, 0, 0, 1, 1, 1, 0}
    };
    int expect = 2;

    CountOneBlocks2 counter = new CountOneBlocks2(matrix);
    int actual = counter.count();
    assertEquals(expect, actual);
  }

  @Test
  void whenThereIsOnlyOneRow_thenCountCorrectly() {
    int[][] matrix = {{0, 1, 0, 0, 0, 1, 0, 0}};
    int expect = 2;

    CountOneBlocks2 counter = new CountOneBlocks2(matrix);
    int actual = counter.count();
    assertEquals(expect, actual);
  }

  @Test
  void whenThereIsOnlyOneColumn_thenCountCorrectly() {
    int[][] matrix = {{0}, {1}, {0}, {0}, {1}, {1}, {0}, {1}};
    int expect = 3;

    CountOneBlocks2 counter = new CountOneBlocks2(matrix);
    int actual = counter.count();
    assertEquals(expect, actual);
  }
}
