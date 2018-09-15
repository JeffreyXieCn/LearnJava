package io.github.jeffreyxiecn.algorithms;

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
    bubbleSort (input, true);
  }

  public static void bubbleSort(int[] input, boolean ascending) {
    boolean swapped = true;
    int inputLength = input.length;
    int temp;
    for (int i = 0; i < inputLength - 1 && swapped; i++) {
      swapped = false;
      for (int j = 1; j < inputLength - i; j++) {
        if (ascending) {
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
  
  public static int[] moveZerosToArrayEnd(int[] input) {
    //Arrays.sort(input);
    Integer[] intArr = Arrays.stream(input).boxed().toArray(Integer[]::new);
    Arrays.sort(intArr, (a, b) -> b == 0 ? -1 : 0); //treat 0 as positive infinity
    //Arrays.sort(intArr, (a, b) -> b.intValue() == 0 ? -1 : 0);
//    Arrays.sort(intArr, (a, b) -> {
//      if (b == 0) {
//        return -1;
//      }
//      else {
//        return 0;
//      }
//     });
    return Arrays.stream(intArr).mapToInt(Integer::intValue).toArray();
    //Arrays.sort(intArr, (a, b) -> b -a);
  }
  
  /**
   * https://leetcode.com/problems/move-zeroes/description/#
   * 
   * @param nums
   */
  public static void moveZerosToArrayEnd2(int[] nums) {
    if(nums.length < 2) {
      return;
    }
    
    int curZeroIdx = nums.length - 1;
    int prevZeroIdx = curZeroIdx;
    while (true) {
      while (prevZeroIdx >= 0 && nums[prevZeroIdx] != 0 ) {
        prevZeroIdx--;
      }
      if (prevZeroIdx < 0) {
        // no more zero in the front of the array
        break;
      }
      
      //move the zero at prevZeroIdx to the end of the array
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
    //int temp;
    for (int lastNonZeroFoundAt = 0, i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        //temp = nums[lastNonZeroFoundAt];
        if(i != lastNonZeroFoundAt) {
          nums[lastNonZeroFoundAt] = nums[i];
          nums[i] = 0;
        }
        lastNonZeroFoundAt++;
        //nums[i] = temp;
      }
    }
  }
}
