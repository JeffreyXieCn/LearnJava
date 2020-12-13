package io.github.jeffreyxiecn.algorithms.bit;

import java.util.HashMap;
import java.util.Map;

public class ReverseBits0190 {
  // you need treat n as an unsigned value
  public int reverseBits(int n) {
    int mask = 1;
    int last;
    int result = 0;
    for (int i = 0; i < 32; i++) {
      last = n & mask;
      result = (result << 1) + last;
      n = n >> 1;
    }

    return result;
  }

  public int reverseBits2(int n) {
    int ret = 0;
    for (int i = 0; i < 32; i++) {
      ret <<= 1;
      ret |= (n & 1);
      n >>>= 1;
    }
    return ret;
  }

  // 如果该函数需要被调用很多次，可以将 int 拆成 4 个 byte，然后缓存 byte 对应的比特位翻转，最后再拼接起来。
  private static Map<Byte, Integer> cache = new HashMap<>(); // maximum 256 entries

  public int reverseBits3(int n) {
    int ret = 0;
    for (int i = 0; i < 4; i++) {
      ret <<= 8;
      ret |= reverseByte((byte) (n & 0b11111111));
      n >>= 8;
    }
    return ret;
  }

  private int reverseByte(byte b) {
    if (cache.containsKey(b)) {
      return cache.get(b);
    }
    int ret = 0;
    byte t = b;
    for (int i = 0; i < 8; i++) {
      ret <<= 1;
      ret |= t & 1;
      t >>= 1;
    }
    cache.put(b, ret);
    return ret;
  }
}
