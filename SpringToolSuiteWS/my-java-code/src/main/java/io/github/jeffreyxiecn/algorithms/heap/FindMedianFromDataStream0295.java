package io.github.jeffreyxiecn.algorithms.heap;

import java.util.PriorityQueue;

public class FindMedianFromDataStream0295 {
  PriorityQueue<Integer>
      maxHeap; // root is the biggest element. maxHeap's size is equal to or 1 more than minHeap's
               // size
  PriorityQueue<Integer> minHeap; // root is the smallest element

  /** initialize your data structure here. */
  public FindMedianFromDataStream0295() {
    maxHeap = new PriorityQueue<>((a, b) -> (b - a));
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    if (maxHeap.size() == 0) {
      maxHeap.add(num);
      return;
    }

    if (maxHeap.size() > minHeap.size()) {
      if (num >= maxHeap.peek()) {
        minHeap.add(num);
      } else {
        minHeap.add(maxHeap.remove());
        maxHeap.add(num);
      }
    } else { // maxHeap.size() == minHeap.size(), add one to maxHeap
      if (num > minHeap.peek()) {
        maxHeap.add(minHeap.remove());
        minHeap.add(num);
      } else {
        maxHeap.add(num);
      }
    }
  }

  public double findMedian() {
    if (maxHeap.size() == minHeap.size()) {
      return (maxHeap.peek() + minHeap.peek()) / 2.0;
    } else {
      return maxHeap.peek();
    }
  }
}

/**
 *
 *
 * <pre>
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 * </pre>
 */
