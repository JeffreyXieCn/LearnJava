package io.github.jeffreyxiecn.algorithms.array;

public class CountCommonsInSortedArrray {

  /**
   * Question: Given two sorted arrays, find the number of elements in common. The arrays are the
   * same length and each has all distinct elements.
   *
   * @param arr1
   * @param arr2
   * @return
   */
  public static int count(int[] arr1, int[] arr2) {
    int counter = 0;
    int i = 0;
    int j = 0;
    while (i < arr1.length && j < arr2.length) {
      if (arr1[i] == arr2[j]) {
        counter++;
        i++;
        j++;
      } else if (arr1[i] > arr2[j]) {
        j++;
      } else {
        i++;
      }
    }

    return counter++;
  }
}
