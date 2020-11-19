package io.github.jeffreyxiecn.algorithms.string;

public class RunLengthEncoding {
  /**
   * Input: aaaabbccc Output: 4a2b3c
   *
   * @param str
   * @return
   */
  public String encode(String str) {
    if (str == null || str.length() == 0) {
      return "";
    }

    int counter;
    char cur;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str.length(); ) {
      cur = str.charAt(i);
      counter = 1;
      while (++i < str.length() && str.charAt(i) == cur) {
        counter++;
      }
      sb.append(counter).append(cur);
    }

    return sb.toString();
  }
}
