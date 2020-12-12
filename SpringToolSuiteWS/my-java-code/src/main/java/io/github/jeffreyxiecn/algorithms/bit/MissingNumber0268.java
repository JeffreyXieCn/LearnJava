package io.github.jeffreyxiecn.algorithms.bit;

public class MissingNumber0268 {
  // this is almost the same question as 0136
  public int missingNumber(int[] nums) {
    int ret = 0;
    for (int i = 0; i < nums.length; i++) {
      ret = ret ^ i ^ nums[i];
    }
    return ret ^ nums.length;
  }

  public int missingNumber2(int[] nums) {
    boolean[] exist = new boolean[nums.length + 1];
    for (int num : nums) {
      exist[num] = true;
    }

    for (int i = 0; i < exist.length; i++) {
      if (!exist[i]) {
        return i;
      }
    }

    return -1;
  }

  public int missingNumber3(int[] nums) {
    int n = nums.length;
    int sum = (1 + n) * n / 2;
    for (int num : nums) {
      sum -= num;
    }

    return sum;
  }
}
