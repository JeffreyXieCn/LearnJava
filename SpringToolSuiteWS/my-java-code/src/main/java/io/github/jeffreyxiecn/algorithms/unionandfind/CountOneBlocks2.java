package io.github.jeffreyxiecn.algorithms.unionandfind;

public class CountOneBlocks2 {
  // add a top and left padding of zeros to the original matrix
  private int[][] ma;

  // parent[i][j] will be the parent of ma[i][j], if parent[i][j][0] is -1, then m[i][j] is the root
  // of a subset and parent[i][j][1] will be the rank
  private int[][][] parent;

  private int counter;

  public CountOneBlocks2(int[][] m) {
    ma = new int[m.length + 1][m[0].length + 1];
    // fill the first row with 0
    //    Arrays.fill(ma[0], 0);
    //    for (int i = 1; i < ma.length; i++) {
    //      ma[i][0] = 0;
    //      System.arraycopy(m[i - 1], 0, ma[i], 1, m[i - 1].length);
    //    }

    parent = new int[ma.length][ma[0].length][2];
    for (int i = 0; i < ma.length; i++) {
      for (int j = 0; j < ma[i].length; j++) {
        if (i == 0 || j == 0) {
          ma[i][j] = 0;
        } else {
          ma[i][j] = m[i - 1][j - 1];
        }

        parent[i][j][0] = 0;
        parent[i][j][1] = 0;
      }
    }

    counter = 0;
  }

  public int count() {
    for (int i = 1; i < ma.length; i++) {
      for (int j = 1; j < ma[i].length; j++) {
        if (ma[i][j] == 1) {
          if (ma[i][j - 1] == 0 && ma[i - 1][j] == 0) {
            // the root of a new set
            parent[i][j][0] = -1;
            counter++;
          } else if (ma[i][j - 1] == 1 && ma[i - 1][j] == 0) {
            // belongs to the left set
            parent[i][j][0] = i;
            parent[i][j][1] = j - 1;
          } else if (ma[i][j - 1] == 0 && ma[i - 1][j] == 1) {
            // belongs to the top set
            parent[i][j][0] = i - 1;
            parent[i][j][1] = j;
          } else {
            // need to merge left set and top set
            union(i, j);
          }
        }
      }
    }

    return counter;
  }

  private int[] findRoot(int i, int j) {
    if (parent[i][j][0] == -1) {
      return new int[] {i, j};
    }

    int[] root = findRoot(parent[i][j][0], parent[i][j][1]);
    parent[i][j][0] = root[0]; // path compression
    parent[i][j][1] = root[1];
    return root;
  }

  private void union(int i, int j) {
    int[] rootLeft = findRoot(i, j - 1);
    int[] rootTop = findRoot(i - 1, j);
    if (rootLeft[0] == rootTop[0] && rootLeft[1] == rootTop[1]) {
      // left and top are in the same set
      parent[i][j][0] = rootLeft[0];
      parent[i][j][1] = rootLeft[1];
    } else {
      if (parent[rootLeft[0]][rootLeft[1]][1] < parent[rootTop[0]][rootTop[1]][1]) {
        // rank of left set is smaller than the rank of top set, merge the left to top
        merge(i, j, rootLeft, rootTop);
      } else if (parent[rootLeft[0]][rootLeft[1]][1] > parent[rootTop[0]][rootTop[1]][1]) {
        // rank of top set is smaller than the rank of left set, merge the top to left
        merge(i, j, rootTop, rootLeft);
      } else {
        // same rank, merge top to left, and increase the rank of left
        merge(i, j, rootTop, rootLeft);
        parent[rootLeft[0]][rootLeft[1]][1]++;
      }
    }
  }

  private void merge(int i, int j, int[] from, int[] to) {
    parent[from[0]][from[1]][0] = to[0];
    parent[from[0]][from[1]][1] = to[1];
    counter--;
    parent[i][j][0] = to[0];
    parent[i][j][1] = to[1];
  }
}
