import java.util.HashSet;
import java.util.Iterator;



public class SetInterface {

	//Hashset, Linkedset, treeset comes under set interface
	//Set doesn't accept duplicate values
	//Set doesn't arrange the values sequentially
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashSet<String> hs = new HashSet<String>();
		hs.add("Java");
		hs.add("Python");
		hs.add("Javascript");
		hs.add("c++");
		System.out.println(hs);
		hs.add("Java");
		System.out.println(hs);
		
		Iterator<String> i = hs.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}

	}

}
