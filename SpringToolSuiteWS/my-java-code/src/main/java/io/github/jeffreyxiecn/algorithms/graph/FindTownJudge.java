package io.github.jeffreyxiecn.algorithms.graph;

public class FindTownJudge {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(findJudge(4, new int[][] {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}));
  }

  public static int findJudge(int N, int[][] trust) {
    int[] inbound = new int[N + 1];
    int[] outbound = new int[N + 1];
    for (int i = 0; i < trust.length; i++) {
      outbound[trust[i][0]]++;
      inbound[trust[i][1]]++;
    }

    for (int j = 1; j <= N; j++) {
      if (inbound[j] == (N - 1) && outbound[j] == 0) {
        return j;
      }
    }

    return -1;
  }
}
