package io.github.jeffreyxiecn.algorithms.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUcache0146 {
  class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
      prev = null;
      next = null;
    }
  }

  private Node head; // head of the doubly linked list
  private Node tail; // tail of the doubly linked list
  private int size;
  private int capacity;
  private Map<Integer, Node> map;

  public LRUcache0146(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.head = null;
    this.tail = null;
    this.map = new HashMap<>();
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      // key is accessed, need to set as the new head
      Node node = map.get(key);
      setNodeAsNewHead(node);
      return node.value;
    }

    return -1;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.value = value;
      // key is accessed, need to set as the new head
      setNodeAsNewHead(node);
    } else {
      // a new key, insert as the head of the doubly linked list
      if (size == capacity) {
        // evict the LRU
        Node temp = tail.prev;
        if (temp == null) {
          // this could happen if the capacity is 1
          map.remove(tail.key);
          head = null;
          tail = null;
        } else {
          temp.next = null;
          tail.prev = null;
          map.remove(tail.key);
          tail = temp;
        }
        size--;
      }

      Node newHead = new Node(key, value);
      if (head == null) {
        head = newHead;
        tail = newHead;
      } else {
        newHead.next = head;
        head.prev = newHead;
        head = newHead;
      }

      map.put(key, newHead);
      size++;
    }
  }

  private void setNodeAsNewHead(Node node) {
    if (head != node) {
      Node prev = node.prev;
      Node next = node.next;
      prev.next = next;
      if (next != null) {
        next.prev = prev;
      } else {
        tail = prev;
      }

      node.prev = null;
      node.next = head;
      head.prev = node;
      head = node;
    }
  }
}

/**
 *
 *
 * <pre>
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * </pre>
 */
