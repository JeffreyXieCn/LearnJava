package io.github.jeffreyxiecn.algorithms.array;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CountCommonsInSortedArrrayTest {

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void whenSomeElementsAreCommon_thenCountCorrectly() {
    int[] arr1 = {13, 27, 35, 40, 49, 55, 59};
    int[] arr2 = {17, 35, 39, 40, 55, 58, 60};
    int counter = CountCommonsInSortedArrray.count(arr1, arr2);
    assertEquals(3, counter);
  }

  @Test
  void whenAllElementsAreCommon_thenCountCorrectly() {
    int[] arr1 = {13, 27, 35, 40, 49, 55, 59};
    int counter = CountCommonsInSortedArrray.count(arr1, arr1);
    assertEquals(arr1.length, counter);
  }

  @Test
  void whenNoElementsAreCommon_thenCountCorrectly() {
    int[] arr1 = {13, 27, 35, 40, 49, 55, 59};
    int[] arr2 = {17, 34, 39, 41, 54, 58, 60};
    int counter = CountCommonsInSortedArrray.count(arr1, arr2);
    assertEquals(0, counter);
  }
}
