package io.github.jeffreyxiecn.algorithms.linkedlist;

public class PalindromeLinkedList0234 {
  class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public boolean isPalindrome(ListNode head) {
    if (head == null) {
      return true;
    }

    String str1 = buildString(head);

    ListNode newHead = reverseList(head);
    String str2 = buildString(newHead);

    reverseList(newHead);
    return str1.equals(str2);
  }

  private ListNode reverseList(ListNode head) {
    ListNode pre = null;
    ListNode cur = head;
    ListNode temp;
    while (cur != null) {
      temp = cur.next;
      cur.next = pre;
      pre = cur;
      cur = temp;
    }

    return pre;
  }

  private String buildString(ListNode head) {
    StringBuilder sb = new StringBuilder();
    ListNode cur = head;
    while (cur != null) {
      sb.append(cur.val).append("-");
      cur = cur.next;
    }

    return sb.toString();
  }

  /**
   * 切成两半，把后半段反转，然后比较两半是否相等。
   *
   * @param head
   * @return
   */
  public boolean isPalindrome2(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    if (fast != null) {
      slow = slow.next; // 偶数节点，让 slow 指向下一个节点
    }
    cut(head, slow); // 切成两个链表
    return isEqual(head, reverse(slow));
  }

  private void cut(ListNode head, ListNode cutNode) {
    while (head.next != cutNode) {
      head = head.next;
    }
    head.next = null;
  }

  private ListNode reverse(ListNode head) {
    ListNode newHead = null;
    while (head != null) {
      ListNode nextNode = head.next;
      head.next = newHead;
      newHead = head;
      head = nextNode;
    }
    return newHead;
  }

  private boolean isEqual(ListNode l1, ListNode l2) {
    while (l1 != null && l2 != null) {
      if (l1.val != l2.val) {
        return false;
      }
      l1 = l1.next;
      l2 = l2.next;
    }
    return true;
  }
}
