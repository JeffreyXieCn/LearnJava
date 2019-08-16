package io.github.jeffreyxiecn.algorithms.heap;

public class MedianNumberTracker {
  // maintain the heaps so that maxHeap.size() - minHeap.size() equals 0 or 1.
  // and maxHeap.peek() <= minHeap.peek()
  private Heap minHeap;
  private Heap maxHeap;

  public MedianNumberTracker() {
    minHeap = new MinHeap();
    maxHeap = new MaxHeap();
  }

  public float addRandomNumber(int num) {
    try {
      if (maxHeap.size() == 0) {
        maxHeap.add(num);
        return num;
      }

      if (num >= maxHeap.peek()) {
        if (minHeap.size() < maxHeap.size()) {
          minHeap.add(num);
          return (maxHeap.peek() + minHeap.peek()) / 2.0f;
        } else {
          // minHeap.size() == maxHeap.size()
          if (num < minHeap.peek()) {
            maxHeap.add(num);
          } else {
            maxHeap.add(minHeap.poll()); // move the smallest from minHeap to maxHeap
            minHeap.add(num);
          }
          return maxHeap.peek();
        }
      } else {
        if (maxHeap.size() == minHeap.size()) {
          maxHeap.add(num);
          return maxHeap.peek();
        } else {
          // maxHeap.size() - minHeap.size() == 1
          minHeap.add(maxHeap.poll()); // move the biggest from maxHeap to minHeap
          maxHeap.add(num);
          return (maxHeap.peek() + minHeap.peek()) / 2.0f;
        }
      }
    } finally {
      assert maxHeap.size() == minHeap.size() || maxHeap.size() == minHeap.size() + 1
          : "maxHeap.size() is neither the same as minHeap.size(), nor it's minHeap.size() + 1";
    }
  }
}
