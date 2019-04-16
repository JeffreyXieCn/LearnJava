package io.github.jeffreyxiecn.algorithms.recursionanddp;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PermutationGeneratorTest {

  @Test
  void testPermutationOf() {
    String input = "ab";
    List<String> expected = Arrays.asList("ab", "ba");
    List<String> result = PermutationGenerator.permutationOf(input);
    assertEquals(expected, result);

    // better, don't enforce the order
    String[] expectedArr = new String[] {"ba", "ab"};
    assertThat(result, hasSize(expectedArr.length));
    assertThat(result, containsInAnyOrder(expectedArr));
    /*    System.out.println(permutationOf("ab"));
    System.out.println(permutationOf("abc"));
    System.out.println(permutationOf("abcd"));
    System.out.println(permutationOf("abcde"));

    System.out.println("==================");
    System.out.println(permutationOfK("ab", 1));
    System.out.println(permutationOfK("abc", 2));
    System.out.println(permutationOfK("abcd", 2));
    System.out.println(permutationOfK("abcde", 3));

    System.out.println("==================");
    System.out.println(subsetOfK("ab", 1));
    System.out.println(subsetOfK("abc", 2));
    System.out.println(subsetOfK("abcd", 2));
    System.out.println(subsetOfK("abcde", 3));
    System.out.println(subsetOfK("abcde", 5));
    fail("Not yet implemented");*/
  }

  @Test
  void testPermutationOfK() {

    String input = "abcd";
    // better, don't enforce the order
    String[] expectedArr =
        new String[] {"ab", "ac", "ad", "ba", "bc", "bd", "ca", "cb", "cd", "da", "db", "dc"};
    List<String> result = PermutationGenerator.permutationOfK(input, 2);
    assertThat(result, hasSize(expectedArr.length));
    assertThat(result, containsInAnyOrder(expectedArr));
  }

  @Test
  void testSubsetOfK() {
    String input = "abcd";
    // better, don't enforce the order
    String[] expectedArr = new String[] {"ab", "ac", "ad", "bc", "bd", "cd"};
    List<String> result = PermutationGenerator.subsetOfK(input, 2);
    assertThat(result, hasSize(expectedArr.length));
    assertThat(result, containsInAnyOrder(expectedArr));
  }

  @Test
  void testSubsetOfK_kEqualsLength() {
    String input = "abcde";
    // better, don't enforce the order
    String[] expectedArr = new String[] {"abcde"};
    List<String> result = PermutationGenerator.subsetOfK(input, 5);
    assertThat(result, hasSize(expectedArr.length));
    assertThat(result, containsInAnyOrder(expectedArr));
  }
}
