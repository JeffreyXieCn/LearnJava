package io.github.jeffreyxiecn.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZigZagConversion6 {

  class Triple {
    char c;
    int x, y;

    public Triple(char c, int x, int y) {
      this.c = c;
      this.x = x;
      this.y = y;
    }
  }

  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }

    char[] chars = s.toCharArray();
    int x = 0, y = 0;
    boolean zag = false;
    Triple[] triples = new Triple[chars.length];
    for (int i = 0; i < chars.length; i++) {
      triples[i] = new Triple(chars[i], x, y);
      if (zag) {
        x--;
        y++;
        if (x == 0) {
          zag = false;
        }
      } else {
        x++;
        if (x == numRows - 1) {
          zag = true;
        }
      }
    }

    //    char[] result = new char[chars.length];
    //    int idx = 0;
    //    for (int j = 0; j < numRows; j++) {
    //      for (Triple triple : triples) {
    //        if (triple.x == j) {
    //          result[idx++] = triple.c;
    //        }
    //      }
    //    }
    //    return new String(result);

    StringBuilder[] sbs = new StringBuilder[numRows];
    for (int m = 0; m < sbs.length; m++) {
      sbs[m] = new StringBuilder();
    }
    for (Triple triple : triples) {
      sbs[triple.x].append(triple.c);
    }
    for (int n = 1; n < sbs.length; n++) {
      sbs[0].append(sbs[n]);
    }
    return sbs[0].toString();
  }

  public String convert2(String s, int numRows) {

    if (numRows == 1) {
      return s;
    }

    List<StringBuilder> rows = new ArrayList<>();
    for (int i = 0; i < Math.min(numRows, s.length()); i++) {
      rows.add(new StringBuilder());
    }

    int curRow = 0;
    boolean goingDown = false;

    for (char c : s.toCharArray()) {
      rows.get(curRow).append(c);
      if (curRow == 0 || curRow == numRows - 1) {
        goingDown = !goingDown;
      }
      curRow += goingDown ? 1 : -1;
    }

    StringBuilder ret = new StringBuilder();
    for (StringBuilder row : rows) {
      ret.append(row);
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(new ZigZagConversion6().convert("PAYPALISHIRING", 4));
    System.out.println(new ZigZagConversion6().convert2("PAYPALISHIRING", 4));
    Map<String, String> map = new HashMap<>();
  }
}
