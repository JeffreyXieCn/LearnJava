package io.github.jeffreyxiecn.algorithms.divideandconquer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentWaysToAddParentheses0241 {
  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> result = new ArrayList<>();

    boolean hasOp = false;
    for (int i = 0; i < input.length(); i++) {
      if (isOperator(input.charAt(i))) {
        hasOp = true;
        List<Integer> left = diffWaysToCompute(input.substring(0, i));
        List<Integer> right = diffWaysToCompute(input.substring(i + 1, input.length()));
        List<Integer> tempResult = computeAll(left, input.charAt(i), right);
        result.addAll(tempResult);
      }
    }

    if (!hasOp) {
      // base case, just a number
      result.add(Integer.valueOf(input));
    }

    return result;
  }

  private boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*';
  }

  private List<Integer> computeAll(List<Integer> left, char c, List<Integer> right) {
    List<Integer> result = new ArrayList<>();
    for (int curLeft : left) {
      for (int curRight : right) {
        result.add(compute(curLeft, c, curRight));
      }
    }
    return result;
  }

  private int compute(int left, char c, int right) {
    switch (c) {
      case '+':
        return left + right;
      case '-':
        return left - right;
      case '*':
        return left * right;
    }

    return 0;
  }

  private Map<String, List<Integer>> cache = new HashMap<>();

  public List<Integer> diffWaysToCompute2(String input) { // with DP
    List<Integer> result = new ArrayList<>();

    boolean hasOp = false;
    for (int i = 0; i < input.length(); i++) {
      if (isOperator(input.charAt(i))) {
        hasOp = true;
        String leftStr = input.substring(0, i);
        List<Integer> left;
        if (cache.containsKey(leftStr)) {
          left = cache.get(leftStr);
        } else {
          left = diffWaysToCompute(leftStr);
          cache.put(leftStr, left);
        }

        // String rightStr = input.substring(i + 1, input.length());
        String rightStr = input.substring(i + 1);
        List<Integer> right;
        if (cache.containsKey(rightStr)) {
          right = cache.get(rightStr);
        } else {
          right = diffWaysToCompute(rightStr);
          cache.put(rightStr, right);
        }

        List<Integer> tempResult = computeAll(left, input.charAt(i), right);
        result.addAll(tempResult);
      }
    }

    if (!hasOp) {
      // base case, just a number
      result.add(Integer.valueOf(input));
    }

    return result;
  }

  public List<Integer> diffWaysToCompute3(String input) {
    List<Integer> ways = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (c == '+' || c == '-' || c == '*') {
        List<Integer> left = diffWaysToCompute(input.substring(0, i));
        List<Integer> right = diffWaysToCompute(input.substring(i + 1));
        for (int l : left) {
          for (int r : right) {
            switch (c) {
              case '+':
                ways.add(l + r);
                break;
              case '-':
                ways.add(l - r);
                break;
              case '*':
                ways.add(l * r);
                break;
            }
          }
        }
      }
    }
    if (ways.size() == 0) {
      ways.add(Integer.valueOf(input));
    }
    return ways;
  }
}
