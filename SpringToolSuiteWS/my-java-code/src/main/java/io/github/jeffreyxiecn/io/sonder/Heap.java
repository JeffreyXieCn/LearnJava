package io.github.jeffreyxiecn.io.sonder;

import java.util.List;

public interface Heap<E extends Comparable<E>> {
  int size();

  void add(E ele);

  E peek();

  E poll();

  List<E> toList();
}
