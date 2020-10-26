package io.github.jeffreyxiecn.algorithms.recursionanddp;

public class HouseRobber0198 {
  public int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    if (nums.length == 1) {
      return nums[0];
    }

    if (nums.length == 2) {
      return Math.max(nums[0], nums[1]);
    }

    int max1 = nums[0];
    int max2 = Math.max(nums[0], nums[1]);
    int max = max2;
    for (int i = 2; i < nums.length; i++) {
      max = Math.max(max1 + nums[i], max2);
      max1 = max2;
      max2 = max;
    }

    return max;
  }

  public int rob2(int[] nums) {
    int pre2 = 0, pre1 = 0;
    for (int num : nums) {
      int cur = Math.max(pre2 + num, pre1);
      pre2 = pre1;
      pre1 = cur;
    }
    return pre1;
  }
}
