package io.github.jeffreyxiecn.algorithms;

import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hamcrest.CoreMatchers.equalTo;

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
}
