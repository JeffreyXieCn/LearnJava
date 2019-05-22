package io.github.jeffreyxiecn.algorithms.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class FloatBstTest {
  BST<Float> bt;

  @Before
  public void setUp() throws Exception {
    bt = new BST<>();
    bt.add(6.1F);
    bt.add(4.2F);
    bt.add(8.3F);
    bt.add(3.4F);
    bt.add(5.5F);
    bt.add(7.6F);
    bt.add(9.7F);

    bt.traverseInOrder();
    System.out.println("\n");
  }

  @Test
  public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {
    assertTrue(bt.containsNode(6.1F));
    assertTrue(bt.containsNode(4.2F));

    assertFalse(bt.containsNode(1F));
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesInOrder_ThenReturnValuesInOrder() {
    List<Float> values = bt.getNodeValuesInOrder();
    List<Float> expected = Arrays.asList(3.4F, 4.2F, 5.5F, 6.1F, 7.6F, 8.3F, 9.7F);
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
    assertTrue(bt.containsNode(9.7F));
    bt.delete(9.7F);
    assertFalse(bt.containsNode(9.7F));
  }

  @Test
  public void givenABinaryTree_WhenDeletingRoot_ThenReplaceRootWithSamllestValueOnRightBranch() {
    assertTrue(bt.containsNode(6.1F));
    bt.delete(6.1F);
    assertTrue(bt.getRoot().isPresent());
    assertEquals(7.6F, bt.getRoot().get().value.floatValue(), 0.01F);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesPreOrder_ThenReturnValuesPreOrder() {
    List<Float> values = bt.getNodeValuesPreOrder();
    List<Float> expected = Arrays.asList(6.1F, 4.2F, 3.4F, 5.5F, 8.3F, 7.6F, 9.7F);
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesPostOrder_ThenReturnValuesPreOrder() {
    List<Float> values = bt.getNodeValuesPostOrder();
    List<Float> expected = Arrays.asList(3.4F, 5.5F, 4.2F, 7.6F, 9.7F, 8.3F, 6.1F);
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesLevelOrder_ThenReturnValuesLevelOrder() {
    List<Float> values = bt.getNodeValuesLevelOrder();
    List<Float> expected = Arrays.asList(6.1F, 4.2F, 8.3F, 3.4F, 5.5F, 7.6F, 9.7F);
    assertEquals(expected, values);
  }
}
