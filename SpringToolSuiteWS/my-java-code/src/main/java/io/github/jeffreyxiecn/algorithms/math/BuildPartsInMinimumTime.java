package io.github.jeffreyxiecn.algorithms.math;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.List;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class BuildPartsInMinimumTime {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  // 2<= numOfParts <= 10^6, 1 <= parts[i] <= 10^6
  int minimumTime(int numOfParts, List<Integer> parts) {
    // WRITE YOUR CODE HERE
    // parts.sort((a, b) -> (a - b));
    parts.sort(null); // the natural order of Integer will be used
    long result = parts.get(0) + parts.get(1);
    long newSize = result;
    for (int i = 2; i < parts.size(); i++) {
      newSize += parts.get(i);
      result += newSize;
    }

    if (result > Integer.MAX_VALUE) {
      result = Integer.MAX_VALUE;
    }

    return (int) result;
  }
  // METHOD SIGNATURE ENDS
}
