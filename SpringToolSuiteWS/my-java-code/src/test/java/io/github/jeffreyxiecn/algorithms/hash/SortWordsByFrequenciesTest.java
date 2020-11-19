package io.github.jeffreyxiecn.algorithms.hash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.github.jeffreyxiecn.algorithms.hash.SortWordsByFrequencies;

class SortWordsByFrequenciesTest {
  private static String input;
  private static Map<Integer, Set<String>> expected;

  @BeforeAll
  static void init() {
    input =
        "Since space is a huge problem, we try different things to reduce the space. One such "
            + "solution is to use jagged array when we know the length of each row in the array."
            + "we will we will we will rock you!";

    expected = new LinkedHashMap<>();
    addSetOfWordsToMap(expected, 5, "we");
    addSetOfWordsToMap(expected, 3, "the", "will");
    // the order can be different
    addSetOfWordsToMap(expected, 2, "space", "is", "to", "array");
    addSetOfWordsToMap(
        expected,
        1,
        "Since",
        "reduce",
        "a",
        "in",
        "use",
        "One",
        "length",
        "when",
        "each",
        "such",
        "problem",
        "solution",
        "of",
        "things",
        "know",
        "huge",
        "try",
        "row",
        "different",
        "jagged",
        "rock",
        "you");
  }

  @Test
  void countingSortTest() {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
    System.out.println(methodName);
    Map<Integer, Set<String>> actual = SortWordsByFrequencies.countingSort(input);
    for (Entry<Integer, Set<String>> entry : actual.entrySet()) {
      System.out.println(entry.getKey() + "\t" + entry.getValue());
    }
    // the equals method overridden in map just works :-)
    assertEquals(expected, actual);

    //    actual.equals(expected);
    //    actual.entrySet().equals(expected.entrySet());
    //    actual.entrySet().contains(o);
    //    for(Entry<Integer, Set<String>> entry: actual.entrySet()) {
    //      entry.equals(o);
    //    }
  }

  @Test
  void sortWithComparatorToMap() {
    String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
    System.out.println(methodName);
    Map<Integer, Set<String>> actual = SortWordsByFrequencies.sortWithComparatorToMap(input);
    for (Entry<Integer, Set<String>> entry : actual.entrySet()) {
      System.out.println(entry.getKey() + "\t" + entry.getValue());
    }
    // the equals method overridden in map just works :-)
    assertEquals(expected, actual);
  }

  private static void addSetOfWordsToMap(
      Map<Integer, Set<String>> map, int frequency, String... words) {
    map.put(frequency, new HashSet<>(Arrays.asList(words)));
  }
}
