package io.github.jeffreyxiecn.algorithms.leetcode;

/* Definition for a binary tree node. */
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class FlipEquivalentBinaryTrees {

  public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) {
      return true;
    } else if (root1 == null || root2 == null) {
      return false;
    }

    if (root1.val != root2.val) {
      return false;
    }

    if (root1.left != null) {
      if (root2.left != null) {
        if (root1.left.val == root2.left.val) {
          // suppose on this level it is not flipped
          return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        } else {
          // suppose on this level it is flipped
          return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
        }
      } else { // root2.left == null
        return flipEquiv(root1.left, root2.right);
      }
    } else if (root1.right != null) {
      if (root2.right != null) {
        if (root1.right.val == root2.right.val) {
          // suppose on this level it is not flipped
          return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        } else {
          // suppose on this level it is flipped
          // return flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right);
          return false;
        }
      } else { // root2.right == null
        return flipEquiv(root1.right, root2.left);
      }

    } else if (root2.left == null && root2.right == null) { // root1 is a leaf node
      return true;
    } else {
      return false;
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    TreeNode ltn5 = new TreeNode(5);
    ltn5.left = new TreeNode(7);
    ltn5.right = new TreeNode(8);
    TreeNode ltn2 = new TreeNode(2);
    ltn2.left = new TreeNode(4);
    ltn2.right = ltn5;
    TreeNode ltn3 = new TreeNode(3);
    ltn3.left = new TreeNode(6);
    TreeNode root1 = new TreeNode(1);
    root1.left = ltn2;
    root1.right = ltn3;

    TreeNode rtn5 = new TreeNode(5);
    rtn5.left = new TreeNode(8);
    rtn5.right = new TreeNode(7);
    TreeNode rtn2 = new TreeNode(2);
    rtn2.left = new TreeNode(4);
    rtn2.right = rtn5;
    TreeNode rtn3 = new TreeNode(3);
    rtn3.right = new TreeNode(6);
    TreeNode root2 = new TreeNode(1);
    root2.left = rtn3;
    root2.right = rtn2;

    System.out.println(flipEquiv(root1, root2));
  }
}
