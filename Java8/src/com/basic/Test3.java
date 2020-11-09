package com.basic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Test3 {

	public static void main(String[] args) {

        //3 apple, 2 banana, others 1
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orange", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("29.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99")),
                new Item("apple", 30, new BigDecimal("19.99"))
                );

        System.out.println("Group by Name and finding the nuber of varieties in them");
        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting()));

        System.out.println(counting);

        System.out.println("Group by Name Adding the total quantity / item name");
        Map<String, Integer> sumByName = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

        System.out.println(sumByName);
        
        System.out.println("Group by Price Adding the total quantity / item price");
        Map<BigDecimal, Integer> sumByPrice = items.stream().collect(Collectors.groupingBy(Item::getPrice, Collectors.summingInt(Item::getQty)));
        System.out.println(sumByPrice);
        
        System.out.println("Group by Price");
        //group by price
        Map<BigDecimal, List<Item>> groupByPriceMap = 
            items.stream().collect(Collectors.groupingBy(Item::getPrice));

        System.out.println(groupByPriceMap);
        
        System.out.println("Group by Name");
        Map<String, List<Item>> groupByName = items.stream().collect(Collectors.groupingBy(Item::getName));
        System.out.println(groupByName);

        // group by price, uses 'mapping' to convert List<Item> to Set<String>
        System.out.println("Collect the items names based on price range");
        Map<BigDecimal, Set<String>> itemsBasedonPricing =
                items.stream().collect(
                        Collectors.groupingBy(Item::getPrice,
                                Collectors.mapping(Item::getName, Collectors.toSet())
                        )
                );

        System.out.println(itemsBasedonPricing);

        System.out.println("Collect the different pricings for an item");
        Map<String, Set<BigDecimal>> itemsWithDiffPricinglevels 
        		= items.stream().collect(Collectors.groupingBy(Item:: getName, 
        				Collectors.mapping(Item::getPrice, Collectors.toSet())));
        System.out.println(itemsWithDiffPricinglevels);
        
        System.out.println("Min price of each item");
        Map<String, Optional<Item>> minPriceofEachItem = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.minBy(Comparator.comparing(Item::getPrice))));
        System.out.println(minPriceofEachItem);
        
        System.out.println("Max price of each item");
        Map<String, Optional<Item>> maxPriceOfEachItem = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.maxBy(Comparator.comparing(Item::getPrice))));
        System.out.println(maxPriceOfEachItem);
        
        System.out.println("Min price item");
        Optional<Item> minPriceItem = items.stream().collect(Collectors.minBy(Comparator.comparing(Item::getPrice)));
        System.out.println(minPriceItem);
        
        System.out.println("Max price of item");
        Optional<Item> maxPriceItem = items.stream().collect(Collectors.maxBy(Comparator.comparing(Item::getPrice)));
        System.out.println(maxPriceItem);
        
        
        Collections.sort(items, 
                Comparator.comparingInt( 
                    Item::getQty).thenComparing(Item::getName).reversed()); 
        items.forEach(e -> System.out.println(e.getName() + " ## " +e.getQty()));
    }
}

class Item {
	private String name;
    private int qty;
    private BigDecimal price;
    
	public Item(String name, int qty, BigDecimal price) {
		super();
		this.name = name;
		this.qty = qty;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Item [name=" + name + ", qty=" + qty + ", price=" + price + "]";
	}
    
}