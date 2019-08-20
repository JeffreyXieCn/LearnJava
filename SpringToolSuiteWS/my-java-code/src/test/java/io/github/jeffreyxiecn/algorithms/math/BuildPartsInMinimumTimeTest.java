package io.github.jeffreyxiecn.algorithms.math;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.RepeatedTest;

class BuildPartsInMinimumTimeTest {

  @RepeatedTest(value = 10)
  void test() {
    int size = 1000000;
    List<Integer> parts = new Random().ints(size, 1, size).boxed().collect(Collectors.toList());
    int actual = new BuildPartsInMinimumTime().minimumTime(size, parts);
    System.out.println("Actual:" + actual);
    System.out.println("Integer.MAX_VALUE:" + Integer.MAX_VALUE); // 2.1 billion = 2.1 * 10^9
    System.out.println("Long.MAX_VALUE:" + Long.MAX_VALUE); // 9.2 billion = 9.2 * 10^18
  }
}
