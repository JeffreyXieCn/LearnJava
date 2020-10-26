package io.github.jeffreyxiecn.algorithms.dfs.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RestoreIPAddresses0093Test {

  private static RestoreIPAddresses0093 sut;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    sut = new RestoreIPAddresses0093();
  }

  @Test
  void testRestoreIpAddresses1() {
    String input = "25525511135";
    List<String> expected = Arrays.asList("255.255.11.135", "255.255.111.35");
    List<String> actual = sut.restoreIpAddresses(input);
    assertEquals(expected, actual);
  }

  @Test
  void testRestoreIpAddresses2() {
    String input = "0000";
    List<String> expected = Arrays.asList("0.0.0.0");
    List<String> actual = sut.restoreIpAddresses(input);
    assertEquals(expected, actual);
  }

  @Test
  void testRestoreIpAddresses3() {
    String input = "101023";
    List<String> expected =
        Arrays.asList("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3");
    List<String> actual = sut.restoreIpAddresses(input);
    assertEquals(expected, actual);
  }
}
