package io.github.jeffreyxiecn.algorithms.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * https://www.baeldung.com/java-binary-tree
 *
 * @author xwy52
 */
public class BinarySearchTree {
  private Node root;

  public Optional<Node> getRoot() {
    return Optional.ofNullable(root);
  }

  public void add(int value) {
    root = addRecursive(root, value);
  }

  private Node addRecursive(Node current, int value) {
    if (current == null) {
      return new Node(value);
    }

    if (value < current.value) {
      current.left = addRecursive(current.left, value);
    } else if (value > current.value) {
      current.right = addRecursive(current.right, value);
    } else {
      return current;
    }

    return current;
  }

  public boolean containsNode(int value) {
    return containsNodeRecursive(root, value);
  }

  private boolean containsNodeRecursive(Node current, int value) {
    if (current == null) {
      return false;
    }

    if (current.value == value) {
      return true;
    } else if (current.value > value) {
      return containsNodeRecursive(current.left, value);
    } else {
      return containsNodeRecursive(current.right, value);
    }

    //    return value < current.value
    //        ? containsNodeRecursive(current.left, value)
    //        : containsNodeRecursive(current.right, value);
  }

  public void delete(int value) {
    root = deleteRecursive(root, value);
  }

  private Node deleteRecursive(Node current, int value) {
    if (current == null) {
      return null;
    }

    if (current.value == value) {
      if (current.left == null && current.right == null) {
        current = null;
        return null;
      } else if (current.left == null) {
        Node right = current.right;
        current = null;
        return right;
      } else if (current.right == null) {
        Node left = current.left;
        current = null;
        return left;
      } else {
        // has both left and right child, replace it with the smallest node on the right branch,
        // which will be either a leaf node, or a node with only right child
        int smallestValue = findSmallestValue(current.right);
        current.value = smallestValue;
        current.right = deleteRecursive(current.right, smallestValue);
        return current;
      }

    } else if (current.value > value) {
      current.left = deleteRecursive(current.left, value);
    } else {
      current.right = deleteRecursive(current.right, value);
    }

    return current;
  }

  private int findSmallestValue(Node root) {
    return root.left == null ? root.value : findSmallestValue(root.left);
  }

  public void traverseInOrder() {
    traverseInOrderRecursive(root);
  }

  private void traverseInOrderRecursive(Node current) {
    if (current != null) {
      traverseInOrderRecursive(current.left);
      System.out.print(" " + current.value);
      traverseInOrderRecursive(current.right);
    }
  }

  public List<Integer> getNodeValuesInOrder() {
    List<Integer> list = new ArrayList<>();
    getNodeValuesInOrderRecursive(root, list);
    return list;
  }

  private void getNodeValuesInOrderRecursive(Node current, List<Integer> list) {
    if (current != null) {
      getNodeValuesInOrderRecursive(current.left, list);
      list.add(current.value);
      getNodeValuesInOrderRecursive(current.right, list);
    }
  }

  public List<Integer> getNodeValuesPreOrder() {
    List<Integer> list = new ArrayList<>();
    getNodeValuesPreOrderRecursive(root, list);
    return list;
  }

  private void getNodeValuesPreOrderRecursive(Node current, List<Integer> list) {
    if (current != null) {
      list.add(current.value);
      getNodeValuesPreOrderRecursive(current.left, list);
      getNodeValuesPreOrderRecursive(current.right, list);
    }
  }

  public List<Integer> getNodeValuesPostOrder() {
    List<Integer> list = new ArrayList<>();
    getNodeValuesPostOrderRecursive(root, list);
    return list;
  }

  private void getNodeValuesPostOrderRecursive(Node current, List<Integer> list) {
    if (current != null) {
      getNodeValuesPostOrderRecursive(current.left, list);
      getNodeValuesPostOrderRecursive(current.right, list);
      list.add(current.value);
    }
  }

  /**
   * Breadth-First Search
   *
   * @return
   */
  public List<Integer> getNodeValuesLevelOrder() {
    List<Integer> list = new ArrayList<>();
    if (root != null) {
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);
      Node current;
      while (!queue.isEmpty()) {
        current = queue.remove();
        list.add(current.value);
        if (current.left != null) {
          queue.add(current.left);
        }
        if (current.right != null) {
          queue.add(current.right);
        }
      }
    }

    return list;
  }
}

class Node {
  int value;
  Node left;
  Node right;

  Node(int value) {
    this.value = value;
    left = null;
    right = null;
  }
}
