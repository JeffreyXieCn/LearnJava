package io.github.jeffreyxiecn.algorithms.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class LRUcache0146Test {

  @Test
  void test() {
    LRUcache0146 lru = new LRUcache0146(2);
    lru.put(1, 1);
    lru.put(2, 2);
    assertEquals(1, lru.get(1));

    lru.put(3, 3);
    assertEquals(-1, lru.get(2));

    lru.put(4, 4);
    assertEquals(-1, lru.get(1));

    assertEquals(3, lru.get(3));
    assertEquals(4, lru.get(4));
  }
}
