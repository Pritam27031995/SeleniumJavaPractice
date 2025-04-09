package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.Scenario;

public class BaseClass {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Scenario scenario;
	public static Actions action;
	public static JavascriptExecutor js;
	public static SoftAssert softassert;

	public static void init_before_method(Scenario scenario) {
		BaseClass.scenario = scenario;
	}

	public static void CaptureScreenshot() {
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "screenshot");
	}

	public static void reportLog(String string) {
		scenario.log(string);
	}

	@BeforeMethod(alwaysRun = true)
	public void intializedriver() throws IOException {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\GlobalData.properties");
		props.load(fis);
		String url = (String) props.get("url");
		if (props.get("browser").equals("chrome")) {
			driver = new ChromeDriver();
		}

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);

	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.quit();
	}

}
