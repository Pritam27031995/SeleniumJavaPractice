package Utilities;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class BaseClass {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Scenario scenario;
	public static Actions action;
	public static JavascriptExecutor js;
	
	public static void init_before_method(Scenario scenario) {
		BaseClass.scenario=scenario;
	}
	
	public static void CaptureScreenshot() {
		byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "screenshot");
	}
	
	public static void reportLog(String string) {
		scenario.log(string);
	}
	

}
