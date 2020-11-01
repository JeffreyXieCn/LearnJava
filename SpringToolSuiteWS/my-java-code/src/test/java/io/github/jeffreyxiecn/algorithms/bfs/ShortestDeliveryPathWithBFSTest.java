package io.github.jeffreyxiecn.algorithms.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.github.jeffreyxiecn.algorithms.bfs.ShortestDeliveryPathWithBFS;

class ShortestDeliveryPathWithBFSTest {
  private static ShortestDeliveryPathWithBFS sdp;

  @BeforeAll
  public static void init() {
    sdp = new ShortestDeliveryPathWithBFS();
  }

  @Test
  void whenThereIsOnlyOnePath_thenReturnThePathLength() {
    List<List<Integer>> input =
        Arrays.asList(Arrays.asList(1, 0, 0), Arrays.asList(1, 0, 0), Arrays.asList(1, 9, 1));

    int expected = 3;
    int actual1 = sdp.minimumDistance(input.size(), input.get(0).size(), input);
    assertEquals(expected, actual1);

    int actual2 = sdp.minimumDistance2(input.size(), input.get(0).size(), input);
    assertEquals(expected, actual2);
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
    int actual1 = sdp.minimumDistance(input.size(), input.get(0).size(), input);
    assertEquals(expected, actual1);

    int actual2 = sdp.minimumDistance2(input.size(), input.get(0).size(), input);
    assertEquals(expected, actual2);
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
    int actual1 = sdp.minimumDistance(input.size(), input.get(0).size(), input);
    assertEquals(expected, actual1);

    int actual2 = sdp.minimumDistance2(input.size(), input.get(0).size(), input);
    assertEquals(expected, actual2);
  }
}
