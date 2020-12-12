package io.github.jeffreyxiecn.algorithms.bit;

public class HammingDistance0461 {
  public int hammingDistance(int x, int y) {
    return Integer.bitCount(x ^ y);
  }

  public int hammingDistance2(int x, int y) {
    int z = x ^ y;
    int cnt = 0;
    while (z != 0) {
      if ((z & 1) == 1) {
        cnt++;
      }
      z = z >> 1;
    }
    return cnt;
  }

  // 使用 z&(z-1) 去除 z 位级表示最低的那一位1。
  public int hammingDistance3(int x, int y) {
    int z = x ^ y;
    int cnt = 0;
    while (z != 0) {
      z &= (z - 1);
      cnt++;
    }
    return cnt;
  }
}
