package io.github.jeffreyxiecn.algorithms.recursionanddp;

import java.math.BigInteger;

public class ChildClimbStairs {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(countWaysToClimbStairs(4));
    System.out.println(countWaysToClimbStairs2(4));

    System.out.println(countWaysToClimbStairs(100));
    System.out.println(countWaysToClimbStairs2(100));

    System.out.println(countWaysToClimbStairs(1000));
    System.out.println(countWaysToClimbStairs2(1000));

    System.out.println(countWaysToClimbStairs(10000));
    System.out.println(countWaysToClimbStairs2(10000));
  }

  public static long countWaysToClimbStairs(int n) {
    if (n == 1) {
      return 1;
    } else if (n == 2) {
      return 2;
    }
    long a = 1;
    long b = 1;
    long c = 2;
    for (int i = 3; i <= n; i++) {
      long temp = a + b + c;
      a = b;
      b = c;
      c = temp;
    }

    return c;
  }

  public static BigInteger countWaysToClimbStairs2(int n) {
    if (n == 1) {
      return BigInteger.valueOf(1);
    } else if (n == 2) {
      return BigInteger.valueOf(2);
    }
    BigInteger a = BigInteger.valueOf(1);
    BigInteger b = BigInteger.valueOf(1);
    ;
    BigInteger c = BigInteger.valueOf(2);
    ;
    for (int i = 3; i <= n; i++) {
      BigInteger temp = a.add(b).add(c);
      a = b;
      b = c;
      c = temp;
    }

    return c;
  }
}
