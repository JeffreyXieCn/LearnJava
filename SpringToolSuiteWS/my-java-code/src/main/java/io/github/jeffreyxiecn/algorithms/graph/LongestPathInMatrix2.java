package io.github.jeffreyxiecn.algorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestPathInMatrix2 {

  public static void main(String[] args) {
    int[][] matrix = new int[][] {{1, 2, 3, 9}, {5, 7, 8, 9}, {4, 3, 2, 1}};
    System.out.println(findLongestPath(matrix));
  }

  /*
   * In this approach, each edge in the directed graph will only be visited once, so the time
   * complexity is O(E) where E is the number of edges in the graph.
   * Space complexity is O(m * n) for the longestPath matrix
   */
  public static int findLongestPath(int[][] matrix) {
    // longestPath will be used to memorize the longest path from a starting point, initialized to 0
    int[][] longestPath = new int[matrix.length][];
    for (int m = 0; m < matrix.length; m++) {
      longestPath[m] = new int[matrix[m].length];
      Arrays.fill(longestPath[m], 0);
    }

    int maxPath = 0;
    int tempPath;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        // find the longest path starting from matrix[i][j] using DFS
        tempPath = findPathRecursive(matrix, i, j, longestPath);
        if (tempPath > maxPath) {
          maxPath = tempPath;
        }
      }
    }

    return maxPath;
  }

  private static int findPathRecursive(int[][] matrix, int i, int j, int[][] longestPath) {
    if (longestPath[i][j] > 0) {
      return longestPath[i][j];
    }

    List<Integer[]> nextList = nextNodes(matrix, i, j);
    if (nextList.isEmpty()) { // base case, nowhere to go from here
      return 1;
    }

    int maxPath = 0;
    int tempPath;
    for (Integer[] node : nextList) {
      if (longestPath[node[0]][node[1]] == 0) { // haven't searched from here yet
        // search and memorize
        longestPath[node[0]][node[1]] = findPathRecursive(matrix, node[0], node[1], longestPath);
      }
      tempPath = 1 + longestPath[node[0]][node[1]];
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
