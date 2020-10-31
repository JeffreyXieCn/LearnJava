package io.github.jeffreyxiecn.algorithms.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FindMaxDiffTest {

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @Test
  void test1() {
    int[] input = {43, 54, 41, 43, 48, 53, 40};
    int expect = 12;

    int result1 = FindMaxDiff.findMaxDiff(input);
    int result2 = FindMaxDiff.findMaxDiff2(input);
    assertEquals(expect, result1);
    assertEquals(expect, result2);
  }

  @Test
  void test2() {
    int[] input = {43, 40};
    int expect = -1;

    int result1 = FindMaxDiff.findMaxDiff(input);
    int result2 = FindMaxDiff.findMaxDiff2(input);
    assertEquals(expect, result1);
    assertEquals(expect, result2);
  }

  @Test
  void test3() {
    int cases = 100;
    int range = 1000;
    // Random rand = new Random();
    for (int i = 0; i < cases; i++) {
      int num = (int) (Math.random() * cases + 1);
      int[] input = new int[num];
      for (int j = 0; j < num; j++) {
        input[j] = (int) (Math.random() * range + 1);
      }

      int result1 = FindMaxDiff.findMaxDiff(input);
      int result2 = FindMaxDiff.findMaxDiff2(input);
      if (result1 != result2) {
        System.out.println("input[]: " + Arrays.toString(input));
        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);
      }
      assertEquals(result2, result1);
    }
  }
}
