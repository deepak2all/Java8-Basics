package com.basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		Company comp1 = new Company("Company1", "Finance", 1981, 2003);
		Company comp2 = new Company("Company2", "Retail", 1992, 2008);
		Company comp3 = new Company("Company3", "Auto", 1999, 2007);
		Company comp4 = new Company("Company4", "Retail", 1989, 2010);
		Company comp5 = new Company("Company5", "Technology", 2009, 2014);
		Company comp6 = new Company("Company6", "Finance", 1987, 2010);
		Company comp7 = new Company("Company7", "Auto", 1986, 1996);
		Company comp8 = new Company("Company8", "Technology", 2011, 2016);
		Company comp9 = new Company("Company9", "Retail", 1981, 1989);
		Company comp10 = new Company("Company10", "Retail", 2001, 2015);
		Company comp11 = new Company("Company11", "Retail", 2011, 2018);
		
		Company[] companies = new Company[]{comp1, comp2, comp3, comp4, comp5, comp6, comp7, comp8, comp9, comp10, comp11};
		Comparator<Company> compareByCompName = Comparator.comparing(Company::getCompanyName);
		Comparator<Company> compareByCategory = Comparator.comparing(Company::getCategory);
		Predicate<Company> retailCompStartedSince2000 = c -> c.getCategory().equalsIgnoreCase("Retail") && c.getStart()>=2000;
		Consumer<Company> retailConsumer = c -> c.setCategory("Retail");

		System.out.println("Ascending order ::::::::::");
		List<Company> sortCompByName = Arrays.stream(companies)
			.sorted(compareByCategory)
			.collect(Collectors.toList());
		sortCompByName.stream().forEach(x -> System.out.println("Name : " + x.getCompanyName() + "## Category : " + x.getCategory()));
		
		System.out.println("Descending order ::::::::::");
		List<Company> sortCompByNameDesc = Arrays.stream(companies)
				.sorted(compareByCompName.reversed()) // Grouped by name within category
				.sorted(compareByCategory.reversed()) //1st grouped by category
				.collect(Collectors.toList());
		sortCompByNameDesc.stream().forEach(x -> System.out.println("Name : " + x.getCompanyName() + "## Category : " + x.getCategory()));
		
		System.out.println("Using multiple filters");
		List<Company> result0 = Arrays.stream(companies)
				.filter(c -> c.getCategory().equalsIgnoreCase("Retail") && c.getStart()>=2000)
				.collect(Collectors.toList());
		System.out.println("Result0 ::" + result0);
		
		System.out.println("Using Predicate");
		List<Company> result1 = Arrays.stream(companies)
				.filter(retailCompStartedSince2000)
				.collect(Collectors.toList());
		System.out.println("Result1 ::" + result1);
		
		System.out.println("Using a method taking parameters and returning Predicate");
		List<Company> result2 = Arrays.stream(companies)
				.filter(compCategoryStartedSinceYear("Retail", 2000))
				.collect(Collectors.toList());
		System.out.println("Result2 ::" + result2);
		
		System.out.println("Using a filter method that takes method returning predicate");
		List<Company> result3 = filterCondition(Arrays.asList(companies), compCategoryStartedSinceYear("Retail", 2000));
		System.out.println("Result3 ::" + result3);
		
		//To change the category of comp7 to Retail
		retailConsumer.accept(comp7);
		System.out.println(comp7.getCategory());
		
		//To change the category of all companies to Retail
		modifyCategory(Arrays.asList(companies), retailConsumer);
		Stream.of(companies).forEach(c -> System.out.println("Name :: " + c.getCompanyName() + " ## Category :: " + c.getCategory()));
		
	}
	
	public static Predicate<Company> compCategoryStartedSinceYear(String category, int year){
		return c -> c.getCategory().equalsIgnoreCase(category) && c.getStart()>=year;
	}
	
	public static List<Company> filterCondition(List<Company> companies, Predicate<Company> p){
		return companies.stream()
				.filter(p)
				.collect(Collectors.toList());
	}
	
	public static void modifyCategory(List<Company> companies, Consumer<Company> comp){
		companies.stream()
				.forEach(comp);
	}
}

class Company {
	private String companyName;
	private String category;
	private int start;
	private int end;
	
	public Company(String companyName, String category, int start, int end) {
		super();
		this.companyName = companyName;
		this.category = category;
		this.start = start;
		this.end = end;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	} 
	
}