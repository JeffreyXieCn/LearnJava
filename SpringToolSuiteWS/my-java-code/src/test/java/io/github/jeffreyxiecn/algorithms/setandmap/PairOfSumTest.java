package io.github.jeffreyxiecn.algorithms.setandmap;

import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PairOfSumTest {

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void whenSomePairsExist_thenFindThem() {
    int[] arr = {5, 12, 7, 15, 8, 9, 11, 10};
    Integer[][] expect = new Integer[][] {{5, 15}, {12, 8}, {9, 11}};
    List<Integer[]> actual = PairOfSum.findPairWithSum(arr, 20);
    Integer[][] actualArr = new Integer[actual.size()][];
    actual.toArray(actualArr);
    assertTrue(Arrays.deepEquals(expect, actualArr));
  }

  @Test
  void whenSomePairsExist_thenFindPairs() {
    int[] arr = {5, 12, 7, 15, 8, 9, 11, 10};
    List<Pair<Integer, Integer>> expect =
        Arrays.asList(Pair.with(5, 15), Pair.with(12, 8), Pair.with(9, 11));
    List<Pair<Integer, Integer>> actual = PairOfSum.getPairsWithSum(arr, 20);
    // Pair inherits the overridden Tuple.equals method
    assertEquals(expect, actual);
  }

  @Test
  void whenSomePairsExist_thenFindIndex() {
    int[] arr = {5, 12, 7, 15, 8, 9, 11, 10};
    Integer[][] expect = new Integer[][] {{0, 3}, {1, 4}, {5, 6}};
    List<Integer[]> actual = PairOfSum.findIndexOfPairWithSum(arr, 20);
    Integer[][] actualArr = new Integer[actual.size()][];
    actual.toArray(actualArr);
    assertTrue(Arrays.deepEquals(expect, actualArr));
  }

  @Test
  void whenNoPairExist_thenReturnEmpty() {
    int[] arr = {5, 12, 7, 15, 8, 9, 11, 10};
    List<Integer[]> actual = PairOfSum.findPairWithSum(arr, 50);
    assertThat(actual, empty());
  }

  @Test
  void whenNoPairExist_thenNoIndexFound() {
    int[] arr = {5, 12, 7, 15, 8, 9, 11, 10};
    List<Integer[]> actual = PairOfSum.findIndexOfPairWithSum(arr, 50);
    assertThat(actual, empty());
  }
}
