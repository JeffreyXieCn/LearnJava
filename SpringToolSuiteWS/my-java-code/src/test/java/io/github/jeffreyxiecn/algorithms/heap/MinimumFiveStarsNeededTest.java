package io.github.jeffreyxiecn.algorithms.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class MinimumFiveStarsNeededTest {

  @Test
  void test() {
    List<List<Integer>> input = new ArrayList<>();
    input.add(Arrays.asList(4, 4));
    input.add(Arrays.asList(1, 2));
    input.add(Arrays.asList(3, 6));

    int expected = 3;
    int actual = MinimumFiveStarsNeeded.minFiveStarsNeeded(input, 77);

    assertEquals(expected, actual);
  }

  @Test
  void test2() {
    List<List<Integer>> input = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      input.add(Arrays.asList(0, 1));
    }
    //    input.add(Arrays.asList(0, 1));
    //    input.add(Arrays.asList(0, 1));
    //    input.add(Arrays.asList(0, 1));
    //    input.add(Arrays.asList(0, 1));

    int expected = 30010;
    int actual = MinimumFiveStarsNeeded.minFiveStarsNeeded(input, 75);

    assertEquals(expected, actual);
  }
}
