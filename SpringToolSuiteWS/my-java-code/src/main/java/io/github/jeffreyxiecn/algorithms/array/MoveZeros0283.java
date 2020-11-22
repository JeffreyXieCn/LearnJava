package io.github.jeffreyxiecn.algorithms.array;

public class MoveZeros0283 {
  /**
   * fast-slow pointers approach
   *
   * @param nums
   */
  public void moveZeroes(int[] nums) {
    int slow = -1;
    int fast = 0;
    while (fast < nums.length) {
      if (nums[fast] != 0) {
        nums[++slow] = nums[fast];
      }
      fast++;
    }

    while (slow < nums.length - 1) {
      nums[++slow] = 0;
    }
  }

  public void moveZeroes2(int[] nums) {
    int idx = 0;
    for (int num : nums) {
      if (num != 0) {
        nums[idx++] = num;
      }
    }
    while (idx < nums.length) {
      nums[idx++] = 0;
    }
  }
}
