package io.github.jeffreyxiecn.algorithms.recursionanddp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class LongestPathInMatrixTest {

  @Test
  void test1() {
    int[][] matrix = new int[][] {{1, 2, 3, 9}, {5, 7, 8, 9}, {4, 3, 2, 1}};
    int expected = 8;
    int actual = LongestPathInMatrix.findLongestPath(matrix);
    assertEquals(expected, actual);
  }

  @Test
  void test2() {
    int[][] matrix = new int[][] {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}};
    int expected = 16;
    int actual = LongestPathInMatrix.findLongestPath(matrix);
    assertEquals(expected, actual);
  }

  @Test
  void test3() {
    int[][] matrix = new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    int expected = 1;
    int actual = LongestPathInMatrix.findLongestPath(matrix);
    assertEquals(expected, actual);
  }

  @Test
  void test4() {
    int[][] matrix = new int[][] {{4}, {3}, {2}, {1}};
    int expected = 4;
    int actual = LongestPathInMatrix.findLongestPath(matrix);
    assertEquals(expected, actual);
  }
}
