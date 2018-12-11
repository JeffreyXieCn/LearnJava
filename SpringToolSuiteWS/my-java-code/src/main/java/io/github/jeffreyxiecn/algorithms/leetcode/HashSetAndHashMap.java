package io.github.jeffreyxiecn.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

  public static void printNumbersOfSum2(int[] arr, int sum) {
    Set<Integer> set = new HashSet<>();
    for (int ele : arr) {
      if (set.contains(sum - ele)) {
        System.out.println((sum - ele) + " + " + ele + " = " + sum);
      } else {
        set.add(ele);
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

  public boolean isHappy(int n) {
    Set<Integer> set = new HashSet<>();
    int digit;
    while (n != 1) {
      int sum = 0;
      while (n > 0) {
        digit = n % 10;
        sum += digit * digit;
        n /= 10;
      }
      if (set.contains(sum)) {
        return false;
      }
      set.add(sum);
      n = sum;
    }

    return true;
  }

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(target - nums[i])) {
        return new int[] {map.get(target - nums[i]), i};
      }
      map.put(nums[i], i);
    }

    return new int[0];
  }

  public boolean isIsomorphic(String s, String t) {
    return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
  }

  private boolean isIsomorphicHelper(String s, String t) {
    char[] charsS = s.toCharArray();
    char[] charsT = t.toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < charsS.length; i++) {
      if (map.containsKey(charsS[i])) {
        // in s, char at i and map.get(chars[i]) are the same
        if (charsT[map.get(charsS[i])] != charsT[i]) {
          return false;
        }
      } else {
        map.put(charsS[i], i);
      }
    }
    return true;
  }

  public boolean isIsomorphic2(String s, String t) {
    char[] charsS = s.toCharArray();
    char[] charsT = t.toCharArray();
    Map<Character, Integer> mapS = new HashMap<>();
    Map<Character, Integer> mapT = new HashMap<>();
    for (int i = 0; i < charsS.length; i++) {
      if (mapS.containsKey(charsS[i])) {
        // in s, char at i and mapS.get(charsS[i]) are the same
        if (charsT[mapS.get(charsS[i])] != charsT[i]) {
          return false;
        }
      } else {
        mapS.put(charsS[i], i);
      }

      if (mapT.containsKey(charsT[i])) {
        // in t, char at i and mapT.get(charsT[i]) are the same
        if (charsS[mapT.get(charsT[i])] != charsS[i]) {
          return false;
        }
      } else {
        mapT.put(charsT[i], i);
      }
    }
    return true;
  }

  public boolean isIsomorphic3(String s, String t) {
    char[] charsS = s.toCharArray();
    char[] charsT = t.toCharArray();
    Map<Character, Integer> mapS = new HashMap<>();
    Map<Character, Integer> mapT = new HashMap<>();
    for (int i = 0; i < charsS.length; i++) {
      if (mapS.containsKey(charsS[i])) {
        // in s, char at i and mapS.get(charsS[i]) are the same
        if (charsT[mapS.get(charsS[i])] != charsT[i]) {
          return false;
        }
      } else {
        mapS.put(charsS[i], i);
      }
    }

    for (int i = 0; i < charsT.length; i++) {
      if (mapT.containsKey(charsT[i])) {
        // in t, char at i and mapT.get(charsT[i]) are the same
        if (charsS[mapT.get(charsT[i])] != charsS[i]) {
          return false;
        }
      } else {
        mapT.put(charsT[i], i);
      }
    }
    return true;
  }

  public boolean isIsomorphic4(String s, String t) {
    char[] charsS = s.toCharArray();
    char[] charsT = t.toCharArray();
    Map<Character, Character> map = new HashMap<>();
    for (int i = 0; i < charsS.length; i++) {
      if (map.containsKey(charsS[i])) {
        // this char has appeared before
        if (charsT[i] != map.get(charsS[i])) {
          return false;
        }
      } else {
        if (map.containsValue(charsT[i])) {
          return false;
        }
        map.put(charsS[i], charsT[i]);
      }
    }
    return true;
  }

  public boolean isIsomorphic5(String s, String t) {
    int[] sCharLastAppearAt = new int[256];
    int[] tCharLastAppearAt = new int[256];
    int n = s.length();
    for (int i = 0; i < n; ++i) {
      if (sCharLastAppearAt[s.charAt(i)] != tCharLastAppearAt[t.charAt(i)]) {
        // s.charAt(i) should map to t.charAt(i), so the position they appear last time should be
        // the same, otherwise, return false
        return false;
      }
      sCharLastAppearAt[s.charAt(i)] = i + 1;
      tCharLastAppearAt[t.charAt(i)] = i + 1;
    }
    return true;
  }

  public String[] findRestaurant(String[] list1, String[] list2) {
    String[] toScan = list1;
    String[] toMap = list2;
    if (list2.length > list1.length) {
      toScan = list2;
      toMap = list1;
    }

    Map<String, Integer> strToIndexMap = new HashMap<>();
    for (int i = 0; i < toMap.length; i++) {
      strToIndexMap.put(toMap[i], i);
    }

    List<String>[] commonRes = new ArrayList[2000];
    int sumOfIdx;
    for (int j = 0; j < toScan.length; j++) {
      if (strToIndexMap.containsKey(toScan[j])) {
        // a common restaurant found
        sumOfIdx = j + strToIndexMap.get(toScan[j]);
        if (commonRes[sumOfIdx] == null) {
          commonRes[sumOfIdx] = new ArrayList<>();
        }
        commonRes[sumOfIdx].add(toScan[j]);
      }
    }

    for (List<String> list : commonRes) {
      if (list != null) {
        return list.toArray(new String[list.size()]);
      }
    }

    return null;
  }

  public String[] findRestaurant2(String[] list1, String[] list2) {
    String[] toScan = list1;
    String[] toMap = list2;
    if (list2.length > list1.length) {
      toScan = list2;
      toMap = list1;
    }

    Map<String, Integer> strToIndexMap = new HashMap<>();
    for (int i = 0; i < toMap.length; i++) {
      strToIndexMap.put(toMap[i], i);
    }

    Map<Integer, List<String>> map = new HashMap<>();
    int sumOfIdx;
    for (int j = 0; j < toScan.length; j++) {
      if (strToIndexMap.containsKey(toScan[j])) {
        // a common restaurant found
        sumOfIdx = j + strToIndexMap.get(toScan[j]);
        if (!map.containsKey(sumOfIdx)) {
          map.put(sumOfIdx, new ArrayList<>());
        }
        map.get(sumOfIdx).add(toScan[j]);
      }
    }

    int minIndexSum = Integer.MAX_VALUE;
    for (Integer key : map.keySet()) {
      if (key < minIndexSum) {
        minIndexSum = key;
      }
    }

    List<String> result = map.get(minIndexSum);
    return result.toArray(new String[result.size()]);
  }

  public static void main(String[] args) {
    int[] arr2 = {5, 12, 7, 15, 8, 9, 11, 10};
    printNumbersOfSum(arr2, 20);
    printNumbersOfSum2(arr2, 20);
    // int a = null;
    int[] a = null;
    // float b = null;
  }
}
