package io.github.jeffreyxiecn.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListToBinarySearchTree0109 {
  /** Definition for singly-linked list. */
  class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  /** Definition for a binary tree node. */
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

  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    }

    List<Integer> list = new ArrayList<>();
    ListNode cur = head;
    while (cur != null) {
      list.add(cur.val);
      cur = cur.next;
    }

    TreeNode root = buildTree(list, 0, list.size() - 1);
    return root;
  }

  private TreeNode buildTree(List<Integer> list, int l, int h) {
    if (l > h) {
      return null;
    }

    int m = l + (h - l) / 2;
    TreeNode node = new TreeNode(list.get(m));
    node.left = buildTree(list, l, m - 1);
    node.right = buildTree(list, m + 1, h);

    return node;
  }

  /**
   *
   *
   * <pre>
   * O(1) space solution with fast-slow pointers
   * One root level, traverse n/2 list.
   * One second level, traverse n/4 list twice.
   * One third level, traverse n/8 list 4 times.
   * ...
   * So on each level, traverse n/2 list.
   * And the total level is log2N
   * So time complexity is log2N * N/2 = O(NlogN)
   * </pre>
   *
   * @param head
   * @return
   */
  public TreeNode sortedListToBST2(ListNode head) {
    if (head == null) {
      return null;
    }
    return toBST(head, null);
  }

  public TreeNode toBST(ListNode head, ListNode tail) {
    ListNode slow = head;
    ListNode fast = head;
    if (head == tail) {
      return null;
    }

    while (fast != tail && fast.next != tail) {
      fast = fast.next.next;
      slow = slow.next;
    }
    TreeNode thead = new TreeNode(slow.val);
    thead.left = toBST(head, slow);
    thead.right = toBST(slow.next, tail);
    return thead;
  }

  private ListNode node;

  /**
   * Time complexity: O(n)
   *
   * <p>I think since you use recursive methods call, so the space complexity is O(log n) [which is
   * the depth of stack frames, or in other words the height of the constructed tree.] instead of
   * O(1) since the recursive call stack will also cost space.
   *
   * @param head
   * @return
   */
  public TreeNode sortedListToBST3(ListNode head) {
    if (head == null) {
      return null;
    }

    int size = 0;
    ListNode runner = head;
    node = head;

    while (runner != null) {
      runner = runner.next;
      size++;
    }

    return inorderHelper(0, size - 1);
  }

  /**
   * Build the left subtree, then build myself, then build right subtree, the order of node creation
   * is the same as the order of elements in the linked list.
   *
   * @param start
   * @param end
   * @return
   */
  private TreeNode inorderHelper(int start, int end) {
    if (start > end) {
      return null;
    }

    int mid = start + (end - start) / 2;
    TreeNode left = inorderHelper(start, mid - 1);

    TreeNode treenode = new TreeNode(node.val);
    treenode.left = left;
    node = node.next;

    TreeNode right = inorderHelper(mid + 1, end);
    treenode.right = right;

    return treenode;
  }
}
