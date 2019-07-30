package io.github.jeffreyxiecn.algorithms.recursionanddp;

import java.util.ArrayList;
import java.util.List;

public class PermutationGenerator {

  public static void main(String[] args) {
    System.out.println(permutationOf("ab"));
    System.out.println(permutationOf("abc"));
    System.out.println(permutationOf("abcd")); // 4! = 4 * 3 * 2 * 1 = 24
    System.out.println(permutationOf("abcde")); // 5! = 5 * 4 * 3 * 2 * 1 = 120

    System.out.println("==================");
    System.out.println(permutationOfK("ab", 1));
    System.out.println(permutationOfK("abc", 2));
    System.out.println(permutationOfK("abcd", 2)); // N4K2 = 4 * 3 = 12
    System.out.println(permutationOfK("abcde", 3)); // N5K3 = 5 * 4 * 3 = 60

    System.out.println("==================");
    System.out.println(subsetOfK("ab", 1));
    System.out.println(subsetOfK2("ab", 1));
    System.out.println(subsetOfK("abc", 2));
    System.out.println(subsetOfK2("abc", 2));
    System.out.println(subsetOfK("abcd", 2));
    System.out.println(subsetOfK2("abcd", 2));
    System.out.println(subsetOfK("abcde", 3));
    System.out.println(subsetOfK2("abcde", 3));
    System.out.println(subsetOfK("abcde", 5));
    System.out.println(subsetOfK2("abcde", 5));
  }

  public static List<String> permutationOf(String input) {
    List<String> result = new ArrayList<>();
    generatePermutations("", input, input.length(), result);
    return result;
  }

  /**
   * P(abcd, 2) = aP(bcd, 1) + bP(acd, 1) + cP(abd, 1) + dP(abc, 1)
   *
   * @param input
   * @param k
   * @return
   */
  public static List<String> permutationOfK(String input, int k) {
    List<String> result = new ArrayList<>();
    generatePermutations("", input, k, result);
    return result;
  }

  /**
   * P(abc) = aP(bc) + bP(ac) + cP(ab) = abP(c) + acP(b) + baP(c) + bcP(a) + caP(b) + cbP(a)
   *
   * @param prefix
   * @param str
   * @param k
   * @param result
   */
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

  /**
   * S(abcd, 2) = aS(bcd, 1) + bS(cd, 1) + cS(d, 1) S(abcde, 3) = aS(bcde, 2) + bS(cde, 2) + cS(de,
   * 2)
   *
   * @param input
   * @param k
   * @return
   */
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

  public static List<String> subsetOfK2(String input, int k) {
    List<String> result = new ArrayList<>();
    generateSubsets2("", input, k, result);
    return result;
  }

  private static void generateSubsets2(String prefix, String str, int k, List<String> result) {
    if (k == 0) {
      result.add(prefix);
      return;
    }

    int end = str.length() - k;
    for (int i = 0; i <= end; i++) {
      char ch = str.charAt(i);
      String remain = str.substring(i + 1);
      generateSubsets2(prefix + ch, remain, k - 1, result); // can't replace k - 1 with --k
    }
  }
}
