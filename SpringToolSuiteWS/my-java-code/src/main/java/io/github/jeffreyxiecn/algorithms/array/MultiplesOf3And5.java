package io.github.jeffreyxiecn.algorithms.array;

public class MultiplesOf3And5 {

  public static void main(String[] args) {
    System.out.println(sumOfMultiplesOf3And5(1000));
  }

  public static int sumOfMultiplesOf3And5(int end) {

    //    T = 3*int(999/3)*(1+int(999/3))/2 + 5*int(999/5)*(1+int(999/5))/2 -
    // 15*int(999/15)*(1+int(999/15))/2
    //
    //    Therefore, T = 3*333*(1+333)/2 + 5*199*(1+199)/2 - 15*66*(1+66)/2 = 233168.
    int sum = 0;
    int multi3 = 3;
    while (multi3 < end) {
      sum += multi3;
      multi3 += 3;
    }

    int multi5 = 5;
    while (multi5 < end) {
      if (multi5 % 3 != 0) {
        sum += multi5;
      }
      multi5 += 5;
    }

    return sum;
  }
}
