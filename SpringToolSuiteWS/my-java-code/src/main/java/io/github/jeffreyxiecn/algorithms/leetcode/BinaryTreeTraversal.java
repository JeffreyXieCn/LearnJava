package io.github.jeffreyxiecn.algorithms.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class BinaryTreeTraversal {

  /*Definition for a binary tree node. */
  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public List<Integer> preorderTraversal(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<Integer> result = new ArrayList<>();
    result.add(root.val);
    List<Integer> left = preorderTraversal(root.left);
    result.addAll(left);
    List<Integer> right = preorderTraversal(root.right);
    result.addAll(right);
    return result;
  }

  public List<Integer> preorderTraversalIteratively(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root != null) {
      Deque<TreeNode> stack = new LinkedList<>();
      stack.push(root);

      TreeNode curTop;
      while (!stack.isEmpty()) {
        curTop = stack.pop();
        result.add(curTop.val);
        if (curTop.right != null) {
          stack.push(curTop.right);
        }
        if (curTop.left != null) {
          stack.push(curTop.left);
        }
      }
    }

    return result;
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<Integer> result = new ArrayList<>();
    List<Integer> left = inorderTraversal(root.left);
    result.addAll(left);
    result.add(root.val);
    List<Integer> right = inorderTraversal(root.right);
    result.addAll(right);
    return result;
  }

  public List<Integer> inorderTraversal2(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    helper(root, res);
    return res;
  }

  public void helper(TreeNode root, List<Integer> res) {
    if (root != null) {
      if (root.left != null) {
        helper(root.left, res);
      }
      res.add(root.val);
      if (root.right != null) {
        helper(root.right, res);
      }
    }
  }

  public List<Integer> inorderTraversalIteratively(TreeNode root) { // original tree damaged
    List<Integer> result = new ArrayList<>();
    if (root != null) {
      Deque<TreeNode> stack = new LinkedList<>();
      //      if (root.right != null) {
      //        stack.push(root.right);
      //      }
      stack.push(root);
      //      if (root.left != null) {
      //        stack.push(root.left);
      //      }
      //
      //      // make the root as processed.
      //      root.left = root.right = null;

      TreeNode curTop;
      while (!stack.isEmpty()) {
        curTop = stack.pop();
        if (curTop.left == null && curTop.right == null) {
          // the curTop shouldn't be put back to the stack
          result.add(curTop.val);
          continue;
        }

        if (curTop.right != null) {
          stack.push(curTop.right);
        }
        stack.push(curTop);
        if (curTop.left != null) {
          stack.push(curTop.left);
        }
        curTop.left = curTop.right = null; // mark curTop as processed
      }
    }

    return result;
  }

  public List<Integer> inorderTraversalIteratively2(TreeNode root) { // original tree intact
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
      curr = stack.pop();
      res.add(curr.val);
      curr = curr.right;
    }
    return res;
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    postorderHelper(root, result);
    return result;
  }

  private void postorderHelper(TreeNode root, List<Integer> result) {
    if (root != null) {
      if (root.left != null) {
        postorderHelper(root.left, result);
      }
      if (root.right != null) {
        postorderHelper(root.right, result);
      }
      result.add(root.val);
    }
  }

  public List<Integer> postorderTraversalIteratively(TreeNode root) { // original tree damaged
    List<Integer> result = new ArrayList<>();
    if (root != null) {
      Deque<TreeNode> stack = new LinkedList<>();
      stack.push(root);

      TreeNode curTop;
      while (!stack.isEmpty()) {
        curTop = stack.pop();
        if (curTop.left == null && curTop.right == null) {
          // the curTop shouldn't be put back to the stack
          result.add(curTop.val);
          continue;
        }

        stack.push(curTop);
        if (curTop.right != null) {
          stack.push(curTop.right);
        }
        if (curTop.left != null) {
          stack.push(curTop.left);
        }
        curTop.left = curTop.right = null; // mark curTop as processed
      }
    }

    return result;
  }

  public List<Integer> postorderTraversalIteratively2(TreeNode root) { // original tree intact
    List<Integer> result = new ArrayList<>();
    if (root != null) {
      Deque<TreeNode> stack = new LinkedList<>();
      Set<TreeNode> processed = new HashSet<>();
      stack.push(root);

      TreeNode curTop;
      while (!stack.isEmpty()) {
        curTop = stack.pop();
        if (processed.contains(curTop)) {
          // the curTop shouldn't be put back to the stack
          result.add(curTop.val);
          continue;
        }

        stack.push(curTop);
        if (curTop.right != null) {
          stack.push(curTop.right);
        }
        if (curTop.left != null) {
          stack.push(curTop.left);
        }
        processed.add(curTop); // mark curTop as processed
      }
    }

    return result;
  }

  public List<Integer> postorderTraversalIteratively3(TreeNode root) { // original tree intact
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> output = new LinkedList<>();
    if (root == null) {
      return output;
    }

    stack.add(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pollLast();
      output.addFirst(node.val);
      if (node.left != null) {
        stack.add(node.left);
      }
      if (node.right != null) {
        stack.add(node.right);
      }
    }
    return output;
  }

  public List<List<Integer>> levelOrder(TreeNode root) { // Using two queues
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new ArrayDeque<>();
    Queue<TreeNode> nextLevelQueue = new ArrayDeque<>();
    Queue<TreeNode> temp;
    queue.add(root);
    TreeNode curHead;
    while (!queue.isEmpty()) {
      List<Integer> curLevel = new ArrayList<>();
      while (!queue.isEmpty()) {
        curHead = queue.remove();
        curLevel.add(curHead.val);
        if (curHead.left != null) {
          nextLevelQueue.add(curHead.left);
        }
        if (curHead.right != null) {
          nextLevelQueue.add(curHead.right);
        }
      }
      result.add(curLevel);
      temp = queue;
      queue = nextLevelQueue;
      nextLevelQueue = temp;
    }

    return result;
  }

  public List<List<Integer>> levelOrder2(TreeNode root) { // Using single queue
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    // Queue<TreeNode> queue = new ArrayDeque<>(); //Null elements are prohibited
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    queue.add(null);
    TreeNode curHead;
    while (!queue.isEmpty()) {
      List<Integer> curLevel = new ArrayList<>();
      curHead = queue.remove();
      if (curHead == null) {
        // reached the bottom level
        break;
      }
      while (curHead != null) {
        curLevel.add(curHead.val);
        if (curHead.left != null) {
          queue.add(curHead.left);
        }
        if (curHead.right != null) {
          queue.add(curHead.right);
        }

        curHead = queue.remove();
      }
      queue.add(null); // add null as the delimiter of levels
      result.add(curLevel);
    }

    return result;
  }

  public List<List<Integer>> levelOrder3(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> wrapList = new LinkedList<>();

    if (root == null) {
      return wrapList;
    }

    queue.offer(root);
    while (!queue.isEmpty()) {
      int levelNum = queue.size();
      List<Integer> subList = new LinkedList<>();
      for (int i = 0; i < levelNum; i++) {
        if (queue.peek().left != null) {
          queue.offer(queue.peek().left);
        }
        if (queue.peek().right != null) {
          queue.offer(queue.peek().right);
        }
        subList.add(queue.poll().val);
      }
      wrapList.add(subList);
    }
    return wrapList;
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int depthLeft = maxDepth(root.left);
    int depthRight = maxDepth(root.right);
    return Math.max(depthLeft, depthRight) + 1;
  }

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }

    return isSymmetric(root.left, root.right);
  }

  private boolean isSymmetric(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) {
      return true;
    }

    if (root1 == null || root2 == null || root1.val != root2.val) {
      return false;
    }

    return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
  }

  private boolean isSymmetricIteratively(TreeNode root1, TreeNode root2) {
    Deque<TreeNode> stackLeft = new LinkedList<>();
    stackLeft.push(root1);
    Deque<TreeNode> stackRight = new LinkedList<>();
    stackRight.push(root2);
    TreeNode curLeftTop, curRightTop;
    while (!stackLeft.isEmpty() && !stackRight.isEmpty()) {
      curLeftTop = stackLeft.pop();
      curRightTop = stackRight.pop();
      if (curLeftTop == null && curRightTop == null) {
        continue;
      }

      if (curLeftTop == null || curRightTop == null || curLeftTop.val != curRightTop.val) {
        return false;
      }

      // push left, then right, null included
      stackLeft.push(curLeftTop.left);
      stackLeft.push(curLeftTop.right);

      // push right, then left, null included
      stackRight.push(curRightTop.right);
      stackRight.push(curRightTop.left);
    }

    if (stackLeft.isEmpty() && stackRight.isEmpty()) {
      return true;
    }

    return false;
  }

  public boolean isSymmetric2(TreeNode root) {
    if (root == null) {
      return true;
    }

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root.left);
    q.add(root.right);
    while (!q.isEmpty()) {
      TreeNode t1 = q.poll();
      TreeNode t2 = q.poll();
      if (t1 == null && t2 == null) {
        continue;
      }
      if (t1 == null || t2 == null) {
        return false;
      }
      if (t1.val != t2.val) {
        return false;
      }
      q.add(t1.left);
      q.add(t2.right);
      q.add(t1.right);
      q.add(t2.left);
    }
    return true;
  }

  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }

    if (root.left == null && root.right == null && root.val == sum) {
      return true;
    }

    return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
}
