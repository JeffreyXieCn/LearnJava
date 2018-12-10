package io.github.jeffreyxiecn.algorithms.leetcode;

public class RemoveDuplicatesfromSortedArray {

  public static int removeDuplicates(int[] nums) {
    int nonDup = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[nonDup - 1]) {
        nums[nonDup] = nums[i];
        nonDup++;
      }
    }

    return nonDup;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
}
