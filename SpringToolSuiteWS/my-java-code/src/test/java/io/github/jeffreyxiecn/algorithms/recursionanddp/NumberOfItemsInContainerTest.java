package io.github.jeffreyxiecn.algorithms.recursionanddp;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class NumberOfItemsInContainerTest {

  @Test
  void test1() {
    String input = "|**|*|*";
    int[] startIndexes = {1, 1};
    int[] endIndexes = {5, 6};

    List<Integer> expected = Arrays.asList(2, 3);
    List<Integer> actual =
        NumberOfItemsInContainer.numberOfItemsInClosedContainers(input, startIndexes, endIndexes);

    assertEquals(expected, actual);
  }

  @Test
  void test2() {
    String input = "**|**|*|****|***|***";
    int[] startIndexes = {1, 1, 3, 1, 18, 1, 1};
    int[] endIndexes = {5, 6, 6, 20, 20, 1, 2};

    List<Integer> expected = Arrays.asList(0, 2, 2, 10, 0, 0, 0);
    List<Integer> actual =
        NumberOfItemsInContainer.numberOfItemsInClosedContainers(input, startIndexes, endIndexes);

    assertEquals(expected, actual);
  }
}
