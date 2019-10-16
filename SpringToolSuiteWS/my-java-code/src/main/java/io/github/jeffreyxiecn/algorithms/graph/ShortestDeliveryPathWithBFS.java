package io.github.jeffreyxiecn.algorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ShortestDeliveryPathWithBFS {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  int minimumDistance(int numRows, int numColumns, List<List<Integer>> area) {
    // WRITE YOUR CODE HERE
    // dist[i][j] keeps track of the distance from starting point [0][0] to point[i][j]
    int[][] dist = new int[numRows][numColumns];
    for (int i = 0; i < numRows; i++) {
      Arrays.fill(dist[i], Integer.MAX_VALUE);
    }

    // visited[i][j] keeps track of whether point[i][j] has been visited
    boolean[][] visited = new boolean[numRows][numColumns];
    for (boolean[] temp : visited) {
      Arrays.fill(temp, false);
    }

    Deque<Integer[]> queue = new LinkedList<>();
    queue.add(new Integer[] {0, 0});
    visited[0][0] = true;
    dist[0][0] = 0;

    Integer[] currHead;
    while (!queue.isEmpty()) {
      currHead = queue.remove();
      if (area.get(currHead[0]).get(currHead[1]) == 9) {
        // reached destination
        return dist[currHead[0]][currHead[1]];
      }

      List<Integer[]> moves =
          nextMoves(numRows, numColumns, area, currHead[0], currHead[1], visited);
      for (Integer[] move : moves) {
        queue.add(move);
        visited[move[0]][move[1]] = true;
        dist[move[0]][move[1]] = dist[currHead[0]][currHead[1]] + 1;
      }
    }

    return Integer.MAX_VALUE; // the destination is unreachable
  }
  // METHOD SIGNATURE ENDS

  // check the possible next moves in clockwise direction
  private List<Integer[]> nextMoves(
      int numRows, int numColumns, List<List<Integer>> area, int i, int j, boolean[][] visited) {
    List<Integer[]> list = new ArrayList<>();

    if (j + 1 < numColumns
        && (area.get(i).get(j + 1) == 1 || area.get(i).get(j + 1) == 9)
        && !visited[i][j + 1]) {
      // can go right
      list.add(new Integer[] {i, j + 1});
    }

    if (i + 1 < numRows
        && (area.get(i + 1).get(j) == 1 || area.get(i + 1).get(j) == 9)
        && !visited[i + 1][j]) {
      // can go down
      list.add(new Integer[] {i + 1, j});
    }

    if (j - 1 >= 0
        && (area.get(i).get(j - 1) == 1 || area.get(i).get(j - 1) == 9)
        && !visited[i][j - 1]) {
      // can go left
      list.add(new Integer[] {i, j - 1});
    }

    if (i - 1 >= 0
        && (area.get(i - 1).get(j) == 1 || area.get(i).get(j - 1) == 9)
        && !visited[i - 1][j]) {
      // can go up
      list.add(new Integer[] {i - 1, j});
    }

    return list;
  }
}