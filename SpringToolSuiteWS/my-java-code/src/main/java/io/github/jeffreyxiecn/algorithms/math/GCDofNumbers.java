package io.github.jeffreyxiecn.algorithms.math;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GCDofNumbers {
  public static int generalizedGCD(int num, int[] arr) {
    int min = arr[0];
    // Arrays.stream(arr).forEach(i -> {if(i < min) {min = i;}});
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < min) {
        min = arr[i];
      }
    }

    List<Integer> divisors = getDivisors(min);

    for (int j : divisors) {
      int k;
      for (k = 0; k < arr.length; k++) {
        if (arr[k] % j != 0) {
          break;
        }
      }

      if (k >= arr.length) {
        return j;
      }
    }

    return 1;
  }

  private static List<Integer> getDivisors(int num) {
    List<Integer> bigger = new ArrayList<>();
    LinkedList<Integer> smaller = new LinkedList<>();
    int sqrt = (int) Math.sqrt(num);
    int end;
    if (sqrt * sqrt == num) {
      end = sqrt - 1;
    } else {
      end = sqrt;
    }

    for (int i = 1; i <= end; i++) {
      if (num % i == 0) {
        bigger.add(num / i);
        // smaller.add(i);
        smaller.addFirst(i);
      }
    }

    if (end == sqrt - 1) {
      // smaller.add(sqrt); // add it only once
      smaller.addFirst(sqrt); // or bigger.add(sqrt);
    }

    // Collections.reverse(smaller);
    bigger.addAll(smaller);
    return bigger;
  }
}
