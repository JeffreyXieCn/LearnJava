package io.github.jeffreyxiecn.algorithms.string;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RunLengthEncodingTest {
  private static RunLengthEncoding sut;

  @BeforeAll
  public static void init() {
    sut = new RunLengthEncoding();
  }

  @Test
  void test1() {
    String input = "aaaabbccc";
    String expected = "4a2b3c";
    String actual = sut.encode(input);
    assertEquals(expected, actual);
  }

  @Test
  void test2() {
    String input = "abcd";
    String expected = "1a1b1c1d";
    String actual = sut.encode(input);
    assertEquals(expected, actual);
  }

  @Test
  void test3() {
    String input = "aaaa";
    String expected = "4a";
    String actual = sut.encode(input);
    assertEquals(expected, actual);
  }

  @Test
  void test4() {
    String input = "aaaab";
    String expected = "4a1b";
    String actual = sut.encode(input);
    assertEquals(expected, actual);
  }
}
