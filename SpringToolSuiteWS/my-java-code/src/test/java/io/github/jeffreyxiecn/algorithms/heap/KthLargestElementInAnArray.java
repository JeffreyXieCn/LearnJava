package io.github.jeffreyxiecn.algorithms.heap;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int num : nums) {
      if (pq.size() < k) {
        pq.add(num);
      } else {
        if (num > pq.element()) {
          pq.remove();
          pq.add(num);
        }
      }
    }

    return pq.element();
  }
}
