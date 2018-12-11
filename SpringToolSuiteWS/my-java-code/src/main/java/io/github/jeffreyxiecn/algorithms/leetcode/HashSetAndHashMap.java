package io.github.jeffreyxiecn.algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;

public class HashSetAndHashMap {

  public static void printNumbersOfSum(int[] arr, int sum) {
    Set<Integer> substract = new HashSet<>();
    for (int ele : arr) {
      if (substract.contains(ele)) {
        System.out.println((sum - ele) + " + " + ele + " = " + sum);
      } else {
        substract.add(sum - ele);
      }
    }
  }

  public boolean containsDuplicate(int[] nums) {
    Set<Integer> unique = new HashSet<>();
    for (int k : nums) {
      if (!unique.add(k)) {
        return true;
      }
    }
    return false;
  }

  public int singleNumber(int[] nums) {
    int target = 0;
    for (int num : nums) {
      target ^= num;
    }
    return target;
  }

  public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set1 = new HashSet<>();
    for (int num : nums1) {
      set1.add(num);
    }

    Set<Integer> result = new HashSet<>();
    for (int num : nums2) {
      if (set1.contains(num)) {
        result.add(num);
      }
    }
    // return result.toArray(new Integer[result.size()]);
    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  public static void main(String[] args) {
    int[] arr2 = {5, 12, 7, 15, 8, 9, 11, 10};
    printNumbersOfSum(arr2, 20);
  }
}
