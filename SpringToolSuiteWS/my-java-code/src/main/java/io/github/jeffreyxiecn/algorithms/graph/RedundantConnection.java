package io.github.jeffreyxiecn.algorithms.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class RedundantConnection {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    // [[3,7],[1,4],[2,8],[1,6],[7,9],[6,10],[1,7],[2,3],[8,9],[5,9]]
    System.out.println(
        Arrays.toString(
            findRedundantConnection(
                new int[][] {
                  {3, 7}, {1, 4}, {2, 8}, {1, 6}, {7, 9}, {6, 10}, {1, 7}, {2, 3}, {8, 9}, {5, 9}
                })));

    System.out.println(
        Arrays.toString(
            findRedundantConnection2(
                new int[][] {
                  {3, 7}, {1, 4}, {2, 8}, {1, 6}, {7, 9}, {6, 10}, {1, 7}, {2, 3}, {8, 9}, {5, 9}
                })));

    System.out.println(
        Arrays.toString(
            findRedundantConnection3(
                new int[][] {
                  {3, 7}, {1, 4}, {2, 8}, {1, 6}, {7, 9}, {6, 10}, {1, 7}, {2, 3}, {8, 9}, {5, 9}
                })));
  }

  public static int[] findRedundantConnection(int[][] edges) {
    LinkedList<HashSet<Integer>> listSets = new LinkedList<>();
    for (int[] edge : edges) {
      int fromSetIdx = -1;
      int toSetIdx = -2;
      for (int j = 0; j < listSets.size(); j++) {
        if (listSets.get(j).contains(edge[0])) {
          fromSetIdx = j;
        }

        if (listSets.get(j).contains(edge[1])) {
          toSetIdx = j;
        }
      }

      if (fromSetIdx == toSetIdx) {
        // this edge will create a cycle
        return edge;
      }

      if (fromSetIdx == -1 && toSetIdx == -2) {
        // create a new set
        HashSet<Integer> set = new HashSet<>();
        set.add(edge[0]);
        set.add(edge[1]);
        listSets.add(set);
      } else if (fromSetIdx != -1 && toSetIdx != -2) {
        // need to merge the two sets
        listSets.get(fromSetIdx).addAll(listSets.get(toSetIdx));
        listSets.remove(toSetIdx);
      } else if (fromSetIdx != -1 && toSetIdx == -2) {
        // add node to set
        listSets.get(fromSetIdx).add(edge[1]);
      } else {
        listSets.get(toSetIdx).add(edge[0]);
      }
    }

    return null;
  }

  public static int[] findRedundantConnection2(int[][] edges) {
    int[] root = new int[edges.length + 1];
    for (int[] edge : edges) {
      int from = edge[0];
      int to = edge[1];
      if (root[from] == 0 && root[to] == 0) {
        // a new subtree
        root[from] = from;
        root[to] = from;
      } else if (root[from] != 0 && root[to] == 0) {
        // add node to subtree
        root[to] = root[from];
      } else if (root[from] == 0 && root[to] != 0) {
        root[from] = root[to];
      } else {
        // both are != 0
        if (root[from] == root[to]) {
          // this edge will create cycle
          return edge;
        } else {
          // merge the tree
          int newRoot = root[from];
          int oldRoot = root[to];
          for (int j = 1; j < root.length; j++) {
            if (root[j] == oldRoot) {
              root[j] = newRoot;
            }
          }
        }
      }
    }

    return null;
  }

  public static int[] findRedundantConnection3(int[][] edges) {
    DisjointSet disjointSet = new DisjointSet(edges.length);

    for (int[] edge : edges) {
      if (!disjointSet.union(edge[0] - 1, edge[1] - 1)) {
        return edge;
      }
    }

    throw new IllegalArgumentException();
  }

  static class DisjointSet {

    private int[] parent;
    private byte[] rank; // the higher the rank, the bigger the disjoint set

    public DisjointSet(int n) {
      if (n < 0) {
        throw new IllegalArgumentException();
      }
      parent = new int[n];
      rank = new byte[n];
    }

    public int find(int x) {
      if (parent[x] == 0) {
        return x;
      }
      return parent[x] = find(parent[x]); // Path compression by halving.
    }

    // Return false if x, y are connected.
    public boolean union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX == rootY) {
        return false;
      }

      // Make root of smaller rank point to root of larger rank, to reduce the height of tree
      if (rank[rootX] < rank[rootY]) {
        parent[rootX] = rootY;
      } else if (rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
      } else {
        parent[rootX] = rootY;
        rank[rootY]++;
      }

      return true;
    }
  }
}
