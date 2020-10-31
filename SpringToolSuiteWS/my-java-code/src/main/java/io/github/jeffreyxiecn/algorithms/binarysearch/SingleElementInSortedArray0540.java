package io.github.jeffreyxiecn.algorithms.binarysearch;

public class SingleElementInSortedArray0540 {
  public int singleNonDuplicate(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }

    int l = 0, h = n - 1;
    int m = 0;
    while (l <= h) {
      m = l + (h - l) / 2;
      if ((m == 0) || (m == n - 1) || (nums[m] != nums[m - 1] && nums[m] != nums[m + 1])) {
        return nums[m];
      } else if (nums[m] == nums[m - 1]) {
        if (m % 2 == 0) {
          h = m - 2;
        } else {
          l = m + 1;
        }
      } else {
        if (m % 2 == 0) {
          l = m + 2;
        } else {
          h = m - 1;
        }
      }
    }

    return nums[m];
  }

  // https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.md#2-%E5%A4%A7%E4%BA%8E%E7%BB%99%E5%AE%9A%E5%85%83%E7%B4%A0%E7%9A%84%E6%9C%80%E5%B0%8F%E5%85%83%E7%B4%A0
  public char nextGreatestLetter2(char[] letters, char target) {
    int n = letters.length;
    int l = 0, h = n - 1;
    while (l <= h) {
      int m = l + (h - l) / 2;
      if (letters[m] <= target) {
        l = m + 1;
      } else {
        h = m - 1;
      }
    }
    return l < n ? letters[l] : letters[0];
  }
}
