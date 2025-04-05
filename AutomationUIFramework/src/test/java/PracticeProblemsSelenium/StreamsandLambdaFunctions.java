package PracticeProblemsSelenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsandLambdaFunctions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Streams are divided into 3 parts: 1.Convert into streams 2.Intermediate operation 3.Terminal Operation
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("Alexandar");
		names.add("Achilies");
		names.add("Colombus");
		names.add("Steve");
		names.add("Marco");
		
		Integer[] numbers = {1,3,2,2,1,4,5,5,7,3,2,1};
		
		//Get the names which starts with A
		Long count = names.stream().filter(S-> S.startsWith("A")).count();
		System.out.println(count);
		
		//Print the names starts with A
		names.stream().filter(S-> S.startsWith("A")).forEach(S-> System.out.println(S));
		
		//Print the names starts with M
		List<String> filtered = names.stream().filter(S-> S.startsWith("M")).toList();
		System.out.println(filtered);
		
		//Print the names in UPPERCASE
		names.stream().map(S-> S.toUpperCase()).forEach(S-> System.out.println(S));
		
		//check if name: Steve is present inside the list
		boolean result = names.stream().anyMatch(S-> S.equalsIgnoreCase("Marco"));
		System.out.println(result);
		
		//print unique numbers from an array
		List<Integer> num = Arrays.asList(numbers);
		List<Integer> resultn = num.stream().distinct().collect(Collectors.toList());
		System.out.println(resultn);
		
	}

}
