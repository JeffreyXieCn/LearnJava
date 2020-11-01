package io.github.jeffreyxiecn.algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
import java.util.List;
import java.util.stream.Collectors;
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
/*
 * given a cell, if the neighbours are different, then it becomes 1 the next day, otherwise, it
 * becomes 0. Assume the virtual left most and right most neighbour is 0
 */
public class ChangeHouseStates {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  private int n;

  public List<Integer> cellCompete(int[] states, int days) {
    // WRITE YOUR CODE HERE
    if (states == null || states.length == 0) {
      return new ArrayList<>();
    }

    if (days < 1) {
      return Arrays.stream(states).boxed().collect(Collectors.toList());
    }

    n = states.length;
    if (n == 1) {
      return Arrays.asList(0);
    }

    int[] newStates = new int[n];
    int[] temp;
    for (int i = 1; i <= days; i++) {
      changeState(states, newStates);
      temp = states;
      states = newStates;
      newStates = temp;
    }

    //    List<Integer> result = new ArrayList<>();
    //    for (int element : newState) {
    //      result.add(element);
    //    }

    return Arrays.stream(states).boxed().collect(Collectors.toList());
    //    return result;
  }
  // METHOD SIGNATURE ENDS

  private void changeState(int[] states, int[] newStates) {
    // newState[0] = states[1] == 1 ? 1 : 0;
    newStates[0] = states[1] ^ 0;
    // newState[states.length - 1] = states[states.length - 2] == 1 ? 1 : 0;
    newStates[n - 1] = states[n - 2] ^ 0;

    for (int i = 1; i < n - 1; i++) {
      // newState[i] = states[i - 1] == states[i + 1] ? 0 : 1;
      newStates[i] = states[i - 1] ^ states[i + 1];
    }
  }
}
