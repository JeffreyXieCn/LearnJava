package io.github.jeffreyxiecn.algorithms.recursionanddp;

import java.util.ArrayList;
import java.util.List;

public class PermutationGenerator {

  public static void main(String[] args) {
    System.out.println(permutationOf("ab"));
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
  }

  public static List<String> permutationOf(String input) {
    List<String> result = new ArrayList<>();
    generatePermutations("", input, input.length(), result);
    return result;
  }

  public static List<String> permutationOfK(String input, int k) {
    List<String> result = new ArrayList<>();
    generatePermutations("", input, k, result);
    return result;
  }

  private static void generatePermutations(String prefix, String str, int k, List<String> result) {
    if (prefix.length() == k) {
      result.add(prefix);
      return;
    }
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      String remain = str.substring(0, i) + str.substring(i + 1);
      generatePermutations(prefix + ch, remain, k, result);
    }
  }

  public static List<String> subsetOfK(String input, int k) {
    List<String> result = new ArrayList<>();
    generateSubsets("", input, k, result);
    return result;
  }

  private static void generateSubsets(String prefix, String str, int k, List<String> result) {
    if (prefix.length() == k) {
      result.add(prefix);
      return;
    }

    int end = prefix.length() + str.length() - k;
    for (int i = 0; i <= end; i++) {
      char ch = str.charAt(i);
      String remain = str.substring(i + 1);
      generateSubsets(prefix + ch, remain, k, result);
    }
  }
}
