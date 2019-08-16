package io.github.jeffreyxiecn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayInJava {

  public static void main(String[] args) {
    List<Integer> li = new ArrayList<>();
    List<String> ls = new ArrayList<>();
    System.out.println(li.getClass()); // class java.util.ArrayList
    System.out.println(ls.getClass()); // class java.util.ArrayList
    List<String>[] lsArr = new List[10];
    System.out.println(lsArr.getClass()); // class [Ljava.util.List;
    List<String>[] lsArr2 = new ArrayList[10];
    System.out.println(lsArr2.getClass()); // class [Ljava.util.ArrayList;
    String[] strArr = new String[10]; // class [Ljava.lang.String;
    System.out.println(strArr.getClass());
    String[][] strArr2 = new String[10][20];
    System.out.println(strArr2.getClass()); // class [[Ljava.lang.String;
    // List<String>[] liArr2 = new ArrayList<>()[10];
    // ArrayList<String>[] alArr3 = new ArrayList<String>[10]; //Cannot create a generic array of
    // ArrayList<String>

    int[] arr = {2, 5, 1023, 0, 5, 7, 2, 1022};
    sortArray(arr);
    System.out.println();
    for (int element : arr) {
      System.out.print(element + " ");
    }
    System.out.println();
    // System.out.println(arr.toString());
    System.out.println(Arrays.toString(arr));
    Arrays.stream(arr).forEach(System.out::println);

    int[] myWeight = {43, 54, 41, 43, 48, 53, 40};
    System.out.println(findMaxDiff(myWeight));
  }

  /** @param arr an array whose elements' value is between [0, 2^10); */
  public static void sortArray(int[] arr) {
    int numberOfBits = 10;
    int arrSize = (int) Math.pow(2, numberOfBits);
    int[] counter = new int[arrSize];
    for (int i = 0; i < arr.length; i++) {
      counter[arr[i]]++;
    }

    int index = 0;
    for (int i = 0; i < arrSize; i++) {
      if (counter[i] > 0) {
        // the value i has appeared at least once
        for (int j = 0; j < counter[i]; j++) {
          arr[index++] = i;
        }
      }
    }
  }

  /*
   *
   * Question2: One int array A[], indicating my weight number every day.
   * Find the max difference weight Max(Aj - Ai). Ai < Aj, i<j,
   * A[]={43, 54, 41, 43, 48, 53, 40}
   * return 53-41=12
   */
  public static int findMaxDiff(int[] arr) {
    int maxDiff = -1;
    int min = arr[0];
    int curDiff;
    for (int i = 1; i < arr.length; i++) {
      curDiff = arr[i] - min;
      if (curDiff > maxDiff) {
        maxDiff = curDiff;
      } else if (arr[i] < min) {
        min = arr[i];
      }
    }

    return maxDiff;
  }
}
