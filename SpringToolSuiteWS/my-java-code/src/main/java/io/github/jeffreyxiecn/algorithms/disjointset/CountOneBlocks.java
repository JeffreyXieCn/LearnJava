package io.github.jeffreyxiecn.algorithms.disjointset;

import java.util.HashSet;
import java.util.Set;

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
public class CountOneBlocks {

  public static void main(String[] args) {
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
