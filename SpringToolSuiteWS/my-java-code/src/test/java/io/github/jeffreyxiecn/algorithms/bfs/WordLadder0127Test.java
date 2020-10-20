package io.github.jeffreyxiecn.algorithms.bfs;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;

public class WordLadder0127Test {
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(WordLadder0127Test.class);

  @Test
  public void test() {
    String beginWord = "hit";
    String endWord = "cog";
    List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
    log.info("The word list is: " + wordList);

    int result = WordLadder0127.ladderLength(beginWord, endWord, wordList);
    assertEquals(5, result);

    int result2 = WordLadder0127.ladderLength2(beginWord, endWord, wordList);
    assertEquals(5, result2);
  }
}
