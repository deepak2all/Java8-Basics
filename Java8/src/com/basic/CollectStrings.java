package com.basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * To collect strings based on certain criteria
 */
public class CollectStrings {

	public static void main(String[] args) {
		List<String> strList = Arrays.asList("deepak", "xxx", "", "ajesh", "yyy", "", "dee", "de", "ape", "deepa");
		/* 
		 * 1# Find the count of empty strings
		 * KEY STEPS
		 * 1. Convert the list to stream using .stream()
		 * 2. Filter the list using predicate (generated using inbuilt function isEmpty)
		 * 3. Count the filtered response using count() method
		 */
		long emptyStringCount = strList.stream()
			.filter(s -> s.isEmpty())
			.count();
		System.out.println("Count of empty strings :: " + emptyStringCount);
		
		/*
		 * 2# Find the non-empty strings
		 * This can be applied to non-empty string too by modifying the filter conditions
		 */
		List<String> nonEmptyStringCount = strList.stream()
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList());
			System.out.println("Non-empty strings :: " + nonEmptyStringCount);
			
		/*
		 * 3# Find the count of non-empty strings starting with "d" and having length > 4
		 * Additional conditions can be introduced by appending them to the filter
		 * Eg. Finding non-empty string starting with "d" and having a length greater than 4
		 */
		List<String> valuesSatifisfyingCondition 
				= strList.stream()
					.filter(s -> s.startsWith("d") && s.length()>4)
					.collect(Collectors.toList());
			System.out.println("Strings larger than 4 characters and beginning with 'd':: " + valuesSatifisfyingCondition);
		
		/*
		 * 4# Find the count of strings having any of the vowels
		 */
		List<String> stringWithVowels 
				= strList.stream()
					.filter(s -> s.toLowerCase().matches(".*[aeiou].*"))
					.collect(Collectors.toList());
		System.out.println("List of strings having any of the vowels :: " + stringWithVowels);
		
		/*
		 * 5# Find the count of strings having more than one vowel
		 * https://www.javamex.com/tutorials/regular_expressions/repetition.shtml
		 */
		List<String> multiVowelStrings
			= strList.stream()
			.filter(s -> s.toLowerCase().matches(".*[aeiou].{2,}.*"))
			.collect(Collectors.toList());
		System.out.println("List of strings having more than one vowel :: " + multiVowelStrings);
	
		/*
		 * 6# Find the count of strings having duplicate vowels (same vowel repeated multiple times)
		 * "deepak", "dee" and "deepa"
		 * Logic:: Check the length matches with the the count of distinct chars
		 */
		List<String> countStringWithDuplicateVowels
			= strList.stream()
				.filter(s -> s.matches(".*[aeiou].*") && (s.chars().distinct().count()!=s.length()))
				.collect(Collectors.toList());
		System.out.println("List of strings having duplicate vowels :: " + countStringWithDuplicateVowels);
		
		
		/*
		 * 7# Compare the list of string with another list
		 * and print the matching count of words
		 */
		List<String> strList2 = Arrays.asList("deepak", "xxx", "new", "non-matching");
		List<String> matchingWords = strList.stream()
										.filter(str -> strList2.contains(str))
										.collect(Collectors.toList());
		System.out.println("List of matching words :: " + matchingWords);
		
		/*
		 * 8# Find the largest word from a sentence
		 * By default, stream would stop as and when one of the largest word is found
		 */
		String sentence = "This is the long sentence from which the longest word needs to be captured";
		String firstLongestWord = Stream.of(sentence.split(" ")).max(Comparator.comparingInt(s -> s.length())).get();
		List<String> longestWords = Arrays.stream(sentence.split(" "))
						.filter(s -> s.length()==firstLongestWord.length())
						.collect(Collectors.toList());
		System.out.println(longestWords);
		
		/*
		 * 9# Find the largest word from a sentence - updated using METHOD REFERENCE
		 */
		String sentence2 = "This is the long sentence from which the longest word needs to be captured";
		String firstLongestWord2 = Stream.of(sentence2.split(" ")).max(Comparator.comparingInt(String::length)).get();
		List<String> longestWords2 = Arrays.stream(sentence2.split(" "))
						.filter(s -> s.length() == firstLongestWord2.length())
						.collect(Collectors.toList());
		System.out.println(longestWords2);
		
		/*
		 * 10# Find the longest sentence in a paragraph
		 */
		String[] sentences = new String[] {"how are you?", "Where are you going?", "I don't want to get you wrong"};
		String firstLongestSentence = Arrays.stream(sentences).max(Comparator.comparingInt(s -> s.length())).get();
		List<String> longestSentences = Arrays.stream(sentences)
							.filter(s -> s.length() == firstLongestSentence.length())
							.collect(Collectors.toList());
		System.out.println(longestSentences);
		
		/*
		 * 11# Find duplicate words from the list
		 * Logic -> Collectors.groupingBy
		 */
		List<String> items =
                Arrays.asList("apple", "apple", "grapes",
                        "apple", "orange", "banana", "papaya", "papaya");
		Map<String,Long> result = items.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		result.entrySet().stream()
			.filter(x -> x.getValue()>1)
			.forEach(x -> System.out.println(x.getKey()));
		
		/*
		 * 12# Find the longest palindrome
		 */
		String testString = "pasdfdddsapdasutyr"; //pasd
		String reverseStr = new StringBuilder(testString).reverse().toString(); //Reverse the string
		
		Set<String> combinations = new HashSet<>();
		StringBuilder st = new StringBuilder();
		testString.chars()
        .mapToObj(i -> st.appendCodePoint(i))
        .forEach(i -> System.out.println(st.toString()));
		
		StringBuilder stRev = new StringBuilder();
		
		/*while(reverseStr.substring(1).length()>0) {	
			System.out.println(reverseStr.substring(1));
			reverseStr.chars()
	        .mapToObj(i -> stRev.appendCodePoint(i))
	        .forEach(i -> combinations.add(stRev.toString()));
			reverseStr = reverseStr.substring(1);
		}*/
		
		reverseStr.chars()
        .mapToObj(i -> stRev.appendCodePoint(i))
        .forEach(i -> combinations.add(stRev.toString()));
        
		System.out.println(combinations);
		
		combinations.stream()
			.filter(i -> testString.contains(i))
			.forEach(i -> System.out.println(i));
			
	}
	
}
