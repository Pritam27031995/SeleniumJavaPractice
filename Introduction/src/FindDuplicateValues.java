import java.util.ArrayList;

public class FindDuplicateValues {
	
	public static void main(String[] args) {
		int[] arr = {1,2,2,4,1,5,5,5,1,7,3,3,2};
		//Find the number of occurances of each number and also find the unique numbers from the list
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i =0; i<arr.length; i++) {
			if(al.contains(arr[i])) {
				continue;
			}
			al.add(arr[i]);
			int count=1;
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i]==arr[j]) {
					count++;
				}
			}
			System.out.println("Count for number: "+ arr[i]+" is: "+count);
			if(count==1) {
				System.out.println("Unique number: "+arr[i]);
			}
		}
	}

}
