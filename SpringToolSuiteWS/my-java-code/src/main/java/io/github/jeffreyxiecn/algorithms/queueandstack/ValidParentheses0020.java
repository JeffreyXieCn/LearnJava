package io.github.jeffreyxiecn.algorithms.queueandstack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class ValidParentheses0020 {
  public boolean isValid(String s) {
    if (s == null || s.length() == 0) {
      return true;
    }

    Deque<Character> stack = new LinkedList<>();
    for (int i = 0; i < s.length(); i++) {
      if (isOpenBracket(s.charAt(i))) {
        stack.push(s.charAt(i));
      } else {
        // assume it's close bracket
        if (stack.isEmpty()) {
          return false;
        }

        if (!isMatch(stack.pop(), s.charAt(i))) {
          return false;
        }
      }
    }

    return stack.isEmpty();
  }

  private boolean isOpenBracket(char c) {
    return c == '(' || c == '[' || c == '{';
  }

  private boolean isMatch(char c1, char c2) {
    return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
  }

  public boolean isValid2(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
      } else {
        if (stack.isEmpty()) {
          return false;
        }
        char cStack = stack.pop();
        boolean b1 = c == ')' && cStack != '(';
        boolean b2 = c == ']' && cStack != '[';
        boolean b3 = c == '}' && cStack != '{';
        if (b1 || b2 || b3) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
