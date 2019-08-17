package io.github.jeffreyxiecn.algorithms.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OptimizeMemoryUtilizationTest {
  private static OptimizeMemoryUtilization optimizer;

  @BeforeAll
  static void setUp() throws Exception {
    optimizer = new OptimizeMemoryUtilization();
  }

  @Test
  void whenOnlyOnePairExists_thenFindOne() {
    int deviceCapacity = 7;
    List<List<Integer>> foregroundAppList =
        Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 4), Arrays.asList(3, 6));
    List<List<Integer>> backgroundAppList = Arrays.asList(Arrays.asList(1, 2));
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(2, 1));

    List<List<Integer>> actual =
        optimizer.optimalUtilization(deviceCapacity, foregroundAppList, backgroundAppList);
    // assertEquals(expected, actual);

    // assert contents are equal, regardless of the order
    assertEquals(expected.size(), actual.size());
    assertTrue(expected.containsAll(actual));
  }

  @Test
  void whenTwoPairsExist_thenFindTwo() {
    int deviceCapacity = 10;
    List<List<Integer>> foregroundAppList =
        Arrays.asList(
            Arrays.asList(1, 3), Arrays.asList(2, 5), Arrays.asList(3, 7), Arrays.asList(4, 10));
    List<List<Integer>> backgroundAppList =
        Arrays.asList(
            Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4), Arrays.asList(4, 5));
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(2, 4), Arrays.asList(3, 2));

    List<List<Integer>> actual =
        optimizer.optimalUtilization(deviceCapacity, foregroundAppList, backgroundAppList);
    // assertEquals(expected, actual);

    // assert contents are equal, regardless of the order
    assertEquals(expected.size(), actual.size());
    assertTrue(expected.containsAll(actual));
  }
}
