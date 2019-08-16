package io.github.jeffreyxiecn.algorithms.setandmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.javatuples.Pair;
// import org.apache.commons.lang3.tuple.Pair;

public class PairOfSum {

  // assume the elements in arr are unique
  public static List<Integer[]> findPairWithSum(int[] arr, int sum) {
    Set<Integer> set = new HashSet<>();
    List<Integer[]> result = new ArrayList<>();
    for (int ele : arr) {
      if (set.contains(sum - ele)) {
        // System.out.println((sum - ele) + " + " + ele + " = " + sum);
        result.add(new Integer[] {sum - ele, ele});
      } else {
        set.add(ele);
      }
    }

    return result;
  }

  public static void printNumbersOfSum(int[] arr, int sum) {
    Set<Integer> substract = new HashSet<>();
    for (int ele : arr) {
      if (substract.contains(ele)) {
        System.out.println(sum - ele + " + " + ele + " = " + sum);
      } else {
        substract.add(sum - ele);
      }
    }
  }

  public static List<Pair<Integer, Integer>> getPairsWithSum(int[] arr, int sum) {
    Set<Integer> set = new HashSet<>();
    List<Pair<Integer, Integer>> result = new ArrayList<>();
    for (int ele : arr) {
      if (set.contains(sum - ele)) {
        // System.out.println((sum - ele) + " + " + ele + " = " + sum);
        result.add(Pair.with(sum - ele, ele));
      } else {
        set.add(ele);
      }
    }

    return result;
  }

  // assume the elements in arr are unique
  public static List<Integer[]> findIndexOfPairWithSum(int[] arr, int sum) {
    Map<Integer, Integer> mapValToIndex = new HashMap<>();
    List<Integer[]> result = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      if (mapValToIndex.containsKey(sum - arr[i])) {
        result.add(new Integer[] {mapValToIndex.get(sum - arr[i]), i});
      } else {
        mapValToIndex.put(arr[i], i);
      }
    }

    return result;
  }
}
