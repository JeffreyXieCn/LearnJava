package io.github.jeffreyxiecn.algorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ShortestDeliveryPathWithBFSTest {

  @Test
  void whenThereIsOnlyOnePath_thenReturnThePathLength() {
    List<List<Integer>> input =
        Arrays.asList(Arrays.asList(1, 0, 0), Arrays.asList(1, 0, 0), Arrays.asList(1, 9, 1));

    int expected = 3;
    int actual =
        new ShortestDeliveryPathWithBFS().minimumDistance(input.size(), input.get(0).size(), input);
    assertEquals(expected, actual);
  }

  @Test
  void whenThereAreMutiplePaths_thenReturnTheShortestPathLength() {
    List<List<Integer>> input =
        Arrays.asList(
            Arrays.asList(1, 0, 1, 0),
            Arrays.asList(1, 1, 1, 1),
            Arrays.asList(1, 0, 0, 1),
            Arrays.asList(1, 1, 9, 1));

    int expected = 5;
    int actual =
        new ShortestDeliveryPathWithBFS().minimumDistance(input.size(), input.get(0).size(), input);
    assertEquals(expected, actual);
  }

  @Test
  void whenThereIsOneTwistedPath_thenReturnThePathLength() {
    List<List<Integer>> input =
        Arrays.asList(
            Arrays.asList(1, 0, 1, 9, 0),
            Arrays.asList(1, 0, 1, 0, 0),
            Arrays.asList(1, 0, 1, 1, 0),
            Arrays.asList(1, 0, 0, 1, 0),
            Arrays.asList(1, 1, 1, 1, 0));

    int expected = 13;
    int actual =
        new ShortestDeliveryPathWithBFS().minimumDistance(input.size(), input.get(0).size(), input);
    assertEquals(expected, actual);
  }
}
