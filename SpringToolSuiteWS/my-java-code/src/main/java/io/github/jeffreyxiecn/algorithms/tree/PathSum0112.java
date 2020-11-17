package io.github.jeffreyxiecn.algorithms.tree;

public class PathSum0112 {
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

  private boolean result = false;

  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }

    pathSum(root, 0, sum);
    return result;
  }

  private void pathSum(TreeNode node, int currSum, int sum) {
    if (node.left == null && node.right == null) {
      if (currSum + node.val == sum) {
        result = true;
      }
      return;
    }

    if (node.left != null && !result) {
      pathSum(node.left, currSum + node.val, sum);
    }

    if (node.right != null && !result) {
      pathSum(node.right, currSum + node.val, sum);
    }
  }

  public boolean hasPathSum2(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }

    pathSum2(root, sum);
    return result;
  }

  private void pathSum2(TreeNode node, int remain) {
    if (node.left == null && node.right == null) {
      if (node.val == remain) {
        result = true;
      }
      return;
    }

    if (node.left != null && !result) {
      pathSum2(node.left, remain - node.val);
    }

    if (node.right != null && !result) {
      pathSum2(node.right, remain - node.val);
    }
  }

  public boolean hasPathSum3(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }
    if (root.left == null && root.right == null && root.val == sum) {
      return true;
    }
    return hasPathSum3(root.left, sum - root.val) || hasPathSum3(root.right, sum - root.val);
  }
}
