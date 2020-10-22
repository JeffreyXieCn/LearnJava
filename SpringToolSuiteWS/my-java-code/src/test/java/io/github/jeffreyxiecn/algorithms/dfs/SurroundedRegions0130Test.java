package io.github.jeffreyxiecn.algorithms.dfs;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class SurroundedRegions0130Test {

  @Test
  void test() {
    char[][] input = {
      {'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}
    };

    char[][] expect = {
      {'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X'}
    };

    SurroundedRegions0130.solve(input);
    // assertEquals(expect, input); // doesn't work

    assertTrue(Arrays.deepEquals(expect, input));

    // it works with  Object array or array of arrays (multi-dimensional array)
    assertArrayEquals(expect, input);
  }
}
