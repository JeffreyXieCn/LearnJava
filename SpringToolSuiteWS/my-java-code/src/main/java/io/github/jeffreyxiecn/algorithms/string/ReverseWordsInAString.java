package io.github.jeffreyxiecn.algorithms.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseWordsInAString {
  public String reverseWords0(String input) {
    StringBuilder sb = new StringBuilder();
    int n = input.length();
    int start;
    int i = 0;
    while (i < n) {
      while (i < n && Character.isWhitespace(input.charAt(i))) {
        sb.append(input.charAt(i));
        i++;
      }

      if (i < n) {
        start = i; // beginning of a word
        while (i < n && !Character.isWhitespace(input.charAt(i))) {
          i++;
        }

        for (int j = i - 1; j >= start; j--) {
          sb.append(input.charAt(j));
        }
      }
    }
    return sb.reverse().toString();
  }

  /**
   * Best solution, works with all white spaces, and preserve all the white spaces in original
   * string
   *
   * @param input
   * @return
   */
  public String reverseWords2(String input) {
    StringBuilder sb = new StringBuilder();
    char[] chars = input.toCharArray();
    int n = chars.length;

    int start;
    int i = 0;
    while (i < n) {
      while (i < n && Character.isWhitespace(chars[i])) {
        sb.append(chars[i]);
        i++;
      }

      if (i < n) {
        start = i; // beginning of a word
        while (i < n && !Character.isWhitespace(chars[i])) {
          i++;
        }

        for (int j = i - 1; j >= start; j--) {
          sb.append(chars[j]);
        }
      }
    }
    return sb.reverse().toString();
  }

  /**
   * split will preserve the leading white spaces (empty strings), but the trailing empty strings
   * are lost. Use split(delimiter, -1) can preserve trailing empty strings
   *
   * <pre>
   *
   * original:[I am a student]
   * after splitting:[I, am, a, student]
   * result:[student a am I]
   *
   * original:[I  am a   student]
   * after splitting:[I, , am, a, , , student]
   * result:[student   a am  I]
   *
   * original:[   I  am  a   student   ]
   * after splitting:[, , , I, , am  a, , , student]
   * result:[student   am    a  I   ]
   *
   * original:[]
   * after splitting:[]
   * result:[]
   *
   * original:[ ]
   * after splitting:[]
   * result:[]
   *
   * original:[   I]
   * after splitting:[, , , I]
   * result:[I   ]
   *
   * original:[I   ]
   * after splitting:[I]
   * result:[I]
   * </pre>
   *
   * @param original
   * @return
   */
  public static String reverseWords3(String original) {
    String delimiter = " ";
    // String[] words = original.split(delimiter); //same as .split(delimiter, 0)
    String[] words = original.split(delimiter, -1);
    System.out.println("\noriginal:[" + original + "]");
    System.out.println("after splitting:" + Arrays.toString(words));
    List<String> listOfWords = Arrays.asList(words);
    Collections.reverse(listOfWords);
    words = listOfWords.toArray(new String[0]);
    String result = String.join(delimiter, words);
    System.out.println("result:[" + result + "]");
    return result;
  }

  public static String reverseWords4(String original) {
    String delimiter = " ";
    String[] words = original.split(delimiter, -1);
    invertUsingFor(words);
    return String.join(delimiter, words);
  }

  public static String reverseWords5(String original) {
    String delimiter = " ";
    String[] words = original.split(delimiter, -1);
    // words = (String[]) invertUsingStreams(words);
    Object[] invertedWords = invertUsingStreams(words);
    words = Arrays.copyOf(invertedWords, invertedWords.length, String[].class);
    return String.join(delimiter, words);
  }

  /**
   * Best solution if the original contains only words and white spaces, no tabs, \n, \r and etc
   *
   * @param original
   * @return
   */
  public static String reverseWords(String original) {
    String delimiter = " ";
    List<String> listOfWords = Arrays.asList(original.split(delimiter, -1));
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
}
