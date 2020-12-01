package io.github.jeffreyxiecn.algorithms.array;

import java.util.List;

public class WaterStorage {
  /*
  Given a list of whole numbers which represent the height of poles/bars placed vertically on a flat surface.
  You need to compute how much water it can store between the bars in case of rain.
  Assumption: pole/bar width is 1.
  Example 3,0,2,0,4 = 7

                  |
  |   x   x   x   |
  |   x   |   x   |
  |   x   |   x   |
  3   0   2   0   4



  003 0 0 0 40200500 4 0 2
  */

  public int storeWater(List<Integer> poles) {
    int sum = 0;
    for (int i = 0; i < poles.size(); i++) {
      int left = findLeft(poles, i); // find the left overflow point
      int right = findRight(poles, i); // find the right overflow point
      int min = Math.min(left, right);
      if (min > poles.get(i)) {
        sum += min - poles.get(i);
      }
    }

    return sum;
  }

  private int findLeft(List<Integer> poles, int i) {
    int max = 0;
    for (int j = i - 1; j >= 0; j--) {
      if (poles.get(j) > max) {
        max = poles.get(j);
      }
    }

    return max;
  }

  private int findRight(List<Integer> poles, int i) {
    int max = 0;
    for (int j = i + 1; j < poles.size(); j++) {
      if (poles.get(j) > max) {
        max = poles.get(j);
      }
    }

    return max;
  }

  public int storeWater3(List<Integer> poles) {
    if (poles == null || poles.size() <= 2) {
      // need at least 3 elements to hold any water, since the first and last position can't hold
      // any water
      return 0;
    }

    int sum = 0;
    int leftHighestPole = poles.get(0); // leftHighestPole will always increase
    int rightHighestPole = findHighestPoleFrom(poles, 2); // rightHighestPole will always decrease
    for (int i = 1; i < poles.size() - 1; i++) {
      if (rightHighestPole == 0) {
        // can't store water at and after i
        break;
      }

      if (poles.get(i) == rightHighestPole) {
        // can't store water at i since it's already the highest point from i to the end
        if (poles.get(i) > leftHighestPole) {
          leftHighestPole = poles.get(i);
        }

        rightHighestPole = findHighestPoleFrom(poles, i + 1);
        continue;
      }

      // hasn't reached the rightHighestPole yet
      int min = Math.min(leftHighestPole, rightHighestPole);
      if (min > poles.get(i)) {
        sum += min - poles.get(i);
      }

      if (poles.get(i) > leftHighestPole) {
        leftHighestPole = poles.get(i);
      }
    }

    return sum;
  }

  private int findHighestPoleFrom(List<Integer> poles, int i) {
    int highest = 0;
    for (int j = i; j < poles.size(); j++) {
      if (poles.get(j) > highest) {
        highest = poles.get(j);
      }
    }

    return highest;
  }

  /**
   * For a given ith position in the poles list, we need to find the left highest pole and right
   * highest pole to determine how much water it can hold. When we iterate over the list from head
   * to tail, we have already seen all the elements before i, so it's easy to track the left highest
   * pole. To quickly know the right highest pole for ith position, we can scan the list from tail
   * to head to build the rightHighestPoles array beforehand. Time complexity: O(N) Space
   * complexity: O(N)
   *
   * @param poles
   * @return
   */
  public int storeWater2(List<Integer> poles) {
    if (poles == null || poles.size() <= 2) {
      // need at least 3 elements to hold any water, since the first and last position can't hold
      // any water
      return 0;
    }

    // scan the list from tail to head to build rightHighestPoles array backwards.
    // once the array is built, the elements in it will be in decreasing order.
    int[] rightHighestPoles = new int[poles.size()];
    int rightHighest = poles.get(poles.size() - 1);
    for (int i = poles.size() - 2; i >= 1; i--) {
      rightHighestPoles[i] = rightHighest;
      if (poles.get(i) > rightHighest) {
        rightHighest = poles.get(i);
      }
    }

    int sum = 0;
    int leftHighestPole = poles.get(0); // leftHighestPole will always increase
    for (int j = 1; j < poles.size() - 1; j++) {
      if (rightHighestPoles[j] == 0) {
        // can't store water at and after i
        break;
      }

      int min = Math.min(leftHighestPole, rightHighestPoles[j]);
      if (min > poles.get(j)) {
        sum += min - poles.get(j);
      }

      if (poles.get(j) > leftHighestPole) {
        leftHighestPole = poles.get(j);
      }
    }

    return sum;
  }
}
