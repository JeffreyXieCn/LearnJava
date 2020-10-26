package io.github.jeffreyxiecn.algorithms.dfs.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber0017 {
  private static String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

  public List<String> letterCombinations(String digits) {
    List<String> list = new ArrayList<>();
    dfs("", digits, list);

    return list;
  }

  private void dfs(String prefix, String digits, List<String> list) {
    if (digits == null || digits.length() == 0) {
      if (!prefix.isEmpty()) {
        list.add(prefix);
      }
      return;
    }

    String first = keys[digits.charAt(0) - '0'];
    for (int i = 0; i < first.length(); i++) {
      String newPrefix = prefix + String.valueOf(first.charAt(i));
      dfs(newPrefix, digits.substring(1, digits.length()), list);
    }
  }
}
