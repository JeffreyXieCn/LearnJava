package io.github.jeffreyxiecn.algorithms.divideandconquer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueBinarySearchTreesII0095 {
  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public List<TreeNode> generateTrees(int n) {
    if (n < 1) {
      return new ArrayList<>();
    }

    return generateTrees(1, n);
  }

  private List<TreeNode> generateTrees(int begin, int end) {
    List<TreeNode> result = new ArrayList<>();
    if (end < begin) {
      result.add(null);
    }

    // when i is the root, all the smaller numbers begin...i-1 goes to left tree,
    // all the bigger numbers i+1...end goes to left tree, the result will be BST
    for (int i = begin; i <= end; i++) {
      List<TreeNode> leftTrees = generateTrees(begin, i - 1);
      List<TreeNode> rightTrees = generateTrees(i + 1, end);
      for (TreeNode left : leftTrees) {
        for (TreeNode right : rightTrees) {
          TreeNode root = new TreeNode(i, left, right);
          result.add(root);
        }
      }
    }

    return result;
  }

  private Map<Integer[], List<TreeNode>> cache = new HashMap<>();

  public List<TreeNode> generateTrees2(int n) {
    if (n < 1) {
      return new ArrayList<>();
    }

    return generateTrees(1, n);
  }

  /**
   * Use DP to avoid repeated calculation
   *
   * @param begin
   * @param end
   * @return
   */
  private List<TreeNode> generateTrees2(int begin, int end) {
    List<TreeNode> result = new ArrayList<>();
    if (end < begin) {
      result.add(null);
    }

    if (cache.containsKey(new Integer[] {begin, end})) {
      return cache.get(new Integer[] {begin, end});
    }

    for (int i = begin; i <= end; i++) {
      List<TreeNode> leftTrees = generateTrees(begin, i - 1);
      List<TreeNode> rightTrees = generateTrees(i + 1, end);
      for (TreeNode left : leftTrees) {
        for (TreeNode right : rightTrees) {
          TreeNode root = new TreeNode(i, left, right);
          result.add(root);
        }
      }
    }

    cache.put(new Integer[] {begin, end}, result);
    return result;
  }
}
