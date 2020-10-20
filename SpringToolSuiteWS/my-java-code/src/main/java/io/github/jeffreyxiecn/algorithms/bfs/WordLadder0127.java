package io.github.jeffreyxiecn.algorithms.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder0127 {
  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (wordList == null || wordList.size() == 0) {
      return 0;
    }

    Map<String, Boolean> mark = new HashMap<>();
    for (String str : wordList) {
      mark.put(str, false);
    }

    Queue<String> queue = new LinkedList<>();
    queue.add(beginWord);
    mark.put(beginWord, false);
    int steps = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      steps++;
      while (size-- > 0) {
        String cur = queue.poll();
        if (cur.equals(endWord)) {
          return steps;
        }

        if (mark.get(cur) == true) {
          continue;
        }

        for (String word : wordList) {
          if (mark.get(word) == true) {
            continue;
          }
          if (canTransform(cur, word)) {
            queue.add(word);
          }
        }

        mark.put(cur, true);
      }
    }

    return 0;
  }

  public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
    if (wordList == null || wordList.size() == 0) {
      return 0;
    }

    Set<String> remainingWords = new HashSet<>(wordList);

    Queue<String> queue = new LinkedList<>();
    queue.add(beginWord);
    remainingWords.add(beginWord);
    int steps = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      steps++;
      while (size-- > 0) {
        String cur = queue.poll();
        if (cur.equals(endWord)) {
          return steps;
        }

        if (!remainingWords.contains(cur)) {
          continue;
        }

        for (String word : remainingWords) {
          if (canTransform(cur, word)) {
            queue.add(word);
          }
        }

        remainingWords.remove(cur);
      }
    }

    return 0;
  }

  private static boolean canTransform(String w1, String w2) {
    int diffCnt = 0;
    for (int i = 0; i < w1.length(); i++) {
      if (w1.charAt(i) != w2.charAt(i)) {
        diffCnt++;
        if (diffCnt > 1) {
          return false;
        }
      }
    }

    return diffCnt == 1;
  }
}
