package com.basic;

import java.util.Arrays;
import java.util.List;

/**
 * To find the count of strings
 *
 */
public class CountStrings {

	public static void main(String[] args) {
		List<String> strList = Arrays.asList("deepak", "xxx", "", "ajesh", "yyy", "", "dee", "de", "ape" , "deepa");
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
		 * 2# Find the count of non-empty strings
		 * This can be applied to non-empty string too by modifying the filter conditions
		 */
		long nonEmptyStringCount = strList.stream()
				.filter(s -> !s.isEmpty())
				.count();
			System.out.println("Count of non-empty strings :: " + nonEmptyStringCount);
			
		/*
		 * 3# Find the count of non-empty strings starting with "d" and having length > 4
		 * Additional conditions can be introduced by appending them to the filter
		 * Eg. Finding non-empty string starting with "d" and having a length greater than 4
		 */
		long countValuesSatifisfyingCondition 
				= strList.stream()
					.filter(s -> s.startsWith("d") && s.length()>4)
					.count();
		System.out.println("Count of strings larger than 4 characters and beginning with 'd':: " + countValuesSatifisfyingCondition);
		
		/*
		 * 4# Find the count of strings having any of the vowels
		 */
		long countStringWithVowels 
				= strList.stream()
					.filter(s -> s.toLowerCase().matches(".*[aeiou].*"))
					.count();
		System.out.println("Count of strings having any of the vowels :: " + countStringWithVowels);
		
		/*
		 * 5# Find the count of strings having more than one vowel
		 * https://www.javamex.com/tutorials/regular_expressions/repetition.shtml
		 */
		long countMultiVowelStrings
			= strList.stream()
			.filter(s -> s.toLowerCase().matches(".*[aeiou].{2,}.*"))
			.count();
		System.out.println("Count of strings having more than one vowel :: " + countMultiVowelStrings);
	
		/*
		 * 6# Find the count of strings having duplicate vowels (same vowel repeated multiple times)
		 */
		long countStringWithDuplicateVowels
			= strList.stream()
				.filter(s -> s.matches(".*[aeiou].*") && (s.chars().distinct().count()!=s.length()))
				.count();
		System.out.println("Count of strings having duplicate vowels :: " + countStringWithDuplicateVowels);
		
		/*
		 * 7# Compare the list of string with another list
		 * and print the matching count of words
		 */
		List<String> strList2 = Arrays.asList("deepak", "xxx", "new", "non-matching");
		long matchingWords = strList.stream()
										.filter(str -> strList2.contains(str))
										.count();
		System.out.println("Count of matching words :: " + matchingWords);
		
	}
}
