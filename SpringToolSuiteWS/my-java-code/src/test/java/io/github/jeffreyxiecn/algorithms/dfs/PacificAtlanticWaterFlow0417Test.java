package io.github.jeffreyxiecn.algorithms.dfs;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PacificAtlanticWaterFlow0417Test {

  private static PacificAtlanticWaterFlow0417 sut;

  @BeforeAll
  public static void init() {
    sut = new PacificAtlanticWaterFlow0417();
  }

  @Test
  void test() {
    int[][] matrix = {
      {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}
    };

    List<List<Integer>> expect = new ArrayList<>();
    expect.add(Arrays.asList(0, 4));
    expect.add(Arrays.asList(1, 3));
    expect.add(Arrays.asList(1, 4));
    expect.add(Arrays.asList(2, 2));
    expect.add(Arrays.asList(3, 0));
    expect.add(Arrays.asList(3, 1));
    expect.add(Arrays.asList(4, 0));

    List<List<Integer>> actual = sut.pacificAtlantic(matrix);
    // assertArrayEquals(expect.toArray(), actual.toArray());
    assertEquals(expect, actual);

    // int e = 6;
    // int a = 5;
    // assertEquals(e, a); // calls assertEquals(long expected, long actual)
  }
}
