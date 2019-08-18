package io.github.jeffreyxiecn.algorithms.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChangeHouseStatesTest {

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void whenChangeForOneDay_thenReturnCorrectNewState() {
    int[] input = new int[] {1, 0, 0, 0, 0, 1, 0, 0};
    List<Integer> expected = Arrays.asList(0, 1, 0, 0, 1, 0, 1, 0);
    List<Integer> actual = new ChangeHouseStates().cellCompete(input, 1);
    assertEquals(expected, actual);
  }

  @Test
  void whenChangeForTwoDays_thenReturnCorrectNewState() {
    int[] input = new int[] {1, 1, 1, 0, 1, 1, 1, 1};
    List<Integer> expected = Arrays.asList(0, 0, 0, 0, 0, 1, 1, 0);
    List<Integer> actual = new ChangeHouseStates().cellCompete(input, 2);
    assertEquals(expected, actual);
  }
}
