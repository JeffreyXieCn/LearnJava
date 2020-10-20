package io.github.jeffreyxiecn.string;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringManipulation {

  public enum BodyType {
    NOBODY,
    JSON,
    MULTIPART,
    FORMDATA
  }

  public static void main(String[] args) {
    String token = "abcd1234";
    String[] tokens = token.split(" ");
    System.out.println("tokens:" + tokens.length);

    BodyType bodyType = BodyType.MULTIPART;
    System.out.println("Body Type: " + bodyType);
    StringBuilder sb = new StringBuilder("mystr");
    System.out.println("mystr:" + sb);

    System.out.println(new Child());

    String str = "Java   is cool";
    System.out.println(new StringBuilder(str).reverse().toString());
    System.out.println(reverseWords(str));
    System.out.println(reverseWords2(str));
    System.out.println(reverseWords3(str));

    String str2 = "ABCDBAGHC";
    System.out.println(firstNonRepeatingChar(str2));
    System.out.println(firstNonRepeatingChar2(str2));

    str2 = "1230321";
    System.out.println(firstNonRepeatingChar(str2));
    System.out.println(firstNonRepeatingChar2(str2));

    str2 = "ABCHBAGHCG";
    char result = firstNonRepeatingChar(str2);
    if (result == 0) {
      System.out.println("No character in " + str2 + " is non-repeated");
    }

    result = firstNonRepeatingChar2(str2);
    if (result == 0) {
      System.out.println("No character in " + str2 + " is non-repeated");
    }

    ByteOrder byteOrder = ByteOrder.nativeOrder();
    if (byteOrder.equals(ByteOrder.LITTLE_ENDIAN)) {
      System.out.println("This JVM is running on little_endian platform");
    } else {
      System.out.println("This JVM is running on big_endian platform");
    }
  }

  public static String reverseWords(String original) {
    String delimiter = " ";
    String[] words = original.split(delimiter);
    List<String> listOfWords = Arrays.asList(words);
    Collections.reverse(listOfWords);
    words = listOfWords.toArray(new String[0]);
    return String.join(delimiter, words);
  }

  public static String reverseWords2(String original) {
    String delimiter = " ";
    String[] words = original.split(delimiter);
    invertUsingFor(words);
    return String.join(delimiter, words);
  }

  public static String reverseWords3(String original) {
    String delimiter = " ";
    String[] words = original.split(delimiter);
    // words = (String[]) invertUsingStreams(words);
    Object[] invertedWords = invertUsingStreams(words);
    words = Arrays.copyOf(invertedWords, invertedWords.length, String[].class);
    return String.join(delimiter, words);
  }

  public static String reverseWords4(String original) {
    String delimiter = " ";
    List<String> listOfWords = Arrays.asList(original.split(delimiter));
    Collections.reverse(listOfWords);
    return listOfWords.stream().collect(Collectors.joining(delimiter));
  }

  private static void invertUsingFor(Object[] array) {
    for (int i = 0; i < array.length / 2; i++) {
      Object temp = array[i];
      array[i] = array[array.length - 1 - i];
      array[array.length - 1 - i] = temp;
    }
  }

  private static Object[] invertUsingStreams(Object[] array) {
    return IntStream.rangeClosed(1, array.length).mapToObj(i -> array[array.length - i]).toArray();
  }

  /**
   * Given a string, find first non-repeating character in it by doing only one traversal of it. //
   * //For example, // // //Input: //string is ABCDBAGHC // //Output: //The first non-repeating
   * character in the string is D
   *
   * @param str the input
   * @return
   */
  public static char firstNonRepeatingChar(String str) {
    LinkedHashMap<Character, Integer> charCount = new LinkedHashMap<>();
    for (int i = 0; i < str.length(); i++) {
      char curChar = str.charAt(i);
      Integer count = charCount.get(curChar);
      if (count == null) {
        charCount.put(curChar, 1);
      } else {
        charCount.put(curChar, count + 1);
      }
    }

    Iterator<Entry<Character, Integer>> iterator = charCount.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<Character, Integer> curEntry = iterator.next();
      if (curEntry.getValue() == 1) {
        return curEntry.getKey();
      }
    }

    return 0;
  }

  /**
   * This method will search and return the first non-repeating character in the input string.
   *
   * @return The first non-repeating character in the input string. Make this comment long enough to
   *     trigger formatting.
   */
  public static char firstNonRepeatingChar2(String s) {
    // By default, LinkedHashMap maintains insertion order
    Map<Character, Boolean> m = new LinkedHashMap<>();
    for (char c : s.toCharArray()) {
      if (m.containsKey(c)) {
        m.put(c, true);
      } else {
        m.put(c, false);
      }
    }

    for (Map.Entry<Character, Boolean> e : m.entrySet()) {
      if (!e.getValue()) {
        return e.getKey();
      }
    }

    return 0;
  }
}

class Parent {

  public String getBodyAsString() {
    return "Parent body string";
  }

  @Override
  public String toString() {
    return "Body:" + getBodyAsString();
  }
}

class Child extends Parent {

  @Override
  public String getBodyAsString() {
    return "Child body string";
  }
}
