package io.github.jeffreyxiecn.algorithms.dfs.backtracking;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LetterCombinationsOfPhoneNumber0017Test {
  private static LetterCombinationsOfPhoneNumber0017 sut;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    sut = new LetterCombinationsOfPhoneNumber0017();
  }

  @Test
  void testLetterCombinations() {
    String phoneNumber = "23";
    Set<String> expected =
        new HashSet<>(Arrays.asList("ad", "ae", "af", "bd", "bf", "cd", "be", "ce", "cf"));
    // new HashSet<>(Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));

    List<String> result = sut.letterCombinations(phoneNumber);
    Set<String> actual = new HashSet<>(result);
    assertEquals(expected, actual);
  }
}
