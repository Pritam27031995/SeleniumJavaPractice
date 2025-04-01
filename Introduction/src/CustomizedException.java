import java.io.IOException;

public class CustomizedException extends Exception{
	
	public CustomizedException() {
		
	}
	
	public CustomizedException(String message) {
		super(message);
	}
}
