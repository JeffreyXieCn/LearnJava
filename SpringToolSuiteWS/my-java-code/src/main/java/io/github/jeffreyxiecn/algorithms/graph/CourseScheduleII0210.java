package io.github.jeffreyxiecn.algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class CourseScheduleII0210 {
  /**
   *
   *
   * <pre>
   * Topological order with two Maps and one Queue
   * Time Complexity: O(V + E)
   * Space Complexity: O(V + E)
   * </pre>
   *
   * @param numCourses
   * @param prerequisites
   * @return
   */
  public int[] findOrder(int numCourses, int[][] prerequisites) {

    // the list of courses can be taken after finishing this one
    Map<Integer, List<Integer>> nextCourse = new HashMap<>();

    // how many prerequisites does this course have
    Map<Integer, Integer> inDegree = new HashMap<>();
    // for(int i = 0; i < numCourses; i++){
    //     inDegree.put(i, 0);
    // }

    List<Integer> nextCourses;
    for (int[] prereq : prerequisites) {
      inDegree.put(prereq[0], inDegree.getOrDefault(prereq[0], 0) + 1);

      if (!nextCourse.containsKey(prereq[1])) {
        nextCourses = new ArrayList<>();
        nextCourses.add(prereq[0]);
        nextCourse.put(prereq[1], nextCourses);
      } else {
        nextCourses = nextCourse.get(prereq[1]);
        nextCourses.add(prereq[0]);
      }
    }

    Queue<Integer> queue = new LinkedList<>();
    // for(Map.Entry<Integer, Integer> entry : inDegree.entrySet()){
    //     if(entry.getValue() == 0){
    //         queue.add(entry.getKey());
    //     }
    // }
    for (int i = 0; i < numCourses; i++) {
      if (!inDegree.containsKey(i)) {
        queue.add(i);
      }
    }

    int[] result = new int[numCourses];
    int index = 0;

    while (!queue.isEmpty()) {
      int cur = queue.remove();
      result[index++] = cur;
      List<Integer> next = nextCourse.get(cur);
      if (next != null) {
        for (int course : next) {
          int newInDegree = inDegree.get(course) - 1;
          if (newInDegree == 0) {
            queue.add(course);
          } else {
            inDegree.put(course, newInDegree);
          }
        }
      }
    }

    if (index < numCourses) {
      // impossible to finish all courses
      return new int[0];
    }

    return result;
  }

  /**
   *
   *
   * <pre>
   * 使用 DFS 来实现拓扑排序，使用一个栈存储后序遍历(先遍历我的prerequisites，再遍历我自己)结果，
   * 这个栈的逆序结果就是拓扑排序结果。
   *
   * 证明：对于任何先序关系：v->w，后序遍历结果可以保证 w 先进入栈中，因此栈的逆序结果中 w 会在 v 之前
   * </pre>
   *
   * @param numCourses
   * @param prerequisites
   * @return
   */
  public int[] findOrder2(int numCourses, int[][] prerequisites) {
    List<Integer>[] graphic = new List[numCourses];
    for (int i = 0; i < numCourses; i++) {
      graphic[i] = new ArrayList<>();
    }
    for (int[] pre : prerequisites) {
      graphic[pre[0]].add(pre[1]);
    }
    Stack<Integer> postOrder = new Stack<>();
    boolean[] globalMarked = new boolean[numCourses];
    boolean[] localMarked = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
      if (hasCycle(globalMarked, localMarked, graphic, i, postOrder)) {
        return new int[0];
      }
    }
    int[] orders = new int[numCourses];
    for (int i = numCourses - 1; i >= 0; i--) {
      orders[i] = postOrder.pop();
    }
    return orders;
  }

  private boolean hasCycle(
      boolean[] globalMarked,
      boolean[] localMarked,
      List<Integer>[] graphic,
      int curNode,
      Stack<Integer> postOrder) {

    if (localMarked[curNode]) {
      return true;
    }
    if (globalMarked[curNode]) {
      return false;
    }
    globalMarked[curNode] = true;
    localMarked[curNode] = true;
    for (int nextNode : graphic[curNode]) {
      if (hasCycle(globalMarked, localMarked, graphic, nextNode, postOrder)) {
        return true;
      }
    }
    localMarked[curNode] = false;
    postOrder.push(curNode);
    return false;
  }
}
