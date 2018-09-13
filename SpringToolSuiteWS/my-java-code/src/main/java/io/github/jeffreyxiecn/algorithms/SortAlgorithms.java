package io.github.jeffreyxiecn.algorithms;

public class SortAlgorithms {
  
  public static void selectionSort(int[] input) {
    int minIndex, temp;
    for(int i = 0; i < input.length - 1; i++) {
      minIndex = i;
      for(int j = i + 1; j < input.length; j++) {
        if(input[j] < input[minIndex]) {
          minIndex = j;
        }
      }
      temp = input[i];
      input[i] = input[minIndex];
      input[minIndex] = temp;
    }
  }
}
