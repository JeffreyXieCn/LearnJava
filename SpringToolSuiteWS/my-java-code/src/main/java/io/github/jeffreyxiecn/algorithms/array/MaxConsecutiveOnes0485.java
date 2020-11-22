package io.github.jeffreyxiecn.algorithms.array;

public class MaxConsecutiveOnes0485 {
  public int findMaxConsecutiveOnes(int[] nums) {
    int max = 0;
    int counter = 0;
    for (int num : nums) {
      if (num == 0) {
        if (counter > max) {
          max = counter;
        }
        counter = 0;
      } else {
        counter++;
      }
    }

    if (counter > max) {
      max = counter;
    }

    return max;
  }

  public int findMaxConsecutiveOnes2(int[] nums) {
    int max = 0, cur = 0;
    for (int x : nums) {
      cur = x == 0 ? 0 : cur + 1;
      max = Math.max(max, cur);
    }
    return max;
  }
}
