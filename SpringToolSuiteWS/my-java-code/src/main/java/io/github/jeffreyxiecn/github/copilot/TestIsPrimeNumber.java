package io.github.jeffreyxiecn.github.copilot;

public class TestIsPrimeNumber {
  public static void main(String[] args) {
    // Test prime numbers
    testIsPrimeNumber(2, true);
    testIsPrimeNumber(3, true);
    testIsPrimeNumber(5, true);
    testIsPrimeNumber(7, true);
    testIsPrimeNumber(11, true);

    // Test composite numbers
    testIsPrimeNumber(4, false);
    testIsPrimeNumber(6, false);
    testIsPrimeNumber(8, false);
    testIsPrimeNumber(9, false);
    testIsPrimeNumber(10, false);
  }

  static void testIsPrimeNumber(int n, boolean expected) {
    boolean result = isPrimeNumber(n);
    if (result == expected) {
      System.out.println("PASS: isPrimeNumber(" + n + ") returned " + result);
    } else {
      System.out.println("FAIL: isPrimeNumber(" + n + ") returned " + result + ", expected " + expected);
    }
  }

  static boolean isPrimeNumber(int n) {
    if (n <= 1) {
      return false;
    }
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}