
public class CoreJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//sum of numbers in an array
		int[] array = {1,5,4,6,7,9};
		System.out.println(sumofElement(array));
		
		//Fibonacci series of 8
		int a=0;
		int b =1;
		int sum =0;
		int counter=1;
		while(counter<9) {
			sum=a+b;
			a=b;
			b=sum;
			counter++;
			System.out.println(sum);
		}

	}
	
	//method to get sum of all element
	public static int sumofElement(int[] arr) {
		int sum=0;
		for (int i =0; i<arr.length;i++) {
			sum=sum+ arr[i];
		}
		return sum;
	}

}
