import java.util.ArrayList;

public class ListInterface {

	// List: Arraylist, Linkedlist, Vector
	// List interface contains duplicate values

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Arraylist Implementation
		ArrayList<String> a = new ArrayList<String>();
		a.add("apple");
		a.add("banana");
		a.add("cherry");
		a.add("mango");
		a.add("apple");
		System.out.println(a);
		a.remove("apple");
		System.out.println(a);
		if (a.contains("orange")) {
			System.out.println("Orange is present");
		} else {
			System.out.println("Orange is not present");
		}
		System.out.println("ArrayList size: " + a.size());
		for (int k = 0; k < a.size(); k++) {
			System.out.println("Index: " + k);
			System.out.println("Value: " + a.get(k));
		}
		
		//LinkedList Implementation
		
		//Vector Implementation

	}

}
