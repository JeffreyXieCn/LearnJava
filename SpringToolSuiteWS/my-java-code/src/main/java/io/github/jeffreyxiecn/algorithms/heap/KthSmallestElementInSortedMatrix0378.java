package io.github.jeffreyxiecn.algorithms.heap;

import java.util.PriorityQueue;
/*
Build a minHeap of elements from the first row.
Do the following operations k-1 times :
Every time when you poll out the root(Top Element in Heap), you need to know the row number and
column number of that element(so we can create a tuple class here), replace that root with the next
element from the same column.
This approach works because the next smallest element must be in the heap, because each element
in the heap is already the smallest one in its own column
 */
public class KthSmallestElementInSortedMatrix0378 {
  class Tuple implements Comparable<Tuple> {
    int row;
    int col;
    int val;

    public Tuple(int r, int c, int v) {
      row = r;
      col = c;
      val = v;
    }

    @Override
    public int compareTo(Tuple that) {
      return this.val - that.val;
    }
  }

  public int kthSmallest(int[][] matrix, int k) {
    PriorityQueue<Tuple> pq = new PriorityQueue<>();
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      pq.add(new Tuple(0, i, matrix[0][i]));
    }

    for (int i = 0; i < k - 1; i++) { // 小根堆，去掉 k - 1 个堆顶元素，此时堆顶元素就是第 k 的数
      Tuple t = pq.remove();
      if (t.row == n - 1) {
        continue;
      }

      pq.add(new Tuple(t.row + 1, t.col, matrix[t.row + 1][t.col]));
    }

    return pq.element().val;
  }

  /*
     * Solution 2 : Binary Search
  We are done here, but let's think about this problem in another way:
  The key point for any binary search is to figure out the "Search Space". For me, I think there are
   two kind of "Search Space" -- index and range(the range from the smallest number to the biggest
   number). Most usually, when the array is sorted in one direction, we can use index as "search
   space", when the array is unsorted and we are going to find a specific number, we can use "range".

  Let me give you two examples of these two "search space"

  index -- A bunch of examples -- https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
  ( the array is sorted)
  range -- https://leetcode.com/problems/find-the-duplicate-number/ (Unsorted Array)
  The reason why we did not use index as "search space" for this problem is the matrix is sorted in
   two directions, we can not find a linear way to map the number and its index.
     */
  public int kthSmallest2(int[][] matrix, int k) {
    int m = matrix.length, n = matrix[0].length;
    int lo = matrix[0][0], hi = matrix[m - 1][n - 1] + 1; // [lo, hi)
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      int count = 0, j = matrix[0].length - 1;
      for (int[] element : matrix) { // scan row by row, for each row, scan from last j column to 0
        while (j >= 0 && element[j] > mid) {
          j--;
        }
        count += (j + 1); // count how many elements are <= mid
      }
      if (count < k) { // the kth element must be in [mid + 1, hi)
        lo = mid + 1;
      } else { // the kth element must be in [Lo, mid)
        hi = mid;
      }
    }
    return lo;
  }

  public int kthSmallest3(int[][] matrix, int k) {
    int m = matrix.length, n = matrix[0].length;
    int lo = matrix[0][0], hi = matrix[m - 1][n - 1];
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cnt = 0;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n && matrix[i][j] <= mid; j++) {
          cnt++;
        }
      }
      if (cnt < k) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return lo;
  }
}
