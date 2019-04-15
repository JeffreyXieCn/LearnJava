package io.github.jeffreyxiecn.algorithms;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortAlgorithmsTest {
  private static final Logger LOG = LoggerFactory.getLogger(SortAlgorithmsTest.class);

  @Test
  public void testSelectionSort() {
    // fail("Not yet implemented");
    LOG.debug("Test selection sort");
    int[] input = {10, 8, 99, 7, 1, 5, 88, 9};
    int[] output = {1, 5, 7, 8, 9, 10, 88, 99};
    SortAlgorithms.selectionSort(input);
    assertThat(input, equalTo(output));
  }

  @Test
  public void testInsertionSort() {
    LOG.debug("Test insertion sort");
    int[] input = {10, 8, 99, 7, 1, 5, 88, 9};
    // LOG.debug("input.getClass() = " + input.getClass().getCanonicalName());
    LOG.debug("input=" + Arrays.toString(input));
    int[] sortAsc = input.clone();
    Arrays.sort(sortAsc);
    SortAlgorithms.insertionSort(input);
    assertThat(input, equalTo(sortAsc));
    LOG.debug("Sort result =" + Arrays.toString(input));
  }

  @Test
  public void testCountingSort() {
    LOG.debug("Test counting sort");
    int[] input = {10, 8, 9, 7, 1, 5, 8, 9};
    int[] output = new int[input.length];
    // LOG.debug("input.getClass() = " + input.getClass().getCanonicalName());
    LOG.debug("input=" + Arrays.toString(input));
    int[] sortAsc = input.clone();
    Arrays.sort(sortAsc);
    SortAlgorithms.countingSort(input, output, 10);
    assertThat(output, equalTo(sortAsc));
    LOG.debug("Sort result =" + Arrays.toString(output));
  }

  @Test
  public void testRadixSort() {
    LOG.debug("Test radix sort");
    int[] input = {10, 8, 99, 7, 1, 5, 88, 9};
    LOG.debug("input=" + Arrays.toString(input));
    int[] sortAsc = input.clone();
    Arrays.sort(sortAsc);
    SortAlgorithms.radixSort(input, 2);
    assertThat(input, equalTo(sortAsc));
    LOG.debug("Sort result =" + Arrays.toString(input));

    int[] input2 = {329, 457, 657, 839, 436, 720, 355};
    LOG.debug("input2=" + Arrays.toString(input2));
    int[] sortAsc2 = input2.clone();
    Arrays.sort(sortAsc2);
    SortAlgorithms.radixSort(input2, 3);
    assertThat(input2, equalTo(sortAsc2));
    LOG.debug("Sort result =" + Arrays.toString(input2));
  }

  @Test
  public void testBubbleSort() {
    LOG.debug("Test bubble sort");
    int[] input = {10, 8, 99, 7, 1, 5, 88, 9};
    LOG.debug("input.getClass() = " + input.getClass().getCanonicalName());
    LOG.debug("input=" + input);
    int[] input2 = input; // refer to the same array
    int[] input3 = input.clone(); // clone to a new array with the same contents
    int[] sortAsc = input.clone();
    Arrays.sort(sortAsc);
    SortAlgorithms.bubbleSort(input);
    assertThat(input, equalTo(sortAsc));
    LOG.debug("input2: " + Arrays.toString(input2));
    LOG.debug("input3: " + Arrays.toString(input3));

    List<Integer> expected = Arrays.stream(input3).boxed().collect(Collectors.toList());
    Collections.sort(expected);
    Collections.reverse(expected);
    LOG.debug(expected.toString());
    // LOG.debug("input3: " + Arrays.toString(input3));
    // Integer[] expectedArr = expected.toArray(new Integer[0]);
    int[] sortDesc = expected.stream().mapToInt(i -> i).toArray();
    LOG.debug(Arrays.toString(sortDesc));
    SortAlgorithms.bubbleSort(input3, false);
    assertThat(input3, equalTo(sortDesc));

    // List<Integer> list = Arrays.asList(input3);

    // Integer[] sourceArray = { 0, 1, 2, 3, 4, 5 };
    // List<Integer> targetList = Arrays.asList(sourceArray);

    int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    // Collections.sort(data);
    // To boxed array
    Integer[] what = Arrays.stream(data).boxed().toArray(Integer[]::new);
    Integer[] ever = IntStream.of(data).boxed().toArray(Integer[]::new);
    // int[] back = Arrays.stream(what).mapToInt(i -> i).toArray();
    int[] back = Arrays.stream(what).mapToInt(Integer::intValue).toArray();
    LOG.debug(Arrays.toString(back));
    // Collections.sort(what);
    // To boxed list
    List<Integer> you = Arrays.stream(data).boxed().collect(Collectors.toList());
    List<Integer> like = IntStream.of(data).boxed().collect(Collectors.toList());

    int[] arr = {10, 8, 99, 7, 1, 5, 88, 9};
    int[] expectedDescArr = {99, 88, 10, 9, 8, 7, 5, 1};
    Integer[] intArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
    // Arrays.sort(intArr, Collections.reverseOrder());
    Arrays.sort(intArr, (a, b) -> (b - a));
    // Arrays.sort(intArr, (a, b) -> (b == 1 ? -1 : 0));
    // Arrays.sort(intArr, (a, b) -> (a - b));
    int[] descArr = Arrays.stream(intArr).mapToInt(Integer::intValue).toArray();
    LOG.debug("descArr:" + Arrays.toString(descArr));
    assertThat(descArr, equalTo(expectedDescArr));
  }

  @Test
  public void testMergeSort() {
    LOG.debug("Test merge sort");
    int[] input = {10, 8, 99, 7, 1, 5, 88, 9};
    // LOG.debug("input.getClass() = " + input.getClass().getCanonicalName());
    LOG.debug("input=" + Arrays.toString(input));
    int[] sortAsc = input.clone();
    Arrays.sort(sortAsc);
    SortAlgorithms.mergeSort(input);
    assertThat(input, equalTo(sortAsc));
    LOG.debug("Sort result =" + Arrays.toString(input));
  }

  @Test
  public void testQuickSort() {
    LOG.debug("Test quick sort");
    int[] input = {10, 8, 99, 7, 1, 5, 88, 9};
    // LOG.debug("input.getClass() = " + input.getClass().getCanonicalName());
    LOG.debug("input=" + Arrays.toString(input));
    int[] sortAsc = input.clone();
    Arrays.sort(sortAsc);
    SortAlgorithms.quickSort(input);
    assertThat(input, equalTo(sortAsc));
    LOG.debug("Sort result =" + Arrays.toString(input));
  }

  @Test
  public void testMoveZerosToArrayEnd() {
    int[] input1 = {3, 0, 4, 0, 1, 0, 2, 0};
    int[] expected1 = {3, 4, 1, 2, 0, 0, 0, 0};
    int[] result1 = SortAlgorithms.moveZerosToArrayEnd(input1);
    if (new Integer(0) == 0) {
      LOG.debug("new Integer(0) == 0");
    }
    if (new Integer(0).intValue() == 0) {
      LOG.debug("new Integer(0).intValue == 0");
    }
    assertThat(result1, equalTo(expected1));
    SortAlgorithms.moveZerosToArrayEnd2(input1);
    assertThat(input1, equalTo(expected1));

    int[] input2 = {0, 4, 0, 1, 0, 0, 2};
    int[] input2a = input2.clone();
    int[] expected2 = {4, 1, 2, 0, 0, 0, 0};
    int[] result2 = SortAlgorithms.moveZerosToArrayEnd(input2);
    assertThat(result2, equalTo(expected2));
    SortAlgorithms.moveZerosToArrayEnd2(input2);
    assertThat(input2, equalTo(expected2));
    SortAlgorithms.moveZerosToArrayEnd3(input2a);
    assertThat(input2a, equalTo(expected2));

    int[] input3 = {4, 1, 3, 2};
    int[] expected3 = {4, 1, 3, 2};
    int[] result3 = SortAlgorithms.moveZerosToArrayEnd(input3);
    assertThat(result3, equalTo(expected3));
    SortAlgorithms.moveZerosToArrayEnd2(input3);
    assertThat(input3, equalTo(expected3));
  }
}
