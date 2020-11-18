package io.github.jeffreyxiecn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    // boolean[][] mark = new boolean[][6]; //Cannot specify an array dimension after an empty
    // dimension
    // boolean[][] mark = new boolean[][]; //Variable must provide either dimension expressions or
    // an array initializer
    boolean[][] mark = new boolean[6][];
    System.out.println("mark.length=" + mark.length);
    for (int i = 0; i < mark.length; i++) {
      mark[i] = new boolean[8];
      Arrays.fill(mark[i], false);
    }

    //    for (boolean[] row : mark) {
    //      row = new boolean[8]; // this doesn't work, row is null, then points to something new
    //      Arrays.fill(row, false);
    //    }

    for (boolean[] curRow : mark) {
      System.out.println(Arrays.toString(curRow));
    }

    System.out.println(Arrays.deepToString(mark));

    Integer[] arr1 = new Integer[] {1, 2};
    Integer[] arr2 = new Integer[] {1, 2};
    System.out.println("arr1:" + arr1); // arr1:[Ljava.lang.Integer;@3e3abc88 (DEC 1044036744)
    System.out.println("arr2:" + arr2); // arr2:[Ljava.lang.Integer;@6ce253f1 (DEC 1826771953)
    System.out.println("arr1.equals(arr2):" + arr1.equals(arr2));

    System.out.println("arr1 hashcode:" + arr1.hashCode()); // 1044036744
    System.out.println("arr2 hashcode:" + arr2.hashCode()); // 1826771953

    Map<Integer[], Integer> map = new HashMap<>();
    map.put(arr1, 1);
    System.out.println("map contains arr1:" + map.containsKey(arr1)); // true
    System.out.println("map contains arr2:" + map.containsKey(arr2)); // false

    Set<Integer[]> set = new HashSet<>();
    set.add(arr1);
    System.out.println("set contains arr1:" + set.contains(arr1)); // true
    System.out.println("set contains arr2:" + set.contains(arr2)); // false

    Integer int1 = 7;
    Integer int2 = 7;
    System.out.println("int1 hashcode:" + int1.hashCode()); // 7
    System.out.println("int2 hashcode:" + int2.hashCode()); // 7
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
}
