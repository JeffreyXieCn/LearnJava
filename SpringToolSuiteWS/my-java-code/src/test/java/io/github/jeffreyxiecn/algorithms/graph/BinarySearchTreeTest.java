package io.github.jeffreyxiecn.algorithms.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
  BinarySearchTree bt;

  @Before
  public void setUp() throws Exception {
    bt = new BinarySearchTree();
    bt.add(6);
    bt.add(4);
    bt.add(8);
    bt.add(3);
    bt.add(5);
    bt.add(7);
    bt.add(9);

    bt.traverseInOrder();
    System.out.println("\n");
  }

  @Test
  public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {
    assertTrue(bt.containsNode(6));
    assertTrue(bt.containsNode(4));

    assertFalse(bt.containsNode(1));
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesInOrder_ThenReturnValuesInOrder() {
    List<Integer> values = bt.getNodeValuesInOrder();
    List<Integer> expected = Arrays.asList(3, 4, 5, 6, 7, 8, 9);
    assertEquals(expected, values);
  }
}
