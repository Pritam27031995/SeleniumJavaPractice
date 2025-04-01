
public class ExceptionHandling {
	//throw: To throw an customized exception which is not covered in the base exceptions provided by java.lang
	//throws: To warn the user that the method or statement can throw exception of that type
	
	public static void testCustomizedException(int b) throws CustomizedException{
		if (b>6) {
			throw new CustomizedException("This is customized exception");
		}
		//System.out.println("This can throw customized exception");
	}

	public static void main(String[] args) throws CustomizedException {
		// TODO Auto-generated method stub
		int k=7;
		ExceptionHandling.testCustomizedException(k);
		
		try {
			int a =5;
			int b=0;
			int c = a/b;
		}
		catch(ArithmeticException em) {
			System.out.println("Inside Arithmatic catch block");
		}
		catch(IndexOutOfBoundsException ibe) {
			System.out.println("Inside Index out of bound exception");
		}
		catch(Exception e) {
			System.out.println("Inside default catch block");
		}


	}

}
