package io.github.jeffreyxiecn.algorithms.array;

import java.util.Arrays;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
import java.util.List;
import java.util.stream.Collectors;
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class ChangeHouseStates {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  public List<Integer> cellCompete(int[] states, int days) {
    // WRITE YOUR CODE HERE
    if (states.length < 2) {
      return Arrays.asList(0);
    }

    int[] newState = states;
    for (int i = 1; i <= days; i++) {
      newState = changeState(newState);
    }

    //    List<Integer> result = new ArrayList<>();
    //    for (int element : newState) {
    //      result.add(element);
    //    }

    return Arrays.stream(newState).boxed().collect(Collectors.toList());
    //    return result;
  }
  // METHOD SIGNATURE ENDS

  private int[] changeState(int[] states) {
    int[] newState = new int[states.length];
    // newState[0] = states[1] == 1 ? 1 : 0;
    newState[0] = states[1] ^ 0;
    // newState[states.length - 1] = states[states.length - 2] == 1 ? 1 : 0;
    newState[states.length - 1] = states[states.length - 2] ^ 0;

    for (int i = 1; i < states.length - 1; i++) {
      // newState[i] = states[i - 1] == states[i + 1] ? 0 : 1;
      newState[i] = states[i - 1] ^ states[i + 1];
    }

    return newState;
  }
}
