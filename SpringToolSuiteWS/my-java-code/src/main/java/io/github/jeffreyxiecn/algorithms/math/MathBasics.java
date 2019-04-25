package io.github.jeffreyxiecn.algorithms.math;

public class MathBasics {

  public static void main(String[] args) {
    int result = (int) Math.pow(16, 0.25);
    System.out.println(result); // 2
    result = (int) Math.sqrt(16);
    System.out.println(result); // 4

    result = (int) Math.cbrt(27);
    System.out.println(result); // 3
  }
}
