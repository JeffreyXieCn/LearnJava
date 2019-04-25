package io.github.jeffreyxiecn.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
  private Node root;

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
