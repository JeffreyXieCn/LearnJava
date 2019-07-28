package io.github.jeffreyxiecn.algorithms.heap;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class MaxHeapTest {

  @Test
  public void givenAnArray_whenAddToHeap_thenHeapIsMaintained() {
    int[] arr = new int[] {17, 25, 15, 20, 10};
    int[] maxs = buildMaxsArray(arr);
    System.out.println(Arrays.toString(arr));
    System.out.println(Arrays.toString(maxs));
    MaxHeap heap = new MaxHeap();
    for (int i = 0; i < arr.length; i++) {
      heap.add(arr[i]);
      assertEquals(maxs[i], heap.peek());
    }
  }

  @Test
  public void givenAheap_whenPoll_thenHeapIsMaintained() {
    int[] arr = new int[] {17, 25, 15, 20, 10, 18, 32, 50, 41, 42, 47, 42, 10};
    Integer[] intArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
    Arrays.sort(intArr, Collections.reverseOrder());
    int[] expectedPoll = Arrays.stream(intArr).mapToInt(Integer::intValue).toArray();
    System.out.println(Arrays.toString(arr));
    System.out.println(Arrays.toString(expectedPoll));
    MaxHeap heap = new MaxHeap();
    for (int element : arr) {
      heap.add(element);
    }

    for (int i = 0; i < arr.length; i++) {
      assertEquals(expectedPoll[i], heap.poll());
    }
  }

  private int[] buildMaxsArray(int[] arr) {
    int[] maxs = new int[arr.length];
    maxs[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > maxs[i - 1]) {
        maxs[i] = arr[i];
      } else {
        maxs[i] = maxs[i - 1];
      }
    }

    return maxs;
  }
}
