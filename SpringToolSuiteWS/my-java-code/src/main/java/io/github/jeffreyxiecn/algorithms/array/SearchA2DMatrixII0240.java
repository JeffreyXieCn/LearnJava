package io.github.jeffreyxiecn.algorithms.array;

/**
 * Note for such a matrix, for any given sub-matrix, the upper left is the smallest, and lower right
 * is the biggest.
 */
public class SearchA2DMatrixII0240 {
  private int m;
  private int n;

  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return false;
    }

    m = matrix.length;
    n = matrix[0].length;

    // matrix[0][i] is the smallest element for columns starting from i
    int colEnd = binarySearch(matrix[0], target);
    if (colEnd == -1) {
      return true; // found
    }

    colEnd = colEnd - 1;

    // matrix[m - 1][i] is the biggest element for columns up to i
    int colStart = binarySearch(matrix[m - 1], target);
    if (colStart == -1) {
      return true; // found
    }

    // scan from colStart to colEnd
    while (colStart <= colEnd) {
      // binary search each column
      if (binarySearchColumn(matrix, colStart, target)) {
        return true;
      }
      colStart++;
    }

    return false;
  }

  private int binarySearch(int[] nums, int target) {
    int l = 0;
    int h = n - 1;
    while (l <= h) {
      int mid = l + (h - l) / 2;
      if (nums[mid] == target) {
        return -1;
      } else if (nums[mid] > target) {
        h = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    return l; // the insertion point
  }

  private boolean binarySearchColumn(int[][] matrix, int col, int target) {
    int l = 0;
    int h = m - 1;
    while (l <= h) {
      int mid = l + (h - l) / 2;
      if (matrix[mid][col] == target) {
        return true;
      } else if (matrix[mid][col] > target) {
        h = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    return false;
  }

  public boolean searchMatrix2(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return false;
    }
    int m = matrix.length, n = matrix[0].length;
    int row = 0, col = n - 1;
    while (row < m && col >= 0) {
      if (target == matrix[row][col]) {
        return true;
      } else if (target < matrix[row][col]) {
        col--;
      } else {
        row++;
      }
    }
    return false;
  }
}
