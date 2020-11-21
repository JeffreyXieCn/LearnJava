package io.github.jeffreyxiecn.algorithms.string;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FirstNonRepeatingCharTest {

  private static String testStr1;
  private static char firstNonRepeatChar1;

  private static String testStr2;
  private static char firstNonRepeatChar2;

  private static String testStr3;
  private static char firstNonRepeatChar3;

  @BeforeAll
  public static void setUp() {
    testStr1 = "ABCDBAGHC";
    firstNonRepeatChar1 = 'D';

    testStr2 = "1230321";
    firstNonRepeatChar2 = '0';

    testStr3 = "ABCHBAGHCG";
    firstNonRepeatChar3 = 0;
  }

  @Test
  void testFirstNonRepeatingChar() {
    assertThat(FirstNonRepeatingChar.firstNonRepeatingChar(testStr1), equalTo(firstNonRepeatChar1));
    assertThat(FirstNonRepeatingChar.firstNonRepeatingChar(testStr2), equalTo(firstNonRepeatChar2));
    assertThat(FirstNonRepeatingChar.firstNonRepeatingChar(testStr3), equalTo(firstNonRepeatChar3));
  }

  @Test
  void testFirstNonRepeatingChar2() {
    assertThat(
        FirstNonRepeatingChar.firstNonRepeatingChar2(testStr1), equalTo(firstNonRepeatChar1));
    assertThat(
        FirstNonRepeatingChar.firstNonRepeatingChar2(testStr2), equalTo(firstNonRepeatChar2));
    assertThat(
        FirstNonRepeatingChar.firstNonRepeatingChar2(testStr3), equalTo(firstNonRepeatChar3));
  }

  @Test
  void testFirstNonRepeatingChar3() {
    assertThat(
        FirstNonRepeatingChar.firstNonRepeatingChar3(testStr1), equalTo(firstNonRepeatChar1));
    assertThat(
        FirstNonRepeatingChar.firstNonRepeatingChar3(testStr2), equalTo(firstNonRepeatChar2));
    assertThat(
        FirstNonRepeatingChar.firstNonRepeatingChar3(testStr3), equalTo(firstNonRepeatChar3));
  }
}
