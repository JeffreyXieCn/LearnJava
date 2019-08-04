package io.github.jeffreyxiecn.algorithms.heap;

public interface Heap {
  int size();

  void add(int num);

  int peek();

  int poll();
}
