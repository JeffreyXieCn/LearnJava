package io.github.jeffreyxiecn.io.sonder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
  private int capacity = 10;
  private int size = 0;

  private Object[] items = new Object[capacity];

  private int getLeftChildIndex(int parentIndex) {
    return 2 * parentIndex + 1;
  }

  private int getRightChildIndex(int parentIndex) {
    return 2 * parentIndex + 2;
  }

  private int getParentIndex(int childIndex) {
    return (childIndex - 1) / 2;
  }

  private boolean hasLeftChild(int index) {
    return getLeftChildIndex(index) < size;
  }

  private boolean hasRightChild(int index) {
    return getRightChildIndex(index) < size;
  }

  private boolean hasParent(int index) {
    return getParentIndex(index) >= 0;
  }

  private E leftChild(int index) {
    return (E) items[getLeftChildIndex(index)];
  }

  private E rightChild(int index) {
    return (E) items[getRightChildIndex(index)];
  }

  private E parent(int index) {
    return (E) items[getParentIndex(index)];
  }

  private void swap(int indexOne, int indexTwo) {
    Object temp = items[indexOne];
    items[indexOne] = items[indexTwo];
    items[indexTwo] = temp;
  }

  private void ensureExtraCapacity() {
    if (size == capacity) {
      capacity *= 2;
      items = Arrays.copyOf(items, capacity);
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public E peek() {
    if (size == 0) {
      throw new IllegalStateException();
    }

    return (E) items[0];
  }

  @Override
  public E poll() {
    if (size == 0) {
      throw new IllegalStateException();
    }

    Object item = items[0];
    items[0] = items[size - 1];
    size--;
    heapifyDown();
    return (E) item;
  }

  @Override
  public List<E> toList() {
    return Arrays.stream(items).limit(size).map(ele -> (E) ele).collect(Collectors.toList());
  }

  @Override
  public void add(E item) {
    ensureExtraCapacity();
    items[size] = item;
    size++;
    heapifyUp();
  }

  private void heapifyUp() {
    int index = size - 1;
    while (hasParent(index) && ((E) items[index]).compareTo(parent(index)) > 0) {
      swap(index, getParentIndex(index));
      index = getParentIndex(index);
    }
  }

  private void heapifyDown() {
    int index = 0;
    while (hasLeftChild(index)) {
      int biggerChildIndex = getLeftChildIndex(index);
      if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) > 0) {
        biggerChildIndex = getRightChildIndex(index);
      }

      if (((E) items[index]).compareTo((E) items[biggerChildIndex]) >= 0) {
        break;
      } else {
        swap(index, biggerChildIndex);
      }
      index = biggerChildIndex;
    }
  }
}
