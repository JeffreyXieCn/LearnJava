package io.github.jeffreyxiecn.algorithms.string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReverseWordsInAStringTest {
  private static ReverseWordsInAString sut;

  @BeforeAll
  public static void init() {
    sut = new ReverseWordsInAString();
  }

  @Test
  void test1() {
    String input = "I am a student";
    String expected = "student a am I";
    String actual = sut.reverseWords(input);
    assertEquals(expected, actual);
  }

  @Test
  void test2() {
    String input = "I  am a   student";
    String expected = "student   a am  I";
    String actual = sut.reverseWords(input);
    assertEquals(expected, actual);
  }

  @Test
  void test3() {
    String input = "   I  am\ta   student   ";
    String expected = "   student   a\tam  I   ";
    String actual = sut.reverseWords(input);
    assertEquals(expected, actual);
  }

  @Test
  void test4() {
    String input = "";
    String expected = "";
    String actual = sut.reverseWords(input);
    assertEquals(expected, actual);
  }

  @Test
  void test5() {
    String input = " ";
    String expected = " ";
    String actual = sut.reverseWords(input);
    assertEquals(expected, actual);
  }

  @Test
  void test6() {
    String input = "   I";
    String expected = "I   ";
    String actual = sut.reverseWords(input);
    assertEquals(expected, actual);
  }

  @Test
  void test7() {
    String input = "I   ";
    String expected = "   I";
    String actual = sut.reverseWords(input);
    assertEquals(expected, actual);
  }
}
