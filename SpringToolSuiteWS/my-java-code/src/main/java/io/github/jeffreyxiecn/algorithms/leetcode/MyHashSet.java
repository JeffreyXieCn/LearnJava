package io.github.jeffreyxiecn.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

public class MyHashSet {
  private List<Integer>[] buckets;
  private int defaultSize;

  /** Initialize your data structure here. */
  public MyHashSet() {
    defaultSize = 1000;
    buckets = new List[defaultSize];
  }

  private int hash(int key) {
    return key % defaultSize;
  }

  public void add(int key) {
    int idx = hash(key);
    if (buckets[idx] == null) {
      buckets[idx] = new ArrayList<>();
      buckets[idx].add(key);
    } else {
      if (!buckets[idx].contains(key)) {
        buckets[idx].add(key);
      }
    }
  }

  public void remove(int key) {
    List<Integer> bucket = buckets[hash(key)];
    if (bucket != null && bucket.contains(key)) {
      bucket.remove(Integer.valueOf(key));
    }
  }

  /** Returns true if this set contains the specified element */
  public boolean contains(int key) {
    List<Integer> bucket = buckets[hash(key)];
    if (bucket != null && bucket.contains(key)) {
      return true;
    }

    return false;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    MyHashSet hashSet = new MyHashSet();
    hashSet.add(1);
    hashSet.add(2);
    System.out.println(hashSet.contains(1)); // returns true
    System.out.println(hashSet.contains(3)); // returns false (not found)
    hashSet.add(2);
    System.out.println(hashSet.contains(2)); // returns true
    hashSet.remove(2);
    System.out.println(hashSet.contains(2)); // returns false (already removed)
  }
}
