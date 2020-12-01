package io.github.jeffreyxiecn.algorithms.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * The diameter must be from one leaf node to another leaf node, via a common ancestor. And it
 * equals to the depth of that ancestor's left subtree + right subtree. A node's left subtree depth
 * = 1 + Math.max(left.leftDepth, left.rightDepth)
 */
public class DiameterOfBinaryTree0543 {
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

  public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }

    // map from the TreeNode to the depth of its left subtree and right subtree
    Map<TreeNode, Integer[]> map = new HashMap<>();
    buildDepthMap(root, map);

    int max = 0;
    for (Map.Entry<TreeNode, Integer[]> entry : map.entrySet()) {
      if (entry.getValue()[0] + entry.getValue()[1] > max) {
        max = entry.getValue()[0] + entry.getValue()[1];
      }
    }

    return max;
  }

  private void buildDepthMap(TreeNode node, Map<TreeNode, Integer[]> map) {
    if (node.left == null && node.right == null) {
      map.put(node, new Integer[] {0, 0});
    }

    int leftDepth = 0;
    if (node.left != null) {
      buildDepthMap(node.left, map);
      leftDepth = 1 + Math.max(map.get(node.left)[0], map.get(node.left)[1]);
    }

    int rightDepth = 0;
    if (node.right != null) {
      buildDepthMap(node.right, map);
      rightDepth = 1 + Math.max(map.get(node.right)[0], map.get(node.right)[1]);
    }

    map.put(node, new Integer[] {leftDepth, rightDepth});
  }

  private int max = 0;

  public int diameterOfBinaryTree2(TreeNode root) {
    if (root == null) {
      return 0;
    }

    calcDepth(root);

    return max;
  }

  private int[] calcDepth(TreeNode node) {
    if (node.left == null && node.right == null) {
      return new int[] {0, 0};
    }

    int leftDepth = 0;
    if (node.left != null) {
      int[] result = calcDepth(node.left);
      leftDepth = 1 + Math.max(result[0], result[1]);
    }

    int rightDepth = 0;
    if (node.right != null) {
      int[] result = calcDepth(node.right);
      rightDepth = 1 + Math.max(result[0], result[1]);
    }

    if (leftDepth + rightDepth > max) {
      max = leftDepth + rightDepth;
    }

    return new int[] {leftDepth, rightDepth};
  }
}
