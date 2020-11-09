package com.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test2 {

	public static void main(String[] args) {
		
		List<String> fruits = Arrays.asList("Banana", "Grape", 
				"Orange", "Apple", "Grape", "Guava", "Grape", "Banana");
		
		// Store the items along with qty in map
		Map<String, Long> itemWithQty = new HashMap<>();
		itemWithQty = fruits.stream()
						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(itemWithQty);
		
		
		// Sort the map on the basis of key
		Map<String, Long> sortItemsWithKey = new LinkedHashMap<>();
		itemWithQty.entrySet().stream()
					.sorted(Map.Entry.comparingByKey())
					.forEachOrdered(item -> sortItemsWithKey.put(item.getKey(), item.getValue()));
		System.out.println(sortItemsWithKey);
		
		// Sort map on desc order of value
		Map<String,Long> descSortItemsWithValue = new LinkedHashMap<>();
		itemWithQty.entrySet().stream()
					.sorted(Map.Entry.<String,Long>comparingByValue().reversed())
					.forEachOrdered(item -> descSortItemsWithValue.put(item.getKey(), item.getValue()));
		System.out.println(descSortItemsWithValue);
		
		// Duplicate items in list
		itemWithQty.entrySet().stream()
					.filter(item -> item.getValue()>1)
					.forEach(System.out::println);
		
		// Fizz if x%3 is 0 and Buzz if x%5 is 0; FizzBuzz if divisible by both
		IntStream.rangeClosed(0, 100)
			.mapToObj(x -> x%3==0&&x%5!=0 ? "Fizz" : x%5==0 && x%3!=0 ? "Buzz" : x%5==0 && x%3==0 ? "FizzBuzz" : x)
			.forEach(x -> System.out.println(x));
		
		// Find the matching pairs
		findThePairsMatchingSumUsingJava8(new int[] {12, 23, 125, 41, -75, 38, 27, 11}, 50);
		
		List<String> strList = Arrays.asList("deepak", "xxx", "", "ajesh", "yyy", "", "dee", "de", "ape", "deepa", "ddepa");
		
		System.out.println("Find words with vowels");
		strList.stream().filter(word -> word.matches(".*[aeiou].*")).forEach(System.out::println);
		System.out.println("Words with multiple vowels");
		strList.stream().filter(word -> word.matches(".*[aeiou].{2,}.*")).forEach(System.out::println);
		System.out.println("Words with duplicate vowels");
		strList.stream().filter(word -> word.matches(".*[aeiou][aeiou].*")
				&& word.chars().distinct().count() != word.length()).forEach(System.out::println);
	
		System.out.println("Find the largest word from a sentence");
		String sentence = "This is the long sentence from which the longest word needs to be captured";
		String maxLenStr = Stream.of(sentence.split(" ")).max(Comparator.comparingInt(s -> s.toString().length())).get();
		List<String> longSentences = Arrays.stream(sentence.split(" ")).filter(s -> s.length() == maxLenStr.length()).collect(Collectors.toList());
		longSentences.forEach(c -> System.out.println(c));
		String strRev = new StringBuffer(sentence).reverse().toString();
		String testString = "pasdfdddsapdasutyr"; 
		Stream<Character> chStream = testString.chars().mapToObj(c -> (char) c);
		StringBuilder sb = new StringBuilder();
		testString.chars().mapToObj(c -> sb.appendCodePoint(c)).forEach(s -> System.out.println(s));
		
		Stream<String> stream = Stream.of("4", "7", "25", "87");
	    Optional<String> opMax = stream.collect(Collectors.maxBy(String::compareTo));
	    System.out.println("Max value :: " + opMax.get());
	    
	    List<String> listIntegers = Arrays.asList("1", "6", "9", "3", "2", "0", "8", "4", "7", "5");	    
	    System.out.println("Before sorting: " + listIntegers);
	    Collections.sort(listIntegers);	
	    System.out.println("After sorting: " + listIntegers);
	    Collections.reverse(listIntegers);
	    System.out.println("After reversing: " + listIntegers);
	}

	private static void findThePairsMatchingSumUsingJava8(int[] iArray, int matchingSum) {
		Map<Integer,Integer> numPairs = new HashMap<>();
		IntStream.range(0, iArray.length)
			.forEach(i ->
				IntStream.range(0, iArray.length)
					.filter(j -> j!=i && iArray[i]+iArray[j]==matchingSum)
					.forEach(j -> numPairs.put(iArray[i], iArray[j]))
			);
		System.out.println(numPairs);
	}
	
}
