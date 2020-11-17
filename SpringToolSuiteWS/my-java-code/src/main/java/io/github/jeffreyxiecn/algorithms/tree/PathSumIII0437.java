package io.github.jeffreyxiecn.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

public class PathSumIII0437 {
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

  private int count = 0;

  public int pathSum(TreeNode root, int sum) {
    List<Integer> list = new ArrayList<>();
    findPathSum(root, list, sum);

    return count;
  }

  private void findPathSum(TreeNode node, List<Integer> list, int sum) {
    if (node == null) {
      return;
    }

    int size = list.size();
    for (int i = 0; i < size; i++) {
      list.set(i, list.get(i) + node.val);
      if (list.get(i) == sum) {
        count++;
      }
    }

    list.add(node.val);
    if (node.val == sum) {
      count++;
    }

    findPathSum(node.left, list, sum);
    findPathSum(node.right, list, sum);

    // backtracking
    list.remove(list.size() - 1);
    for (int i = 0; i < size; i++) {
      list.set(i, list.get(i) - node.val);
    }
  }

  /**
   *
   *
   * <pre>
   * The root (level 1) will be accessed once.
   * Nodes on level 2 will be accessed twice.
   * Nodes on level 3 will be accessed 3 times.
   * ...
   * 1*1 + 2*2 + 4*3 + 8*4 + 16*5 + ... + 2^m * (m + 1)= S
   * 2*1 + 4*2 + 8*3 + 16*4 + ... + 2^m * m + 2^(m+1) * (m + 1) = 2S
   * -S = 1 + 2 + 4 + 8 + 16 + ... + 2^m -  2^(m+1) * (m + 1)
   * S = 2^(m+1) * m + 1
   *
   * If there are N nodes in a balanced tree, then level m = log2(N + 1)
   * So the time complexity is NlogN
   *
   * At any given time, only one path is explored, so the maximum space is the height of the tree,
   * thus the space complexity is O(logN) for a balanced tree
   * </pre>
   *
   * @param root
   * @param sum
   * @return
   */
  public int pathSum2(TreeNode root, int sum) {
    if (root == null) {
      return 0;
    }
    int ret =
        pathSumStartWithRoot(root, sum) + pathSum2(root.left, sum) + pathSum2(root.right, sum);
    return ret;
  }

  private int pathSumStartWithRoot(TreeNode root, int sum) {
    if (root == null) {
      return 0;
    }
    int ret = 0;
    if (root.val == sum) {
      ret++;
    }
    ret +=
        pathSumStartWithRoot(root.left, sum - root.val)
            + pathSumStartWithRoot(root.right, sum - root.val);
    return ret;
  }
}
