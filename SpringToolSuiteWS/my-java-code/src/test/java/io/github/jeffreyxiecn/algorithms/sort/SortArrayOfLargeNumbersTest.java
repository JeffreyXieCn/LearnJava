package io.github.jeffreyxiecn.algorithms.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class SortArrayOfLargeNumbersTest {

  @Test
  void whenNoDuplicateElements_thenSortCorrectly() {
    String arr[] = {"5", "1237637463746732323", "12"};
    SortArrayOfLargeNumbers.sort(arr);
    String[] expected = {"5", "12", "1237637463746732323"};
    assertArrayEquals(expected, arr);
  }

  @Test
  void whenDuplicateElementsExist_thenSortCorrectly() {
    String arr[] = {"50", "12", "12", "1"};
    SortArrayOfLargeNumbers.sort(arr);
    String[] expected = {"1", "12", "12", "50"};
    assertArrayEquals(expected, arr);
  }
}
