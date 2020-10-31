package io.github.jeffreyxiecn.algorithms.binarysearch;

public class MySqrt0069 {
  public int mySqrt(int x) {
    int l = 1;
    int h = x;
    while (l <= h) {
      int m = l + (h - l) / 2;
      if (m == (x / m)) {
        return m;
      } else if (m > (x / m)) {
        h = m - 1;
      } else {
        l = m + 1;
      }
    }

    return l - 1;
  }

  public int mySqrt2(int x) {
    if (x <= 1) {
      return x;
    }
    int l = 1, h = x;
    while (l <= h) {
      int mid = l + (h - l) / 2;
      int sqrt = x / mid;
      if (sqrt == mid) {
        return mid;
      } else if (mid > sqrt) {
        h = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return h;
  }
}
