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
public class StringBstTest {
  BST<String> bt;

  @Before
  public void setUp() throws Exception {
    bt = new BST<>();
    bt.add("Facebook");
    bt.add("Dell");
    bt.add("HP");
    bt.add("Cisco");
    bt.add("Ericsson");
    bt.add("Google");
    bt.add("Iphone");

    bt.traverseInOrder();
    System.out.println("\n");
  }

  @Test
  public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {
    assertTrue(bt.containsNode("Facebook"));
    assertTrue(bt.containsNode("Dell"));

    assertFalse(bt.containsNode("Apple"));
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesInOrder_ThenReturnValuesInOrder() {
    List<String> values = bt.getNodeValuesInOrder();
    List<String> expected =
        Arrays.asList("Cisco", "Dell", "Ericsson", "Facebook", "Google", "HP", "Iphone");
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
    assertTrue(bt.containsNode("Iphone"));
    bt.delete("Iphone");
    assertFalse(bt.containsNode("Iphone"));
  }

  @Test
  public void givenABinaryTree_WhenDeletingRoot_ThenReplaceRootWithSamllestValueOnRightBranch() {
    assertTrue(bt.containsNode("Facebook"));
    bt.delete("Facebook");
    assertTrue(bt.getRoot().isPresent());
    assertEquals("Google", bt.getRoot().get().value);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesPreOrder_ThenReturnValuesPreOrder() {
    List<String> values = bt.getNodeValuesPreOrder();
    List<String> expected =
        Arrays.asList("Facebook", "Dell", "Cisco", "Ericsson", "HP", "Google", "Iphone");
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesPostOrder_ThenReturnValuesPreOrder() {
    List<String> values = bt.getNodeValuesPostOrder();
    List<String> expected =
        Arrays.asList("Cisco", "Ericsson", "Dell", "Google", "Iphone", "HP", "Facebook");
    assertEquals(expected, values);
  }

  @Test
  public void givenABinaryTree_WhenGetNodeValuesLevelOrder_ThenReturnValuesLevelOrder() {
    List<String> values = bt.getNodeValuesLevelOrder();
    List<String> expected =
        Arrays.asList("Facebook", "Dell", "HP", "Cisco", "Ericsson", "Google", "Iphone");
    assertEquals(expected, values);
  }
}
