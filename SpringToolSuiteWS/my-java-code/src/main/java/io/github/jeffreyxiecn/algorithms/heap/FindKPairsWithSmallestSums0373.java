package io.github.jeffreyxiecn.algorithms.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class FindKPairsWithSmallestSums0373 {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
      return result;
    }

    PriorityQueue<Tuple> pq = new PriorityQueue<>();
    // Set<Integer[]> set = new HashSet<>();
    Set<String> set = new HashSet<>();

    pq.add(new Tuple(0, 0, nums1[0] + nums2[0]));
    set.add(0 + "," + 0);

    int m = nums1.length;
    int n = nums2.length;
    if (k > m * n) {
      k = m * n;
    }

    for (int i = 0; i < k; i++) {
      Tuple t = pq.remove();
      result.add(Arrays.asList(nums1[t.i], nums2[t.j]));

      int nextJ = t.j + 1;
      if (nextJ < n && !set.contains(t.i + "," + nextJ)) {
        pq.add(new Tuple(t.i, nextJ, nums1[t.i] + nums2[nextJ]));
        // set.add(new Integer[]{t.i, nextJ});
        set.add(t.i + "," + nextJ);
      }

      int nextI = t.i + 1;
      if (nextI < m && !set.contains(nextI + "," + t.j)) {
        pq.add(new Tuple(nextI, t.j, nums1[nextI] + nums2[t.j]));
        // set.add(new Integer[]{nextI, t.j});
        set.add(nextI + "," + t.j);
      }
    }

    return result;
  }

  /*
   * Basic idea: Use min_heap to keep track on next minimum pair sum, and we only need to maintain K
   *  possible candidates in the data structure.

  Some observations: For every numbers in nums1, its best partner(yields min sum) always starts from
   nums2[0] since arrays are all sorted; And for a specific number in nums1, its next candidate
   should be [this specific number] + nums2[current_associated_index + 1], unless out of boundary;)

  Here is a simple example demonstrate how this algorithm works.
  https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
   */
  public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
    List<int[]> res = new ArrayList<>();
    if (nums1.length == 0 || nums2.length == 0 || k == 0) {
      return res;
    }

    PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);

    for (int i = 0; i < nums1.length && i < k; i++) {
      que.offer(new int[] {nums1[i], nums2[0], 0});
    }

    while (k-- > 0 && !que.isEmpty()) {
      int[] cur = que.poll();
      res.add(new int[] {cur[0], cur[1]});
      if (cur[2] == nums2.length - 1) {
        continue;
      }
      que.offer(new int[] {cur[0], nums2[cur[2] + 1], cur[2] + 1});
    }
    return res;
  }
}

class Tuple implements Comparable<Tuple> {
  int i, j, sum;

  public Tuple(int x, int y, int s) {
    i = x;
    j = y;
    sum = s;
  }

  @Override
  public int compareTo(Tuple that) {
    return this.sum - that.sum;
  }
}
