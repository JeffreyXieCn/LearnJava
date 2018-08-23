package io.github.jeffreyxiecn.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class StringManipulation {

	public static void main(String[] args) {
		String str = "Java   is cool";
		System.out.println(reverseWords(str));
		System.out.println(reverseWords2(str));
		System.out.println(reverseWords3(str));

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
		//words = (String[]) invertUsingStreams(words);
		Object[] invertedWords = invertUsingStreams(words);
		words = Arrays.copyOf(invertedWords, invertedWords.length, String[].class);
		return String.join(delimiter, words);
	}
	
	public static void invertUsingFor(Object[] array) {
		for(int i = 0; i < array.length / 2; i++) {
			Object temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
	}
	
	public static Object[] invertUsingStreams(Object[] array) {
		return IntStream.rangeClosed(1, array.length).mapToObj(i -> array[array.length - i]).toArray();
	}

}
