package io.github.jeffreyxiecn.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

/*
Q Given a matrix with numbers and ability to start at any position and move N,S,E,W to only
positions with strictly higher numbers find the max path length traversed before you can go no
further.

1 2 3 9
5 7 8 9
4 3 2 1

Max path length = 8 [1,2,3,4,5,7,8,9]
*/

public class LongestPathInMatrix {

  public static void main(String[] args) {
    int[][] matrix = new int[][] {{1, 2, 3, 9}, {5, 7, 8, 9}, {4, 3, 2, 1}};
    System.out.println(findLongestPath(matrix));
  }

  public static int findLongestPath(int[][] matrix) {
    int maxPath = 0;
    int tempPath;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        // find the longest path starting from matrix[i][j] using DFS
        tempPath = findPathRecursive(matrix, i, j);
        if (tempPath > maxPath) {
          maxPath = tempPath;
        }
      }
    }

    return maxPath;
  }

  private static int findPathRecursive(int[][] matrix, int i, int j) {
    List<Integer[]> nextList = nextNodes(matrix, i, j);
    if (nextList.isEmpty()) { // base case, nowhere to go from here
      return 1;
    }

    int maxPath = 0;
    int tempPath;
    for (Integer[] node : nextList) {
      tempPath = 1 + findPathRecursive(matrix, node[0], node[1]);
      if (tempPath > maxPath) {
        maxPath = tempPath;
      }
    }

    return maxPath;
  }

  private static List<Integer[]> nextNodes(int[][] matrix, int i, int j) {
    List<Integer[]> list = new ArrayList<>();

    if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
      // can go south
      list.add(new Integer[] {i + 1, j});
    }

    if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
      // can go north
      list.add(new Integer[] {i - 1, j});
    }

    if (j + 1 < matrix[i].length && matrix[i][j + 1] > matrix[i][j]) {
      // can go east
      list.add(new Integer[] {i, j + 1});
    }

    if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
      // can go west
      list.add(new Integer[] {i, j - 1});
    }

    return list;
  }
}
