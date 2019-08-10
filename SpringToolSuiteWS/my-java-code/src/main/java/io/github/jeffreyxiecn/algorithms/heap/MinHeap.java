package io.github.jeffreyxiecn.algorithms.heap;

import java.util.Arrays;

public class MinHeap implements Heap {
  private int capacity = 10;
  private int size = 0;

  private int[] items = new int[capacity];

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

  private int leftChild(int index) {
    return items[getLeftChildIndex(index)];
  }

  private int rightChild(int index) {
    return items[getRightChildIndex(index)];
  }

  private int parent(int index) {
    return items[getParentIndex(index)];
  }

  private void swap(int indexOne, int indexTwo) {
    int temp = items[indexOne];
    items[indexOne] = items[indexTwo];
    items[indexTwo] = temp;
  }

  private void ensureExtraCapacity() {
    if (size == capacity) {
      System.out.println("****Capacity doubled****");
      capacity *= 2;
      items = Arrays.copyOf(items, capacity);
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int peek() {
    if (size == 0) {
      throw new IllegalStateException();
    }

    return items[0];
  }

  @Override
  public int poll() {
    if (size == 0) {
      throw new IllegalStateException();
    }

    final int item = items[0];
    items[0] = items[size - 1];
    size--;
    heapifyDown();
    return item;
  }

  @Override
  public void add(int item) {
    ensureExtraCapacity();
    items[size] = item;
    size++;
    heapifyUp();
  }

  private void heapifyUp() {
    int index = size - 1;
    while (hasParent(index) && items[index] < parent(index)) {
      swap(index, getParentIndex(index));
      index = getParentIndex(index);
    }
  }

  private void heapifyDown() {
    int index = 0;
    while (hasLeftChild(index)) {
      int smallerChildIndex = getLeftChildIndex(index);
      if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
        smallerChildIndex = getRightChildIndex(index);
      }

      if (items[index] <= items[smallerChildIndex]) {
        break;
      } else {
        swap(index, smallerChildIndex);
      }
      index = smallerChildIndex;
    }
  }

  //  private void heapifyDown() {
  //    int index = 0;
  //    while (hasLeftChild(index) && items[index] > leftChild(index)
  //        || hasRightChild(index) && items[index] > rightChild(index)) {
  //      if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
  //        swap(index, getRightChildIndex(index));
  //        index = getRightChildIndex(index);
  //      } else {
  //        swap(index, getLeftChildIndex(index));
  //        index = getLeftChildIndex(index);
  //      }
  //    }
  //  }

}
