package io.github.jeffreyxiecn.string;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringManipulationTest {
  private String original;
  private String expected;

  private String testStr1;
  private char firstNonRepeatChar1;
  private String testStr2;
  private char firstNonRepeatChar2;
  private String testStr3;
  private char firstNonRepeatChar3;

  @BeforeEach
  public void setUp() {
    original = "Java   is cool";
    expected = "cool is   Java";
    // System.out.println("@Before - runBeforeTestMethod");
    testStr1 = "ABCDBAGHC";
    firstNonRepeatChar1 = 'D';

    testStr2 = "1230321";
    firstNonRepeatChar2 = '0';

    testStr3 = "ABCHBAGHCG";
    firstNonRepeatChar3 = 0;
  }

  @Test
  void testReverseWords() {
    // System.out.println("Enter testReverseWords()");
    String reversed = StringManipulation.reverseWords(original);
    assertThat(reversed, equalTo(expected));
  }

  @Test
  void testReverseWords2() {
    String reversed = StringManipulation.reverseWords2(original);
    assertThat(reversed, equalTo(expected));
  }

  @Test
  void testReverseWords3() {
    String reversed = StringManipulation.reverseWords3(original);
    assertThat(reversed, equalTo(expected));
  }

  @Test
  void testReverseWords4() {
    String reversed = StringManipulation.reverseWords4(original);
    assertThat(reversed, equalTo(expected));
  }

  @Test
  void testFirstNonRepeatingChar() {
    assertThat(StringManipulation.firstNonRepeatingChar(testStr1), equalTo(firstNonRepeatChar1));
    assertThat(StringManipulation.firstNonRepeatingChar(testStr2), equalTo(firstNonRepeatChar2));
    assertThat(StringManipulation.firstNonRepeatingChar(testStr3), equalTo(firstNonRepeatChar3));
  }

  @Test
  void testFirstNonRepeatingChar2() {
    assertThat(StringManipulation.firstNonRepeatingChar2(testStr1), equalTo(firstNonRepeatChar1));
    assertThat(StringManipulation.firstNonRepeatingChar2(testStr2), equalTo(firstNonRepeatChar2));
    assertThat(StringManipulation.firstNonRepeatingChar2(testStr3), equalTo(firstNonRepeatChar3));
  }
}
