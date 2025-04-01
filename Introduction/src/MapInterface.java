import java.util.HashMap;

public class MapInterface {
	
	//Difference between hashmap and hashtable is: Hashmap is not thread safe and non synchronas, whereas hashset is thread safe.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(1, "Java");
		hm.put(2, "Javascript");
		System.out.println(hm.get(1));
	}

}
