package io.github.jeffreyxiecn.algorithms.graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class DetectCycleInAnUndirectedGraphTest {

  @Test
  void test1() {
    int nodes = 5;
    int[] edges = {0, 1, 2, 3, 3, 4, 4, 2};
    assertTrue(DetectCycleInAnUndirectedGraph.isCyclic(buildAdjacentList(nodes, edges), nodes));
  }

  @Test
  void test2() {
    int nodes = 4;
    int[] edges = {0, 1, 1, 2, 2, 3};
    assertFalse(DetectCycleInAnUndirectedGraph.isCyclic(buildAdjacentList(nodes, edges), nodes));
  }

  @Test
  void test3() {
    int nodes = 7;
    int[] edges = {0, 1, 0, 5, 0, 6, 1, 2, 2, 3, 2, 4};
    assertFalse(DetectCycleInAnUndirectedGraph.isCyclic(buildAdjacentList(nodes, edges), nodes));
  }

  @Test
  void test4() {
    int nodes = 7;
    int[] edges = {0, 1, 0, 5, 0, 6, 1, 2, 2, 3, 2, 4, 4, 5};
    assertTrue(DetectCycleInAnUndirectedGraph.isCyclic(buildAdjacentList(nodes, edges), nodes));
  }

  @Test
  void test5() {
    int nodes = 10;
    int[] edges = {0, 1, 0, 5, 0, 6, 1, 2, 2, 3, 2, 4, 7, 8, 8, 9, 9, 7};
    assertTrue(DetectCycleInAnUndirectedGraph.isCyclic(buildAdjacentList(nodes, edges), nodes));
  }

  @Test
  void test6() {
    int nodes = 10;
    int[] edges = {0, 1, 0, 5, 0, 6, 1, 2, 2, 3, 2, 4, 4, 7, 7, 8, 8, 9, 9, 5};
    assertTrue(DetectCycleInAnUndirectedGraph.isCyclic(buildAdjacentList(nodes, edges), nodes));
  }

  private ArrayList<ArrayList<Integer>> buildAdjacentList(int nodes, int[] edges) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    for (int i = 0; i < nodes; i++) {
      result.add(new ArrayList<>());
    }

    for (int i = 0; i < edges.length - 1; i += 2) {
      result.get(edges[i]).add(edges[i + 1]);
      result.get(edges[i + 1]).add(edges[i]);
    }

    return result;
  }
}
