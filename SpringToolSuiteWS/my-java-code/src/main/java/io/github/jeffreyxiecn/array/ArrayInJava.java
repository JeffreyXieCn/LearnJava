package io.github.jeffreyxiecn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

    int[] myWeight = {43, 54, 41, 43, 48, 53, 40};
    System.out.println(findMaxDiff(myWeight));

    System.out.println("Finding one blocks");
    int[][] matrix1 = {
      {0, 1, 0, 0, 0, 1, 0, 0},
      {0, 1, 1, 1, 1, 1, 0, 0}
    };
    int expect1 = 1;
    System.out.println(countOneBlocks(matrix1) == expect1);

    int[][] matrix2 = {
      {0, 1, 0, 0, 0, 1, 1, 0},
      {0, 1, 1, 1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0, 1, 1, 0},
      {0, 0, 0, 0, 0, 1, 1, 0},
      {0, 0, 0, 0, 1, 1, 1, 0}
    };
    int expect2 = 2;
    System.out.println(countOneBlocks(matrix2) == expect2);

    int[][] matrix3 = {
      {0, 1, 0, 1, 0, 1, 1, 0},
      {0, 1, 1, 1, 0, 1, 1, 0},
      {0, 1, 0, 1, 0, 1, 1, 1},
      {0, 1, 0, 1, 1, 1, 1, 0},
      {0, 0, 1, 1, 0, 1, 1, 1},
      {0, 1, 1, 0, 1, 1, 0, 1}
    };
    int expect3 = 1;
    System.out.println(countOneBlocks(matrix3) == expect3);

    int[][] matrix4 = {{0, 1, 0, 0, 0, 1, 0, 0}};
    int expect4 = 2;
    System.out.println(countOneBlocks(matrix4) == expect4);

    int[][] matrix5 = {{0}, {1}, {0}, {0}, {1}, {1}, {0}, {1}};
    int expect5 = 3;
    System.out.println(countOneBlocks(matrix5) == expect5);
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

  /* Question3: find the number of 1 block.
  *
  * 0 1 0 0 0 1 0 0
  * 0 1 1 1 1 1 0 0
  * should return 1

  * 0 1 0 0 0 1 1 0
  * 0 1 1 1 0 1 1 0
  * 0 1 0 1 0 1 1 0
  * 0 1 0 1 0 1 1 0
  * 0 0 0 0 0 1 1 0
  * 0 0 0 0 1 1 1 0
  *
  * should return 2
  */
  public static int countOneBlocks(int[][] matrix2) {
    // add a zero line on top and on left to make the logic easier to understand
    int[][] matrix = new int[matrix2.length + 1][matrix2[0].length + 1];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (i == 0 || j == 0) {
          matrix[i][j] = 0;
        } else {
          matrix[i][j] = matrix2[i - 1][j - 1];
        }
      }
    }

    int counter = 0;
    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[i].length; j++) {
        // consider only up and left direction
        if (matrix[i][j] == 1) {
          if (matrix[i][j - 1] > 0
              && matrix[i - 1][j] > 0
              && matrix[i][j - 1] != matrix[i - 1][j]) {
            // case 1: this element's left neighbor and up neighbor belongs to two different labeled
            // blocks, merge the block with big label with the block with small label
            mergeBlocks(matrix, i, j);
          } else if (matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
            // case 2: both up and left are 0, label it as the beginning of a new block
            counter++;
            matrix[i][j] = counter;
          } else if (matrix[i - 1][j] > 0) {
            // label current one the same as up
            matrix[i][j] = matrix[i - 1][j];
          } else {
            // the left is already labeled, label current one the same as left
            matrix[i][j] = matrix[i][j - 1];
          }
        }
      }
    }

    // finally, count how many labels are used
    Set<Integer> labels = new HashSet<>();
    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[i].length; j++) {
        if (matrix[i][j] > 0) {
          labels.add(matrix[i][j]);
        }
      }
    }

    return labels.size();
  }

  private static void mergeBlocks(int[][] matrix, int i, int j) {
    int small = matrix[i - 1][j];
    int big = matrix[i][j - 1];
    if (small > big) {
      int temp = small;
      small = big;
      big = temp;
    }

    // search from matrix[1][1] to matrix[i][j], re-label the big block as small block
    for (int m = 1; m <= i; m++) {
      for (int n = 1; n < matrix[m].length; n++) {
        if (m == i && n == j) {
          matrix[i][j] = small;
          return;
        }
        if (matrix[m][n] == big) {
          matrix[m][n] = small;
        }
      }
    }
  }
}

// int counter = 0;
// for (int i = 0; i < matrix.length; i++) {
//  for (int j = 0; j < matrix[i].length; j++) {
//    // first, consider only up and left direction
//    if (matrix[i][j] == 1) {
//      if (j > 0
//          && i > 0
//          && matrix[i - 1][j] > 0
//          && matrix[i][j - 1] > 0
//          && matrix[i - 1][j] != matrix[i][j - 1]) {
//        // case 1: this element's left neighbor and up neighbor belongs to two different labeled
//        // blocks merge the block with bigger label with the block with small label
//        mergeBlocks(i, j, matrix[i - 1][j], matrix[i][j - 1]);
//      } else if ((i == 0 || (i > 0 && matrix[i - 1][j] == 0))
//          && (j == 0 || (j > 0 && matrix[i][j - 1] == 0))) {
//        // case 2: both left and up are 0, label it as the beginning of a new block
//        counter++;
//        matrix[i][j] = counter;
//      }
//      else if()
//    }
//  }
// }
