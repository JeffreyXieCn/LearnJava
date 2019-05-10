package io.github.jeffreyxiecn.algorithms.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * A generic Binary Search Tree
 *
 * @author Jeffrey XIE
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
  private TreeNode<E> root;

  public Optional<TreeNode<E>> getRoot() {
    return Optional.ofNullable(root);
  }

  public void add(E value) {
    root = addRecursive(root, value);
  }

  private TreeNode<E> addRecursive(TreeNode<E> current, E value) {
    if (current == null) {
      return new TreeNode<>(value);
    }

    if (value.compareTo(current.value) < 0) {
      current.left = addRecursive(current.left, value);
    } else if (value.compareTo(current.value) > 0) {
      current.right = addRecursive(current.right, value);
    } else {
      return current;
    }

    return current;
  }

  public boolean containsNode(E value) {
    return containsNodeRecursive(root, value);
  }

  private boolean containsNodeRecursive(TreeNode<E> current, E value) {
    if (current == null) {
      return false;
    }

    if (value.compareTo(current.value) == 0) {
      return true;
    } else if (value.compareTo(current.value) < 0) {
      return containsNodeRecursive(current.left, value);
    } else {
      return containsNodeRecursive(current.right, value);
    }

    //    return value < current.value
    //        ? containsNodeRecursive(current.left, value)
    //        : containsNodeRecursive(current.right, value);
  }

  public void delete(E value) {
    root = deleteRecursive(root, value);
  }

  private TreeNode<E> deleteRecursive(TreeNode<E> current, E value) {
    if (current == null) {
      return null;
    }

    if (value.compareTo(current.value) == 0) {
      if (current.left == null && current.right == null) {
        current = null;
        return null;
      } else if (current.left == null) {
        TreeNode<E> right = current.right;
        current = null;
        return right;
      } else if (current.right == null) {
        TreeNode<E> left = current.left;
        current = null;
        return left;
      } else {
        // has both left and right child, replace it with the smallest TreeNode on the right branch,
        // which will be either a leaf TreeNode, or a TreeNode with only right child
        E smallestValue = findSmallestValue(current.right);
        current.value = smallestValue;
        current.right = deleteRecursive(current.right, smallestValue);
        return current;
      }

    } else if (value.compareTo(current.value) < 0) {
      current.left = deleteRecursive(current.left, value);
    } else {
      current.right = deleteRecursive(current.right, value);
    }

    return current;
  }

  private E findSmallestValue(TreeNode<E> root) {
    return root.left == null ? root.value : findSmallestValue(root.left);
  }

  public void traverseInOrder() {
    traverseInOrderRecursive(root);
  }

  private void traverseInOrderRecursive(TreeNode<E> current) {
    if (current != null) {
      traverseInOrderRecursive(current.left);
      System.out.print(" " + current.value);
      traverseInOrderRecursive(current.right);
    }
  }

  public List<E> getNodeValuesInOrder() {
    List<E> list = new ArrayList<>();
    getNodeValuesInOrderRecursive(root, list);
    return list;
  }

  private void getNodeValuesInOrderRecursive(TreeNode<E> current, List<E> list) {
    if (current != null) {
      getNodeValuesInOrderRecursive(current.left, list);
      list.add(current.value);
      getNodeValuesInOrderRecursive(current.right, list);
    }
  }

  public List<E> getNodeValuesPreOrder() {
    List<E> list = new ArrayList<>();
    getNodeValuesPreOrderRecursive(root, list);
    return list;
  }

  private void getNodeValuesPreOrderRecursive(TreeNode<E> current, List<E> list) {
    if (current != null) {
      list.add(current.value);
      getNodeValuesPreOrderRecursive(current.left, list);
      getNodeValuesPreOrderRecursive(current.right, list);
    }
  }

  public List<E> getNodeValuesPostOrder() {
    List<E> list = new ArrayList<>();
    getNodeValuesPostOrderRecursive(root, list);
    return list;
  }

  private void getNodeValuesPostOrderRecursive(TreeNode<E> current, List<E> list) {
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
  public List<E> getNodeValuesLevelOrder() {
    List<E> list = new ArrayList<>();
    if (root != null) {
      Queue<TreeNode<E>> queue = new LinkedList<>();
      queue.add(root);
      TreeNode<E> current;
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

class TreeNode<E> {
  E value;
  TreeNode<E> left;
  TreeNode<E> right;

  TreeNode(E value) {
    this.value = value;
    left = null;
    right = null;
  }
}
