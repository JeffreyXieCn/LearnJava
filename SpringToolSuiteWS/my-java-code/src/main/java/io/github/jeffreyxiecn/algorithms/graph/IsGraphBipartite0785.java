package io.github.jeffreyxiecn.algorithms.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class IsGraphBipartite0785 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  public boolean isBipartite(int[][] graph) {
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();
    List<Integer> unprocessedNodes = new LinkedList<>();
    for (int i = 0; i < graph.length; i++) {
      unprocessedNodes.add(i);
    }

    while (unprocessedNodes.size() != 0) {
      int curNode = unprocessedNodes.remove(0);
      //      if (graph[curNode].length == 0) {
      //        return false;
      //      }
      set1.add(curNode);
      for (int j = 0; j < graph[curNode].length; j++) {
        set2.add(graph[curNode][j]);
        queue.add(graph[curNode][j]); // breadth first search
      }

      while (!queue.isEmpty()) {
        int nextNode = queue.remove();
        if (unprocessedNodes.contains(nextNode)) {
          if (set1.contains(nextNode)) {
            for (int k = 0; k < graph[nextNode].length; k++) {
              if (set1.contains(graph[nextNode][k])) {
                return false;
              }
              if (set2.add(graph[nextNode][k])) {
                // when a node was added to the set for the first time, it was also added to the
                // queue
                queue.add(graph[nextNode][k]);
              }
            }
          } else {
            for (int k = 0; k < graph[nextNode].length; k++) {
              if (set2.contains(graph[nextNode][k])) {
                return false;
              }
              if (set1.add(graph[nextNode][k])) {
                queue.add(graph[nextNode][k]);
              }
            }
          }

          unprocessedNodes.remove(Integer.valueOf(nextNode));
        }
      }
    }

    return true;
  }

  public boolean isBipartite2(int[][] graph) {
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();
    int[] unprocessedNodes = new int[graph.length];
    for (int i = 0; i < graph.length; i++) {
      unprocessedNodes[i] = i;
    }

    int idx = 0;
    while (true) {
      while (idx < graph.length && unprocessedNodes[idx] == -1) {
        // -1 means the node has been processed
        idx++;
      }
      if (idx >= graph.length) {
        // all nodes have been processed
        break;
      }
      int curNode = unprocessedNodes[idx];
      unprocessedNodes[idx] = -1;
      idx++;

      set1.add(curNode);
      for (int j = 0; j < graph[curNode].length; j++) {
        set2.add(graph[curNode][j]);
        queue.add(graph[curNode][j]); // breadth first search
      }

      while (!queue.isEmpty()) {
        int nextNode = queue.remove();
        if (unprocessedNodes[nextNode] != -1) {
          if (set1.contains(nextNode)) {
            for (int k = 0; k < graph[nextNode].length; k++) {
              if (set1.contains(graph[nextNode][k])) {
                return false;
              }
              if (set2.add(graph[nextNode][k])) {
                // when a node was added to the set for the first time, it was also added to the
                // queue
                queue.add(graph[nextNode][k]);
              }
            }
          } else {
            for (int k = 0; k < graph[nextNode].length; k++) {
              if (set2.contains(graph[nextNode][k])) {
                return false;
              }
              if (set1.add(graph[nextNode][k])) {
                queue.add(graph[nextNode][k]);
              }
            }
          }

          unprocessedNodes[nextNode] = -1;
        }
      }
    }

    return true;
  }

  // https://leetcode.com/problems/is-graph-bipartite/discuss/115487/Java-Clean-DFS-solution-with-Explanation
  public boolean isBipartiteDFS(int[][] graph) {
    int[] color = new int[graph.length];
    Deque<Integer> stack = new LinkedList<>();

    for (int i = 0; i < graph.length; i++) {
      color[i] = 0;
    }

    int idx = 0;
    while (true) {
      while (idx < graph.length && color[idx] != 0) {
        // the node has been colored
        idx++;
      }

      if (idx >= graph.length) {
        break;
      }

      color[idx] = 1;
      stack.push(idx);
      idx++;
      while (!stack.isEmpty()) {
        int curNode = stack.pop();
        int coloring = color[curNode] == 1 ? -1 : 1;
        for (int j = 0; j < graph[curNode].length; j++) {
          if (color[graph[curNode][j]] == 0) {
            color[graph[curNode][j]] = coloring;
            stack.push(graph[curNode][j]);
          } else if (color[graph[curNode][j]] == color[curNode]) {
            return false;
          }
        }
      }
    }

    return true;
  }

  public boolean isBipartiteBFS(int[][] graph) {
    int len = graph.length;
    int[] colors = new int[len];

    for (int i = 0; i < len; i++) {
      if (colors[i] != 0) {
        continue;
      }
      Queue<Integer> queue = new LinkedList<>();
      queue.offer(i);
      // queue.add(i);
      colors[i] = 1; // Blue: 1; Red: -1.

      while (!queue.isEmpty()) {
        int cur = queue.poll();
        // queue.remove();
        for (int next : graph[cur]) {
          if (colors[next] == 0) { // If this node hasn't been colored;
            colors[next] = -colors[cur]; // Color it with a different color;
            queue.offer(next);
          } else if (colors[next]
              != -colors[cur]) { // If it is colored and its color is different, return false;
            return false;
          }
        }
      }
    }

    return true;
  }

  public boolean isBipartite6(int[][] graph) {
    if (graph == null || graph.length == 0) {
      return false;
    }

    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();
    int m = graph.length;
    boolean[] visited = new boolean[m];
    Arrays.fill(visited, false);
    for (int i = 0; i < m; i++) {
      if (visited[i]) {
        continue;
      }

      // BFS starting from graph[i]
      Queue<Integer> queue = new LinkedList<>();
      queue.add(i);
      while (!queue.isEmpty()) {
        int cur = queue.remove();
        // can skip if cur is added to the queue more than once and is visited previously
        if (visited[cur]) {
          continue;
        }

        if (!set1.contains(cur) && !set2.contains(cur)) {
          // the first node in a new conneted sub-graph
          set1.add(cur);
          for (int dest : graph[cur]) {
            set2.add(dest);
            queue.add(dest);
          }
        } else if (set1.contains(cur)) {
          // put graph[cur]'s reachable nodes to set2
          // for(int dest : graph[cur]){
          //     if(set1.contains(dest)){
          //         return false;
          //     }
          //     set2.add(dest);
          //     if(!visited[dest]){
          //         queue.add(dest);
          //     }
          // }
          if (!addToSetAndQueue(graph[cur], set1, set2, queue, visited)) {
            return false;
          }
        } else {
          // set2.contains(cur), put graph[cur]'s reachable nodes to set1
          // for(int dest : graph[cur]){
          //     if(set2.contains(dest)){
          //         return false;
          //     }
          //     set1.add(dest);
          //     if(!visited[dest]){
          //         queue.add(dest);
          //     }
          // }
          if (!addToSetAndQueue(graph[cur], set2, set1, queue, visited)) {
            return false;
          }
        }

        visited[cur] = true;
      }
    }

    return true;
  }

  private boolean addToSetAndQueue(
      int[] dests, Set<Integer> set1, Set<Integer> set2, Queue<Integer> queue, boolean[] visited) {
    for (int dest : dests) {
      if (set1.contains(dest)) {
        return false;
      }
      set2.add(dest);
      if (!visited[dest]) {
        queue.add(dest);
      }
    }

    return true;
  }

  public boolean isBipartite7(int[][] graph) {
    int[] colors = new int[graph.length];
    Arrays.fill(colors, -1); // -1: unclassified, 0: set1, 1: set2
    for (int i = 0; i < graph.length; i++) { // 处理图不是连通的情况
      if (colors[i] == -1 && !isBipartite(i, 0, colors, graph)) {
        return false;
      }
    }
    return true;
  }

  private boolean isBipartite(int curNode, int curColor, int[] colors, int[][] graph) {
    if (colors[curNode] != -1) {
      return colors[curNode] == curColor;
    }
    colors[curNode] = curColor;
    for (int nextNode : graph[curNode]) {
      // DFS to color my neighbour to opposite color
      if (!isBipartite(nextNode, 1 - curColor, colors, graph)) {
        return false;
      }
    }
    return true;
  }
}

/*wrong solution
class Solution {
  public boolean isBipartite(int[][] graph) {
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();
    for (int i = 0; i < graph.length; i++) {
      if (set1.contains(i)) {
        if (!checkAndUpdateSet(set1, set2, graph[i])) {
          return false;
        }
      } else if (set2.contains(i)) {
        if (!checkAndUpdateSet(set2, set1, graph[i])) {
          return false;
        }
      } else {
        if (graph[i].length == 0) {
          return false;
        }
        boolean processed = false;
        for (int j = 0; j < graph[i].length; j++) {
          if (set1.contains(graph[i][j])) {
            set2.add(i);
            if (!checkAndUpdateSet(set2, set1, graph[i])) {
              return false;
            }
            processed = true;
            break;
          }
          if (set2.contains(graph[i][j])) {
            set1.add(i);
            if (!checkAndUpdateSet(set1, set2, graph[i])) {
              return false;
            }
            processed = true;
            break;
          }
        }

        if (!processed) {
          // none of nodes connected to this node has been processed
          set1.add(i);
          for (int k = 0; k < graph[i].length; k++) {
            set2.add(graph[i][k]);
          }
        }
      }
    }
    return true;
  }

  private boolean checkAndUpdateSet(Set<Integer> toChk, Set<Integer> toUpt, int[] nodes) {
    for (int j = 0; j < nodes.length; j++) {
      if (toChk.contains(nodes[j])) {
        return false;
      }
      if (!toUpt.contains(nodes[j])) {
        toUpt.add(nodes[j]);
      }
    }
    return true;
  }
}

*/
