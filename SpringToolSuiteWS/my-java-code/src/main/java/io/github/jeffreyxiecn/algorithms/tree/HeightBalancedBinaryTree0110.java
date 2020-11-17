package io.github.jeffreyxiecn.algorithms.tree;

public class HeightBalancedBinaryTree0110 {
  // Definition for a binary tree node.
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

  public boolean isBalanced(TreeNode root) {
    return treeHeight(root) >= 0;
  }

  private int treeHeight(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int left = treeHeight(node.left);
    if (left == -1) {
      return -1;
    }

    int right = treeHeight(node.right);
    if (right == -1) {
      return -1;
    }

    if (Math.abs(left - right) > 1) {
      return -1;
    }

    return 1 + Math.max(left, right);
  }
}
