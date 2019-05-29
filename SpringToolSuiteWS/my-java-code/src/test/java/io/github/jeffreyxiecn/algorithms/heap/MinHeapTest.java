package io.github.jeffreyxiecn.algorithms.heap;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;

public class MinHeapTest {

  @Test
  public void givenAnArray_whenAddToHeap_thenHeapIsMaintained() {
    int[] arr = new int[] {17, 25, 15, 20, 10};
    int[] mins = buildMinsArray(arr);
    System.out.println(Arrays.toString(arr));
    System.out.println(Arrays.toString(mins));
    MinHeap heap = new MinHeap();
    for (int i = 0; i < arr.length; i++) {
      heap.add(arr[i]);
      assertEquals(mins[i], heap.peek());
    }
  }

  @Test
  public void givenAheap_whenPoll_thenHeapIsMaintained() {
    int[] arr = new int[] {17, 25, 15, 20, 10, 18, 32, 50, 41, 42, 47, 42, 10};
    int[] expectedPoll = Arrays.copyOf(arr, arr.length);
    Arrays.sort(expectedPoll);
    System.out.println(Arrays.toString(arr));
    System.out.println(Arrays.toString(expectedPoll));
    MinHeap heap = new MinHeap();
    for (int element : arr) {
      heap.add(element);
    }

    for (int i = 0; i < arr.length; i++) {
      assertEquals(expectedPoll[i], heap.poll());
    }
  }

  private int[] buildMinsArray(int[] arr) {
    int[] mins = new int[arr.length];
    mins[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < mins[i - 1]) {
        mins[i] = arr[i];
      } else {
        mins[i] = mins[i - 1];
      }
    }

    return mins;
  }
}
