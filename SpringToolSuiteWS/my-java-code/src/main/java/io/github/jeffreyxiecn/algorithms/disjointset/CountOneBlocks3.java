package io.github.jeffreyxiecn.algorithms.disjointset;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class CountOneBlocks3 {

  /**
   * This problem is similar to counting the connected sub-graphs in a disconnected graph. BFS
   * should be used to solve this kind of problem.
   *
   * @param m
   * @return
   */
  public static int count(int[][] m) {
    boolean[][] visited = new boolean[m.length][m[0].length];
    for (int i = 0; i < m.length; i++) {
      Arrays.fill(visited[i], false);
    }

    int counter = 0;
    for (int j = 0; j < m.length; j++) {
      for (int k = 0; k < m[j].length; k++) {
        if (!visited[j][k] && m[j][k] == 1) {
          visitWithBFS(m, visited, j, k);
          counter++;
        }
      }
    }

    return counter;
  }

  private static void visitWithBFS(int[][] m, boolean[][] visited, int j, int k) {
    Queue<Integer[]> queue = new ArrayDeque<>();
    queue.add(new Integer[] {j, k});
    Integer[] next;
    while (!queue.isEmpty()) {
      next = queue.remove();
      visited[next[0]][next[1]] = true;
      // add possible next hops to the queue
      if (next[1] + 1 < m[next[0]].length
          && m[next[0]][next[1] + 1] == 1
          && !visited[next[0]][next[1] + 1]) {
        // add right neighbor to the queue
        queue.add(new Integer[] {next[0], next[1] + 1});
      }

      if (next[0] + 1 < m.length
          && m[next[0] + 1][next[1]] == 1
          && !visited[next[0] + 1][next[1]]) {
        // add down neighbor to the queue
        queue.add(new Integer[] {next[0] + 1, next[1]});
      }

      if (next[1] - 1 >= 0 && m[next[0]][next[1] - 1] == 1 && !visited[next[0]][next[1] - 1]) {
        // add left neighbor to the queue
        queue.add(new Integer[] {next[0], next[1] - 1});
      }

      if (next[0] - 1 >= 0 && m[next[0] - 1][next[1]] == 1 && !visited[next[0] - 1][next[1]]) {
        // add up neighbor to the queue
        queue.add(new Integer[] {next[0] - 1, next[1]});
      }
    }
  }
}
