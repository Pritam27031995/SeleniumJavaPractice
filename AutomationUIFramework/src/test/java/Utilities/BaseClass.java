package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.Scenario;

public class BaseClass {

	public WebDriver driver;
	public WebDriverWait wait;
	public Scenario scenario;
	public Actions action;
	public JavascriptExecutor js;
	public SoftAssert softassert;
	public ChromeOptions options;
	public ExtentReports extent;
	

	public void init_before_method(Scenario scenario) {
		this.scenario = scenario;
	}

	public void CaptureScreenshotCucumber() {
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "screenshot");
	}
	
	public String CaptureScreenshot(WebDriver driver, String TestCaseName) throws IOException
	{
		String FilePath = System.getProperty("user.dir")+"//Screenshots//"+TestCaseName+".png";
		File fs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fs, new File(FilePath));
		return FilePath;
	}

	public void reportLog(String string) {
		scenario.log(string);
	}

	@BeforeMethod(alwaysRun = true)
	public void intializedriver() throws IOException {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\GlobalData.properties");
		props.load(fis);
		String url = (String) props.get("url");
		options = new ChromeOptions();
		options.addArguments("--disable-extensions");
        options.addArguments("test-type");
		Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

		if (props.get("browser").equals("chrome")) {
			driver = new ChromeDriver(options);
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
	
	public List<HashMap<String, String>> getDataUsingJsonAsMap() throws IOException {
		FileUtils fis = new FileUtils();
		String jsonContent = fis.readFileToString(new File(System.getProperty("user.dir")+"\\TestData\\OrderData.json"));
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> Data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return Data;
	}
	
	public ExtentReports createExtentReport() {
		ExtentSparkReporter exsp = new ExtentSparkReporter((System.getProperty("user.dir")+"\\Reports\\index.html"));
		exsp.config().setReportName("Web Automation Report");
		exsp.config().setDocumentTitle("Test Automation Results");
		extent = new ExtentReports();
		extent.attachReporter(exsp);
		return extent;
	}

}
