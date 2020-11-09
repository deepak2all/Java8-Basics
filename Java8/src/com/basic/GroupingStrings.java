package com.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * https://mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
 */
public class GroupingStrings {

	public static void main(String[] main) {
		
		List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya", "guava", "grapes", "papaya");
		
		
		Map<String, Long> result = items.stream()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println("Grouping of items :: " + result);
		
		// Sorting a map 
		// Logic 
		// a) Sort the existing map using value
		// b) and insert each ordered key using LinkedHashMap which maintains the order of insertion
		Map<String, Long> sortResultByValue = new LinkedHashMap<String, Long>();
		result.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.forEachOrdered(e -> sortResultByValue.put(e.getKey(), e.getValue()));
		System.out.println("Sorting the grouped items, based on value :: " + sortResultByValue);
		
		// Sorting the map using descending order of value
		Map<String, Long> revSortResultByValue = new LinkedHashMap<String, Long>();
		result.entrySet().stream()
			.sorted(Map.Entry.<String, Long>comparingByValue().reversed())
			.forEachOrdered(e -> revSortResultByValue.put(e.getKey(), e.getValue()));
		System.out.println("Reverse sorting the grouped items, based on value :: " + revSortResultByValue);
		
		
		// Sorting the grouped items, based on key
		Map<String, Long> sortResultByKey = new LinkedHashMap<String, Long>();
		result.entrySet().stream()
			.sorted(Map.Entry.comparingByKey())
			.forEachOrdered(e -> sortResultByKey.put(e.getKey(), e.getValue()));
		System.out.println("Sorting the grouped items, based on key :: " + sortResultByKey);
		
		// Sorting the grouped items, based descending order of key
		Map<String, Long> revSortResultByKey = new LinkedHashMap<String, Long>();
		result.entrySet().stream()
			.sorted(Map.Entry.<String,Long>comparingByKey().reversed())
			.forEachOrdered(e -> revSortResultByKey.put(e.getKey(), e.getValue()));
		System.out.println("Reverse sorting the grouped items, based on key :: " + revSortResultByKey);
		
		// Find the duplicate elements
		List<String> duplicateValues = new ArrayList<String>();
		result.entrySet().stream()
			.filter(e -> e.getValue()>1)
			.forEach(e -> duplicateValues.add(e.getKey()));
		System.out.println("Duplicate items :: " + duplicateValues);
		
		// Find the unique elements
		List<String> uniqueItems = new ArrayList<String>();
		result.entrySet().stream()
									.distinct()
									.forEach(e -> uniqueItems.add(e.getKey()));
		System.out.println("Unique items :: " + uniqueItems);
		
		// Export the unique elements, say no to banana
		List<String> uniqueItemsExclBanana = new ArrayList<String>();
		result.entrySet().stream()
				.distinct().filter(e -> !e.getKey().equalsIgnoreCase("banana"))
				.forEach(e -> uniqueItemsExclBanana.add(e.getKey()));
		System.out.println("Unique items excl banana :: " + uniqueItemsExclBanana);
		
		// Find the non - duplicate elements
		List<String> nonDuplicateItems = new ArrayList<String>();
		result.entrySet().stream()
			.filter(e -> e.getValue()==1)
			.forEach(e -> nonDuplicateItems.add(e.getKey()));
		System.out.println("Non duplicate items :: " + nonDuplicateItems);
		
		// Find the non - duplicate elements using map (to transform map to list)
		List<String> nonDuplicateItemsUsingMap = result.entrySet().stream()
			.filter(e -> e.getValue()==1)
			.map(e -> e.getKey())
			.collect(Collectors.toList());
		System.out.println("Non duplicate items using map :: " + nonDuplicateItemsUsingMap);
		
	}
}
