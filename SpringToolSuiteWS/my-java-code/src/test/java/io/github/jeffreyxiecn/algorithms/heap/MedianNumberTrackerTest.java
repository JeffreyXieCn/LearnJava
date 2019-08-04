package io.github.jeffreyxiecn.algorithms.heap;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class MedianNumberTrackerTest {
  private MedianNumberTracker tracker;

  @BeforeEach
  void setUp() throws Exception {
    tracker = new MedianNumberTracker();
  }

  @Test
  void givenPredefinedNumbers_whenAddToTracker_thenMedianIsTracked() {
    System.out.println("please enable assert in JVM with -ea(JUnit test case did this by default)");
    float currMedian;
    currMedian = tracker.addRandomNumber(5);
    Assert.assertEquals(5f, currMedian, 0.0f);

    currMedian = tracker.addRandomNumber(3);
    Assert.assertEquals(4f, currMedian, 0.0f);

    currMedian = tracker.addRandomNumber(2);
    Assert.assertEquals(3f, currMedian, 0.0f);

    currMedian = tracker.addRandomNumber(7);
    Assert.assertEquals(4f, currMedian, 0.0f);

    currMedian = tracker.addRandomNumber(13);
    Assert.assertEquals(5f, currMedian, 0.0f);

    currMedian = tracker.addRandomNumber(6);
    Assert.assertEquals(5.5f, currMedian, 0.0f);
  }

  @Test
  void givenRandomlyGeneratedNumbers_whenAddToTracker_thenMedianIsTracked() {
    Random numGenerator = new Random();
    int[] randomInts = numGenerator.ints(20, 0, 100).toArray();
    System.out.println("Random ints:" + Arrays.toString(randomInts));

    float expectedMedian;
    float currMedian;
    for (int i = 0; i < randomInts.length; i++) {
      // note randomInts[i] may be overwritten in method calculateMedian
      currMedian = tracker.addRandomNumber(randomInts[i]);
      expectedMedian = calculateMedian(randomInts, i);
      Assert.assertEquals(expectedMedian, currMedian, 0.0f);
    }
  }

  /**
   * Sort the portion of randomInts[0...i], and return the median number of this portion
   *
   * @param randomInts
   * @param i
   * @return
   */
  private float calculateMedian(int[] randomInts, int i) {
    if (i == 0) {
      return randomInts[i];
    }

    // randomInts[0...(i-1)] is already sorted, using insertion sort to insert randomInts[i]
    int insertionPoint = Arrays.binarySearch(randomInts, 0, i, randomInts[i]);
    if (insertionPoint < 0) {
      // randomInts[i] not found in the sorted array
      insertionPoint = -1 * insertionPoint - 1;
    }

    // shift to the right, starting at insertionPoint
    int temp = randomInts[i];
    System.arraycopy(
        randomInts, insertionPoint, randomInts, insertionPoint + 1, i - insertionPoint);
    randomInts[insertionPoint] = temp;

    // now randonInts[0...i] is sorted
    System.out.println("Sorted array so far:" + Arrays.toString(Arrays.copyOf(randomInts, i + 1)));

    if (i % 2 == 1) {
      // even numbers
      return (randomInts[i / 2] + randomInts[i / 2 + 1]) / 2.0f;
    } else {
      // odd numbers
      return randomInts[i / 2];
    }
  }
}
