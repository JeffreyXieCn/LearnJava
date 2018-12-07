package io.github.jeffreyxiecn.algorithms.leetcode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// You are given two non-empty linked lists representing two non-negative integers. The digits are
// stored in reverse order and each of their nodes contain a single digit. Add the two numbers and
// return it as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
// Example:
//
// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 0 -> 8
// Explanation: 342 + 465 = 807.

/** Definition for singly-linked list. */
class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class AddTwoNumbers {
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int sum = l1.val + l2.val;
    boolean aboveTen = sum > 9 ? true : false;
    ListNode head = new ListNode(sum % 10);
    ListNode curNode = head;
    while (l1.next != null && l2.next != null) {
      sum = l1.next.val + l2.next.val;
      if (aboveTen) {
        sum++;
      }
      aboveTen = sum > 9 ? true : false;
      ListNode nextNode = new ListNode(sum % 10);
      curNode.next = nextNode;
      curNode = nextNode;
      l1 = l1.next;
      l2 = l2.next;
    }

    if (l1.next != null) {
      while (l1.next != null) {
        sum = l1.next.val;
        if (aboveTen) {
          sum++;
        }
        aboveTen = sum > 9 ? true : false;
        ListNode nextNode = new ListNode(sum % 10);
        curNode.next = nextNode;
        curNode = nextNode;
        l1 = l1.next;
      }

    } else if (l2.next != null) {
      while (l2.next != null) {
        sum = l2.next.val;
        if (aboveTen) {
          sum++;
        }
        aboveTen = sum > 9 ? true : false;
        ListNode nextNode = new ListNode(sum % 10);
        curNode.next = nextNode;
        curNode = nextNode;
        l2 = l2.next;
      }
    }

    if (aboveTen) {
      curNode.next = new ListNode(1);
    }

    return head;

    /*int left = l1.val;
    int power = 10;
    while(l1.next != null){
        left += power * l1.next.val;
        l1 = l1.next;
        power = 10 * power;
    }

    int right = l2.val;
    power = 10;
    while(l2.next != null){
        right += power * l2.next.val;
        l2 = l2.next;
        power = 10 * power;
    }

    int sum = left + right;
    int digit = sum % 10;
    ListNode head = new ListNode(digit);
    ListNode cursor = head;
    sum /= 10;
    while(sum > 0){
        digit = sum % 10;
        ListNode newNode = new ListNode(digit);
        cursor.next = newNode;
        cursor = newNode;
        sum /= 10;
    }

    return head;
    */
  }

  public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
      int x = (p != null) ? p.val : 0;
      int y = (q != null) ? q.val : 0;
      int sum = carry + x + y;
      carry = sum / 10;
      curr.next = new ListNode(sum % 10);
      curr = curr.next;
      if (p != null) {
        p = p.next;
      }
      if (q != null) {
        q = q.next;
      }
    }
    if (carry > 0) {
      curr.next = new ListNode(carry);
    }
    return dummyHead.next;
  }

  public static void main(String[] args) {
    List<ListNode> nodes = Stream.of(2, 4, 3).map(ListNode::new).collect(Collectors.toList());
    IntStream.range(0, nodes.size() - 1).forEach(i -> nodes.get(i).next = nodes.get(i + 1));
    printList(nodes.get(0));
    List<ListNode> nodes2 = Stream.of(5, 6, 4).map(ListNode::new).collect(Collectors.toList());
    IntStream.range(0, nodes2.size() - 1).forEach(i -> nodes2.get(i).next = nodes2.get(i + 1));
    printList(nodes2.get(0));

    ListNode result = addTwoNumbers(nodes.get(0), nodes2.get(0));
    printList(result);
  }

  private static void printList(ListNode head) {
    System.out.print(head.val);
    while (head.next != null) {
      System.out.print(" -> " + head.next.val);
      head = head.next;
    }
    System.out.println();
  }
}
