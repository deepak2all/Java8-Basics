package com.basic.treemap;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapEx {

	 public static void main(String args[]){  
		 TreeMap<Integer,String> personMap=new TreeMap<Integer,String>();    
		 personMap.put(100,"Amit");        
		 personMap.put(101,"Vijay");    
		 personMap.put(103,"Rahul"); 
		 personMap.put(102,"Ravi");
        
	     for(Map.Entry m:personMap.entrySet()){    
	       System.out.println(m.getKey()+" "+m.getValue());    
	     }    
	     System.out.println("descendingMap: "+personMap.descendingMap());
	     
	     NavigableMap<Integer,String> fruitMap=new TreeMap<Integer,String>();    
	     fruitMap.put(100,"Apple");        
	     fruitMap.put(101,"Grape");    
	     fruitMap.put(103,"Orange"); 
	     fruitMap.put(102,"Banana");
	     System.out.println("AscendingMap: "+fruitMap);
	     System.out.println("descendingMap: "+fruitMap.descendingMap());  
	     
	     SortedMap<Integer, String> mapHttpStatus = new TreeMap<>(new ReverseComparator());
	     
	     mapHttpStatus.put(100, "Continue");
	     mapHttpStatus.put(200, "OK");
	     mapHttpStatus.put(300, "Multiple Choices");
	      
	     mapHttpStatus.put(400, "Bad Request");
	     mapHttpStatus.put(401, "Unauthorized");
	     mapHttpStatus.put(402, "Payment Required");
	     mapHttpStatus.put(403, "Forbidden");
	     mapHttpStatus.put(404, "Not Found");
	      
	     mapHttpStatus.put(500, "Internal Server Error");
	     mapHttpStatus.put(501, "Not Implemented");
	     mapHttpStatus.put(502, "Bad Gateway");
	      
	     for (Integer code : mapHttpStatus.keySet()) {
	         System.out.println(code + " -> " + mapHttpStatus.get(code));
	     }
	} 
    
}

class ReverseComparator implements Comparator<Integer> {
    public int compare(Integer num1, Integer num2) {
        return num2.compareTo(num1);
    }
}