package io.github.jeffreyxiecn.algorithms.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GCDofNumbersTest {

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void whenGCDisNotOne_thenReturnCorrectGCD() {
    int[] arr = {2, 4, 6, 8, 10};
    int expected = 2;
    int actual = GCDofNumbers.generalizedGCD(arr.length, arr);
    assertEquals(expected, actual);
  }

  @Test
  void whenGCDisOne_thenReturnOne() {
    int[] arr = {2, 3, 4, 5, 6};
    int expected = 1;
    int actual = GCDofNumbers.generalizedGCD(arr.length, arr);
    assertEquals(expected, actual);
  }

  @Test
  void whenGCDisNotOne_thenReturnCorrectGCD2() {
    int[] arr = {2, 4, 6, 8, 10};
    int expected = 2;
    int actual = GCDofNumbers.generalizedGCD2(arr.length, arr);
    assertEquals(expected, actual);
  }

  @Test
  void whenGCDisOne_thenReturnOne2() {
    int[] arr = {2, 3, 4, 5, 6};
    int expected = 1;
    int actual = GCDofNumbers.generalizedGCD2(arr.length, arr);
    assertEquals(expected, actual);
  }

  @Test
  void whenGCDisBiggerDivisor_thenReturnCorrectGCD() {
    int[] arr = {20, 30, 10, 50, 25};
    int expected = 5;
    int actual = GCDofNumbers.generalizedGCD2(arr.length, arr);
    assertEquals(expected, actual);
  }

  @Test
  void whenGCDisSmallerDivisor_thenReturnCorrectGCD() {
    int[] arr = {20, 30, 10, 50, 24};
    int expected = 2;
    int actual = GCDofNumbers.generalizedGCD2(arr.length, arr);
    assertEquals(expected, actual);
  }
}