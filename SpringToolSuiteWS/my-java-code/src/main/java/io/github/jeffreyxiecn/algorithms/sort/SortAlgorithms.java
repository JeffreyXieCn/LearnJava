package io.github.jeffreyxiecn.algorithms.sort;

import java.util.Arrays;

public class SortAlgorithms {

  public static void selectionSort(int[] input) {
    int minIndex;
    int temp;
    int inputLength = input.length;
    for (int i = 0; i < inputLength - 1; i++) {
      minIndex = i;
      for (int j = i + 1; j < inputLength; j++) {
        if (input[j] < input[minIndex]) {
          minIndex = j;
        }
      }
      temp = input[i];
      input[i] = input[minIndex];
      input[minIndex] = temp;
    }
  }

  public static void bubbleSort(int[] input) {
    bubbleSort(input, true);
  }

  public static void bubbleSort(int[] input, boolean ascending) {
    boolean swapped = true;
    int inputLength = input.length;
    int temp;
    for (int i = 0; i < inputLength - 1 && swapped; i++) {
      swapped = false;
      for (int j = 1; j < inputLength - i; j++) {
        if (ascending) { // note i is not used inside the loop
          if (input[j - 1] > input[j]) {
            temp = input[j - 1];
            input[j - 1] = input[j];
            input[j] = temp;
            swapped = true;
          }
        } else {
          if (input[j - 1] < input[j]) {
            temp = input[j - 1];
            input[j - 1] = input[j];
            input[j] = temp;
            swapped = true;
          }
        }
      }
    }
  }

  public static void insertionSort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      // insert array[i] to the right place from 0 to i - 1, which is already sorted
      for (int j = 0; j < i; j++) {
        if (array[j] > array[i]) {
          int temp = array[i];
          for (int k = i; k > j; k--) {
            array[k] = array[k - 1];
          }
          array[j] = temp;
          break;
        }
      }
    }
  }

  public static void countingSort(int[] input, int[] output, int k) {
    int[] c = new int[k + 1];
    for (int e : input) {
      c[e]++;
    }

    for (int i = 1; i <= k; i++) {
      c[i] = c[i - 1] + c[i];
    }

    for (int j = input.length - 1; j >= 0; j--) {
      output[c[input[j]] - 1] = input[j];
      c[input[j]]--;
    }
  }

  public static void radixSort(int[] input, int d) {
    int div = 1;
    int len = input.length;
    for (int i = 0; i < d; i++) {
      // get the array of current digits
      int[] digits = new int[len];
      for (int j = 0; j < len; j++) {
        digits[j] = (input[j] / div) % 10;
      }

      // counting sort the digits array
      int[] c = new int[10];
      for (int dg : digits) {
        c[dg]++;
      }

      for (int k = 1; k < 10; k++) {
        c[k] = c[k - 1] + c[k];
      }

      // sort the original array into output array
      int[] output = new int[len];
      for (int m = len - 1; m >= 0; m--) {
        output[c[digits[m]] - 1] = input[m];
        c[digits[m]]--;
      }

      // assign output to input
      // input = output;
      System.arraycopy(output, 0, input, 0, len);
      div *= 10;
    }
  }

  public static void mergeSort(int[] array) {
    int[] helper = new int[array.length];
    mergeSort(array, helper, 0, array.length - 1);
  }

  private static void mergeSort(int[] array, int[] helper, int low, int high) {
    if (low < high) {
      int middle = (low + high) / 2;
      mergeSort(array, helper, low, middle); // divide into
      mergeSort(array, helper, middle + 1, high); // two sub-problems,
      merge(array, helper, low, middle, high); // then combine
    }
  }

  // array[low...middle] and array[middle + 1...high] is already sorted
  private static void merge(int[] array, int[] helper, int low, int middle, int high) {
    for (int i = low; i <= high; i++) {
      helper[i] = array[i];
    }

    int helperLeft = low;
    int helperRight = middle + 1;
    int current = low;
    while (helperLeft <= middle && helperRight <= high) {
      if (helper[helperLeft] <= helper[helperRight]) {
        array[current] = helper[helperLeft];
        helperLeft++;
      } else {
        array[current] = helper[helperRight];
        helperRight++;
      }
      current++;
    }

    // when the while loop ends, if helperRight <= high (all the elements on the left portion have
    // been examined, the non-examined part in the right portion are already in their final places,
    // nothing needs to be done

    // int remaining = middle - helperLeft;
    for (int j = helperLeft; j <= middle; j++) {
      array[current] = helper[j];
      current++;
    }
  }

  public static void quickSort(int[] array) {
    quickSort(array, 0, array.length - 1);
  }

  private static void quickSort(int[] array, int left, int right) {
    int index = partition(array, left, right);
    // after partition, all the elements in array[left...index-1] are <= the elements in
    // array[index, right]
    if (left < index - 1) {
      quickSort(array, left, index - 1);
    }
    if (index < right) {
      quickSort(array, index, right);
    }
  }

  private static int partition(int[] array, int left, int right) {
    int pivot = array[(left + right) / 2]; // use the middle element as the pivot
    while (left <= right) {
      // Find element on left that should be on right
      while (array[left] < pivot) {
        left++; // not stable sorting
      }
      // Find element on right that should be on left
      while (array[right] > pivot) {
        right--; // not stable sorting
      }

      // Swap elements, and move left and right indices
      if (left <= right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
        left++;
        right--;
      }
    }
    return left;
  }

  public static int binarySearch(int[] a, int x) {
    int low = 0;
    int high = a.length - 1;
    int middle;
    while (low <= high) {
      middle = (low + high) / 2;
      if (a[middle] == x) {
        return middle;
      }
      if (x < a[middle]) {
        high = middle - 1;
      } else {
        low = middle + 1;
      }
    }

    return -1;
  }

  public static int[] moveZerosToArrayEnd(int[] input) {
    // Arrays.sort(input);
    Integer[] intArr = Arrays.stream(input).boxed().toArray(Integer[]::new);
    Arrays.sort(intArr, (a, b) -> b == 0 ? -1 : 0); // treat 0 as positive infinity
    // Arrays.sort(intArr, (a, b) -> b.intValue() == 0 ? -1 : 0);
    // Arrays.sort(intArr, (a, b) -> {
    // if (b == 0) {
    // return -1;
    // }
    // else {
    // return 0;
    // }
    // });
    return Arrays.stream(intArr).mapToInt(Integer::intValue).toArray();
    // Arrays.sort(intArr, (a, b) -> b -a);
  }

  /**
   * https://leetcode.com/problems/move-zeroes/description/#
   *
   * @param nums
   */
  public static void moveZerosToArrayEnd2(int[] nums) {
    if (nums.length < 2) {
      return;
    }

    int curZeroIdx = nums.length - 1;
    int prevZeroIdx = curZeroIdx;
    while (true) {
      while (prevZeroIdx >= 0 && nums[prevZeroIdx] != 0) {
        prevZeroIdx--;
      }
      if (prevZeroIdx < 0) {
        // no more zero in the front of the array
        break;
      }

      // move the zero at prevZeroIdx to the end of the array
      while (prevZeroIdx < curZeroIdx) {
        nums[prevZeroIdx] = nums[prevZeroIdx + 1];
        nums[prevZeroIdx + 1] = 0;
        prevZeroIdx++;
      }
      curZeroIdx--;
      prevZeroIdx--;
    }
  }

  public static void moveZerosToArrayEnd3(int[] nums) {
    int lastNonZeroFoundAt = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        nums[lastNonZeroFoundAt++] = nums[i];
      }
    }

    for (int j = lastNonZeroFoundAt; j < nums.length; j++) {
      nums[j] = 0;
    }
  }

  public static void moveZerosToArrayEnd4Best(int[] nums) {
    // int temp;
    for (int lastNonZeroFoundAt = 0, i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        // temp = nums[lastNonZeroFoundAt];
        if (i != lastNonZeroFoundAt) {
          nums[lastNonZeroFoundAt] = nums[i];
          nums[i] = 0;
        }
        lastNonZeroFoundAt++;
        // nums[i] = temp;
      }
    }
  }
}
