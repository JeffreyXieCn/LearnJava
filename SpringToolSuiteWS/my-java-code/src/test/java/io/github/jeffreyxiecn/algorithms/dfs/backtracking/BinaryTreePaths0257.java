package io.github.jeffreyxiecn.algorithms.dfs.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryTreePaths0257 {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    dfs(root, new StringBuilder(String.valueOf(root.val)), result);

    return result;
  }

  private void dfs(TreeNode node, StringBuilder prefix, List<String> result) {
    if (node.left == null && node.right == null) {
      result.add(prefix.toString());
    }

    if (node.left != null) {
      int index = prefix.length();
      prefix.append("->").append(node.left.val);
      dfs(node.left, prefix, result);
      prefix.delete(index, prefix.length());
    }

    if (node.right != null) {
      int index = prefix.length();
      prefix.append("->").append(node.right.val);
      dfs(node.right, prefix, result);
      prefix.delete(index, prefix.length());
    }
  }

  public List<String> binaryTreePaths2(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    dfs2(root, String.valueOf(root.val), result);

    return result;
  }

  private void dfs2(TreeNode node, String prefix, List<String> result) {
    if (node.left == null && node.right == null) {
      result.add(prefix);
    }

    if (node.left != null) {
      dfs2(node.left, prefix + "->" + node.left.val, result);
    }

    if (node.right != null) {
      dfs2(node.right, prefix + "->" + node.right.val, result);
    }
  }

  public List<String> binaryTreePaths3(TreeNode root) {
    List<String> paths = new ArrayList<>();
    if (root == null) {
      return paths;
    }
    List<Integer> values = new ArrayList<>();
    backtracking(root, values, paths);
    return paths;
  }

  private void backtracking(TreeNode node, List<Integer> values, List<String> paths) {
    if (node == null) {
      return;
    }
    values.add(node.val);
    if (isLeaf(node)) {
      paths.add(buildPath(values));
    } else {
      backtracking(node.left, values, paths);
      backtracking(node.right, values, paths);
    }
    values.remove(values.size() - 1);
  }

  private boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }

  private String buildPath(List<Integer> values) {
    return values.stream().map(String::valueOf).collect(Collectors.joining("->"));

    //    StringBuilder str = new StringBuilder();
    //    for (int i = 0; i < values.size(); i++) {
    //      str.append(values.get(i));
    //      if (i != values.size() - 1) {
    //        str.append("->");
    //      }
    //    }
    //    return str.toString();
  }
}

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
