package io.github.jeffreyxiecn.algorithms.unionandfind;

import java.util.Arrays;

public class RedundantConnection0684 {
  private int[] parent;
  private int[] treeSize;

  public int[] findRedundantConnection(int[][] edges) {
    // if parent[i] is 0, then i is the root of a tree
    parent = new int[edges.length + 1];
    Arrays.fill(parent, 0);

    // initially each node is a tree of itself
    treeSize = new int[edges.length + 1];
    Arrays.fill(treeSize, 1);

    for (int[] edge : edges) {
      int root1 = findRoot(edge[0]);
      int root2 = findRoot(edge[1]);
      if (root1 == root2) {
        // redundant edge
        return edge;
      }

      if (treeSize[root1] < treeSize[root2]) {
        parent[root1] = root2;
        treeSize[root2] += treeSize[root1];
      } else {
        parent[root2] = root1;
        treeSize[root1] += treeSize[root2];
      }
    }

    return new int[0];
  }

  // find root with path compression
  private int findRoot(int node) {
    if (parent[node] == 0) {
      return node;
    }

    int root = findRoot(parent[node]);
    parent[node] = root;
    return root;
  }
}
