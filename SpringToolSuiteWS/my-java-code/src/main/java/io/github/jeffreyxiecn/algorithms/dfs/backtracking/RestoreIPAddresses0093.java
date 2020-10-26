package io.github.jeffreyxiecn.algorithms.dfs.backtracking;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses0093 {
  public List<String> restoreIpAddresses(String s) {
    List<String> list = new ArrayList<>();
    dfs("", s, 4, list, "");

    return list;
  }

  private void dfs(String prefix, String s, int remain, List<String> list, String indent) {
    System.out.println(indent + "prefix: " + prefix + " s: " + s + " remain:" + remain);
    if (s == null || s.isEmpty() || remain <= 0) {
      if ((s == null || s.isEmpty()) && remain == 0) {
        list.add(prefix);
      }
      return;
    }

    int len;
    if (s.charAt(0) == '0') {
      len = 1;
    } else {
      len = Math.min(3, s.length());
    }
    for (int i = 1; i <= len; i++) {
      String num = s.substring(0, i);
      int intNum = Integer.valueOf(num);
      if (intNum >= 0 && intNum <= 255) {
        String newPrefix;
        if (prefix.isEmpty()) {
          newPrefix = num;
        } else {
          newPrefix = prefix + "." + num;
        }

        dfs(newPrefix, s.substring(i), remain - 1, list, indent + "\t");
      }
    }
  }
}
