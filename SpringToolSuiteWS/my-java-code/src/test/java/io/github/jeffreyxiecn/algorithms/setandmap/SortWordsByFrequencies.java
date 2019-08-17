package io.github.jeffreyxiecn.algorithms.setandmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
// I'm working on a large project, I won't bother to summarize it here, but this section of the
// project is to take a very large document of text (minimum of around 50,000 words (not unique)),
// and output each unique word in order of most used to least used (probably top three will be "a"
// "an" and "the").

// My question is of course, what would be the best sorting algorithm to use? I was reading of
// counting sort, and I like it, but my concern is that the range of values will be too large
// compared to the number of unique words.

// https://stackoverflow.com/questions/954181/most-efficient-sorting-algorithm-for-a-large-set-of-numbers
public class SortWordsByFrequencies {
  public static Map<Integer, Set<String>> countingSort(String input) {
    String[] tokens = input.split("\\W");
    Map<String, Integer> wordFrequency = new HashMap<>();
    int frequency;
    int maxFrequency = 0;
    for (String token : tokens) {
      if (!token.isEmpty()) {
        frequency = wordFrequency.getOrDefault(token, 0);
        frequency++;
        wordFrequency.put(token, frequency);
        if (frequency > maxFrequency) {
          maxFrequency = frequency;
        }
      }
    }

    // wordsOfSameFreq[i] is the set of words that each occurred i times (reverse mapping of
    // wordFrequency
    Set<String>[] wordsOfSameFreq = new Set[maxFrequency + 1];
    for (Entry<String, Integer> entry : wordFrequency.entrySet()) {
      if (wordsOfSameFreq[entry.getValue()] == null) {
        // lazy initialize the set, since some array elements may still be null at the end
        wordsOfSameFreq[entry.getValue()] = new HashSet<>();
      }
      wordsOfSameFreq[entry.getValue()].add(entry.getKey());
    }

    // by default, insertion order is maintained
    System.out.println("---Creating the LinkedHashMap begin---");
    Map<Integer, Set<String>> result = new LinkedHashMap<>();
    for (int i = wordsOfSameFreq.length - 1; i > 0; i--) {
      if (wordsOfSameFreq[i] != null) {
        result.put(i, wordsOfSameFreq[i]);
        System.out.println(i + "\t" + wordsOfSameFreq[i]);
      }
    }
    System.out.println("---Creating the LinkedHashMap end---");

    return result;
  }

  public static Map<Integer, Set<String>> sortWithComparatorToMap(String input) {
    return reduceListToMap(sortWithComparator(input));
  }

  private static Map<Integer, Set<String>> reduceListToMap(ArrayList<Entry<String, Integer>> list) {
    Map<Integer, Set<String>> map = new LinkedHashMap<>();
    //    for (Entry<String, Integer> e : list) {
    //      if (map.containsKey(e.getValue())) {
    //        map.get(e.getValue()).add(e.getKey());
    //      } else {
    //        Set<String> set = new HashSet<>();
    //        set.add(e.getKey());
    //        map.put(e.getValue(), set);
    //      }
    //
    //      // below statement is wrong, since when creating a new HashSet, the put method is not
    // called
    //      // map.getOrDefault(e.getValue(), new HashSet<>()).add(e.getKey());
    //    }

    // since the list is sorted, we can improve the performance to build the map
    int prevFreq = 0;
    for (Entry<String, Integer> e : list) {
      if (e.getValue() != prevFreq) {
        // need to create a new entry in the map
        Set<String> set = new HashSet<>();
        set.add(e.getKey());
        map.put(e.getValue(), set);
        prevFreq = e.getValue();
      } else {
        map.get(e.getValue()).add(e.getKey());
      }
    }

    return map;
  }

  private static ArrayList<Entry<String, Integer>> sortWithComparator(String input) {
    Map<String, Integer> wordFrequency = mapWordToFrequency(input);
    ArrayList<Entry<String, Integer>> list = new ArrayList<>(wordFrequency.entrySet());
    Comparator<Entry<String, Integer>> cmp =
        (entry1, entry2) -> {
          return entry2.getValue() - entry1.getValue();
        };
    Collections.sort(list, cmp);
    System.out.println("--------------------");
    System.out.println(list);
    System.out.println("--------------------");
    return list;
  }

  private static Map<String, Integer> mapWordToFrequency(String input) {
    String[] tokens = input.split("\\W");
    Map<String, Integer> wordFrequency = new HashMap<>();
    int frequency;
    for (String token : tokens) {
      if (!token.isEmpty()) {
        frequency = wordFrequency.getOrDefault(token, 0);
        frequency++;
        wordFrequency.put(token, frequency);
      }
    }

    return wordFrequency;
  }
}
