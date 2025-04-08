package Utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener{
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("The test is successfully completed: "+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("The test is failed: "+result.getName());
	}

}
