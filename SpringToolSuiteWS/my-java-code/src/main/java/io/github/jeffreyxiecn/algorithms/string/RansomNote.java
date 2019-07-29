package io.github.jeffreyxiecn.algorithms.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RansomNote {
  private String magazine;
  // Map the word to its frequency
  private Map<String, Integer> magWordsCounter;

  public RansomNote(String magazine) {
    this.magazine = magazine;
    magWordsCounter = countWords(magazine);
  }

  public boolean canForm(String ransonNote) {
    Map<String, Integer> noteWordsCounter = countWords(ransonNote);
    for (Entry<String, Integer> entry : noteWordsCounter.entrySet()) {
      if (!magWordsCounter.containsKey(entry.getKey())
          || magWordsCounter.get(entry.getKey()) < entry.getValue()) {
        return false;
      }
    }

    return true;
  }

  private Map<String, Integer> countWords(String str) {
    // When str is empty string, words.length is 1
    String[] words = str.split("\\W+");
    Map<String, Integer> wordsCounter = new HashMap<>();
    int counter;
    for (String word : words) {
      if (!word.isEmpty()) {
        counter = wordsCounter.getOrDefault(word, 0);
        counter++;
        wordsCounter.put(word, counter);
      }
    }

    return wordsCounter;
  }
}
