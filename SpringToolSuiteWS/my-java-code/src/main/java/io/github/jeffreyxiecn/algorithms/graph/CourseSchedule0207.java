package io.github.jeffreyxiecn.algorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule0207 {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // arr[i] is the list of courses whose prerequisite is course i
    List<Integer>[] arr = new List[numCourses];
    // Map<Integer, Integer> inDegree = new HashMap<>(); // how many prerequisites does a course
    // have
    int[] inDegree = new int[numCourses];
    Arrays.fill(inDegree, 0);

    for (int[] pre : prerequisites) {
      if (arr[pre[1]] == null) {
        arr[pre[1]] = new ArrayList<>();
      }
      arr[pre[1]].add(pre[0]);
      inDegree[pre[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      // if(!inDegree.containsKey(i)){
      if (inDegree[i] == 0) {
        // course i has no prerequisites
        queue.add(i);
      }
    }

    int counter = 0;
    while (!queue.isEmpty()) {
      int cur = queue.remove();
      counter++;
      if (arr[cur] != null) {
        for (int next : arr[cur]) {
          // int newDegree = inDegree.get(next) - 1;
          // inDegree.put(next, newDegree);
          inDegree[next]--;
          if (inDegree[next] == 0) {
            queue.add(next);
          }
        }
      }
    }

    return counter == numCourses;
  }

  public boolean canFinish2(int numCourses, int[][] prerequisites) {
    List<Integer>[] graphic = new List[numCourses];
    for (int i = 0; i < numCourses; i++) {
      graphic[i] = new ArrayList<>();
    }
    for (int[] pre : prerequisites) {
      graphic[pre[0]].add(pre[1]); // graphic[i] is the list of course i's prerequisites
    }
    boolean[] globalMarked = new boolean[numCourses];
    boolean[] localMarked = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
      if (hasCycle(globalMarked, localMarked, graphic, i)) {
        return false;
      }
    }
    return true;
  }

  private boolean hasCycle(
      boolean[] globalMarked, boolean[] localMarked, List<Integer>[] graphic, int curNode) {

    if (localMarked[curNode]) {
      return true;
    }
    if (globalMarked[curNode]) {
      return false;
    }
    globalMarked[curNode] = true;
    localMarked[curNode] = true;
    for (int nextNode : graphic[curNode]) {
      if (hasCycle(globalMarked, localMarked, graphic, nextNode)) {
        return true;
      }
    }
    localMarked[curNode] = false;
    return false;
  }
}
