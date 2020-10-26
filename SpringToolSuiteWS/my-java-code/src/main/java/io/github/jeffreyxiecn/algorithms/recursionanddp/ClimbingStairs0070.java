package io.github.jeffreyxiecn.algorithms.recursionanddp;

public class ClimbingStairs0070 {
  public int climbStairs(int n) {
    /*int[] ways = new int[n + 1];
    ways[0] = 1;
    ways[1] = 1;
    for(int i = 2; i <= n; i++){
        ways[i] = ways[i - 1] + ways[i - 2];
    }

    return ways[n];*/

    int ways1 = 1;
    int ways2 = 1;
    int ways = 1;
    for (int i = 2; i <= n; i++) {
      ways = ways1 + ways2;
      ways1 = ways2;
      ways2 = ways;
    }

    return ways;
  }
}
