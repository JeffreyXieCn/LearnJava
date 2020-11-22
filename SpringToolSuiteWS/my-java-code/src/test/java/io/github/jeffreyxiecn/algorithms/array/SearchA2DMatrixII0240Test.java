package io.github.jeffreyxiecn.algorithms.array;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SearchA2DMatrixII0240Test {

  private static SearchA2DMatrixII0240 sut;
  private static int[][] input = {
    {1, 4, 7, 11, 15},
    {2, 5, 8, 12, 19},
    {3, 6, 9, 16, 22},
    {10, 13, 14, 17, 24},
    {18, 21, 23, 26, 30}
  };

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    sut = new SearchA2DMatrixII0240();
  }

  @Test
  void test() {
    assertTrue(sut.searchMatrix(input, 5));
  }

  @Test
  void test2() {
    assertFalse(sut.searchMatrix(input, 20));
  }

  @Test
  void test3() {
    assertTrue(sut.searchMatrix(input, 1));
  }

  @Test
  void test4() {
    assertTrue(sut.searchMatrix(input, 30));
  }

  @Test
  void test5() {
    assertFalse(sut.searchMatrix(input, 0));
  }

  @Test
  void test6() {
    assertFalse(sut.searchMatrix(input, 31));
  }

  @Test
  void test7() {
    assertFalse(sut.searchMatrix(input, 25));
  }

  @Test
  void test8() {
    assertFalse(sut.searchMatrix(new int[][] {}, 25));
  }

  @Test
  void test9() {
    assertFalse(sut.searchMatrix(new int[][] {{}, {}, {}}, 25));
  }
}
