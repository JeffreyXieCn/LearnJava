package io.github.jeffreyxiecn.algorithms.dfs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MaxAreaOfIsland0695 {
  public static int maxAreaOfIsland(int[][] grid) {
    boolean[][] mark = new boolean[grid.length][];
    for (int i = 0; i < mark.length; i++) {
      mark[i] = new boolean[grid[i].length];
      Arrays.fill(mark[i], false);
    }

    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int max = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1 && !mark[i][j]) {
          // discovered a new island
          Deque<int[]> stack = new LinkedList<>();
          stack.push(new int[] {i, j});
          mark[i][j] = true;
          int count = 0;

          while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            count++;
            for (int[] dir : directions) {
              int r = cur[0] + dir[0];
              int c = cur[1] + dir[1];
              if (r >= 0
                  && r < grid.length
                  && c >= 0
                  && c < grid[i].length
                  && grid[r][c] == 1
                  && !mark[r][c]) {
                stack.push(new int[] {r, c});
                mark[r][c] = true;
              }
            }
          }

          if (count > max) {
            max = count;
          }
        }
      }
    }

    return max;
  }

  public static int maxAreaOfIslandShowVisitingOrder(int[][] grid) {
    boolean[][] mark = new boolean[grid.length][];
    for (int i = 0; i < mark.length; i++) {
      mark[i] = new boolean[grid[i].length];
      Arrays.fill(mark[i], false);
    }

    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int max = 0;

    // visualize the order of cells being visited
    int[][] visit = new int[grid.length][];
    for (int i = 0; i < visit.length; i++) {
      visit[i] = new int[grid[i].length];
      Arrays.fill(visit[i], 0);
    }
    int visitOrder = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1 && !mark[i][j]) {
          // discovered a new island
          Deque<int[]> stack = new LinkedList<>();
          stack.push(new int[] {i, j});
          mark[i][j] = true;
          int count = 0;

          while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            visit[cur[0]][cur[1]] = ++visitOrder;
            count++;
            for (int[] dir : directions) {
              int r = cur[0] + dir[0];
              int c = cur[1] + dir[1];
              if (r >= 0
                  && r < grid.length
                  && c >= 0
                  && c < grid[i].length
                  && grid[r][c] == 1
                  && !mark[r][c]) {
                stack.push(new int[] {r, c});
                mark[r][c] = true;
              }
            }
          }

          if (count > max) {
            max = count;
          }
        }
      }
    }

    System.out.println("The order of visiting in this grid with DFS:");
    for (int[] row : visit) {
      System.out.println(Arrays.toString(row));
    }

    return max;
  }
}
