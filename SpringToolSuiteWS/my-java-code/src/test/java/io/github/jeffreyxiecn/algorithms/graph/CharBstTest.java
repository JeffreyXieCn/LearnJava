package io.github.jeffreyxiecn.algorithms.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CharBstTest {
  BST<Character> bt;

  @Before
  public void setUp() throws Exception {
    bt = new BST<>();
    bt.add('F');
    bt.add('D');
    bt.add('H');
    bt.add('C');
    bt.add('E');
    bt.add('G');
    bt.add('I');

    bt.traverseInOrder();
    System.out.println("\n");
  }

  @Test
  public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {
    assertTrue(bt.containsNode('F'));
    assertTrue(bt.containsNode('D'));

    assertFalse(bt.containsNode('A'));
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesInOrder_ThenReturnValuesInOrder() {
    List<Character> values = bt.getNodeValuesInOrder();
    List<Character> expected = Arrays.asList('C', 'D', 'E', 'F', 'G', 'H', 'I');
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
    assertTrue(bt.containsNode('I'));
    bt.delete('I');
    assertFalse(bt.containsNode('I'));
  }

  @Test
  public void givenABinaryTree_WhenDeletingRoot_ThenReplaceRootWithSamllestValueOnRightBranch() {
    assertTrue(bt.containsNode('F'));
    bt.delete('F');
    assertTrue(bt.getRoot().isPresent());
    assertEquals('G', bt.getRoot().get().value.charValue());
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesPreOrder_ThenReturnValuesPreOrder() {
    List<Character> values = bt.getNodeValuesPreOrder();
    List<Character> expected = Arrays.asList('F', 'D', 'C', 'E', 'H', 'G', 'I');
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesPostOrder_ThenReturnValuesPreOrder() {
    List<Character> values = bt.getNodeValuesPostOrder();
    List<Character> expected = Arrays.asList('C', 'E', 'D', 'G', 'I', 'H', 'F');
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesLevelOrder_ThenReturnValuesLevelOrder() {
    List<Character> values = bt.getNodeValuesLevelOrder();
    List<Character> expected = Arrays.asList('F', 'D', 'H', 'C', 'E', 'G', 'I');
    assertEquals(expected, values);
  }
}
