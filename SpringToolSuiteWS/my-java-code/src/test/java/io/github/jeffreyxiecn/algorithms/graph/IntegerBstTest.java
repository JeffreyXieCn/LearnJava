package io.github.jeffreyxiecn.algorithms.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class IntegerBstTest {
  BST<Integer> bt;

  @Before
  public void setUp() throws Exception {
    bt = new BST<>();
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

  @Test
  public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
    assertTrue(bt.containsNode(9));
    bt.delete(9);
    assertFalse(bt.containsNode(9));
  }

  @Test
  public void givenABinaryTree_WhenDeletingRoot_ThenReplaceRootWithSamllestValueOnRightBranch() {
    assertTrue(bt.containsNode(6));
    bt.delete(6);
    assertTrue(bt.getRoot().isPresent());
    assertEquals(7, bt.getRoot().get().value.intValue());
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesPreOrder_ThenReturnValuesPreOrder() {
    List<Integer> values = bt.getNodeValuesPreOrder();
    List<Integer> expected = Arrays.asList(6, 4, 3, 5, 8, 7, 9);
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesPostOrder_ThenReturnValuesPreOrder() {
    List<Integer> values = bt.getNodeValuesPostOrder();
    List<Integer> expected = Arrays.asList(3, 5, 4, 7, 9, 8, 6);
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesLevelOrder_ThenReturnValuesLevelOrder() {
    List<Integer> values = bt.getNodeValuesLevelOrder();
    List<Integer> expected = Arrays.asList(6, 4, 8, 3, 5, 7, 9);
    assertEquals(expected, values);
  }
}
