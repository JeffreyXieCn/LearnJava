package io.github.jeffreyxiecn.algorithms.array;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WaterStorageTest {
  private static WaterStorage sut;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    sut = new WaterStorage();
  }

  @Test
  void test1() {
    List<Integer> input = Arrays.asList(3, 0, 2, 0, 4);
    int expected = 7;
    int actual = sut.storeWater(input);
    assertEquals(expected, actual);

    actual = sut.storeWater2(input);
    assertEquals(expected, actual);
  }

  @Test
  void test2() {
    List<Integer> input = Arrays.asList(0, 0, 3, 0, 0, 0, 4, 0, 2, 0, 0, 5, 0, 0, 4, 0, 2);
    int expected = 33;
    int actual = sut.storeWater(input);
    assertEquals(expected, actual);

    actual = sut.storeWater2(input);
    assertEquals(expected, actual);
  }

  @Test
  void test3() {
    List<Integer> input = Arrays.asList(0, 0, 3, 0, 0);
    int expected = 0;
    int actual = sut.storeWater(input);
    assertEquals(expected, actual);

    actual = sut.storeWater2(input);
    assertEquals(expected, actual);
  }

  @Test
  void test4() {
    List<Integer> input = Arrays.asList(0);
    int expected = 0;
    int actual = sut.storeWater(input);
    assertEquals(expected, actual);

    actual = sut.storeWater2(input);
    assertEquals(expected, actual);
  }

  @Test
  void test5() {
    List<Integer> input = Arrays.asList(0, 0, 0, 0);
    int expected = 0;
    int actual = sut.storeWater(input);
    assertEquals(expected, actual);

    actual = sut.storeWater2(input);
    assertEquals(expected, actual);
  }

  @Test
  void test6() {
    List<Integer> input = Arrays.asList(4, 4, 4, 4);
    int expected = 0;
    int actual = sut.storeWater(input);
    assertEquals(expected, actual);

    actual = sut.storeWater2(input);
    assertEquals(expected, actual);
  }

  @Test
  void test7() {
    List<Integer> input = Arrays.asList(4, 0, 0, 4);
    int expected = 8;
    int actual = sut.storeWater(input);
    assertEquals(expected, actual);

    actual = sut.storeWater2(input);
    assertEquals(expected, actual);
  }

  @Test
  void test8() {
    List<Integer> input = Arrays.asList(4, 0, 0, 4, 0, 0, 0, 0);
    int expected = 8;
    int actual = sut.storeWater(input);
    assertEquals(expected, actual);

    actual = sut.storeWater2(input);
    assertEquals(expected, actual);
  }

  @Test
  void test9() {
    List<Integer> input = Arrays.asList(3, 4, 5);
    int expected = 0;
    int actual = sut.storeWater(input);
    assertEquals(expected, actual);

    actual = sut.storeWater2(input);
    assertEquals(expected, actual);
  }
}
