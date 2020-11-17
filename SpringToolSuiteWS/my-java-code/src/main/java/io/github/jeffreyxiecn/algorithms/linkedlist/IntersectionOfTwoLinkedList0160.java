package io.github.jeffreyxiecn.algorithms.linkedlist;

public class IntersectionOfTwoLinkedList0160 {
  class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  /**
   * Each value on each linked list is in the range [1, 10^9]
   *
   * @param headA
   * @param headB
   * @return
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    // turn the value in ListA into negative
    flipValues(headA);

    // search ListB to find negative value
    ListNode result = null;
    ListNode cur = headB;
    while (cur != null) {
      if (cur.val < 0) {
        result = cur;
        break;
      }
      cur = cur.next;
    }

    // Store the values in ListA
    flipValues(headA);

    return result;
  }

  private void flipValues(ListNode head) {
    ListNode cur = head;
    while (cur != null) {
      cur.val = cur.val * -1;
      cur = cur.next;
    }
  }

  /**
   * 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
   *
   * <p>当访问 A 链表的指针访问到链表尾部时，令它从链表 B 的头部开始访问链表 B；同样地，当访问 B 链表的指针访问到链表尾部时，令它从链表 A 的头部开始访问链表 A。这样就能控制访问
   * A 和 B 两个链表的指针能同时访问到交点。
   *
   * <p>如果不存在交点，那么 a + b = b + a，以下实现代码中 l1 和 l2 会同时为 null，从而退出循环。
   *
   * @param headA
   * @param headB
   * @return
   */
  public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
    ListNode l1 = headA, l2 = headB;
    while (l1 != l2) {
      l1 = (l1 == null) ? headB : l1.next;
      l2 = (l2 == null) ? headA : l2.next;
    }
    return l1;
  }
}
