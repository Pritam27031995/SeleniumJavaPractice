package PracticeProblemsSelenium;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utilities.BaseClass;

public class PracticeSelenium extends BaseClass {

	@Test
	public void testDropdowns() {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated((By.cssSelector("#ctl00_mainContent_DropDownListCurrency"))));
		Select staticDropdown = new Select(
				driver.findElement(By.cssSelector("#ctl00_mainContent_DropDownListCurrency")));
		staticDropdown.selectByIndex(1);
		staticDropdown.selectByValue("USD");
		staticDropdown.selectByVisibleText("AED");
		List<WebElement> dropdownValues = staticDropdown.getOptions();
		for (WebElement element : dropdownValues) {
			System.out.println(element.getText());
		}

	}
	// to get attribute value of an element: .getDomAttribute("<AttributeName>")

	@Test
	public void testCheckboxes() {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		int count = driver.findElements(By.cssSelector("[type='checkbox']")).size();
		System.out.println("Number of checkboxes in Page: " + count);
		System.out.println(driver.findElement(By.cssSelector("#ctl00_mainContent_chk_friendsandfamily")).isSelected());
		driver.findElement(By.cssSelector("#ctl00_mainContent_chk_friendsandfamily")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#ctl00_mainContent_chk_friendsandfamily")).isSelected());
	}

	@Test
	public void testAlerts() {
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.id("name")).sendKeys("Pritam");
		driver.findElement(By.id("alertbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		driver.findElement(By.id("confirmbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();
	}

	@Test
	public void testLoginPagePracticeWithSynchronization() {
		try {
			driver.get("https://rahulshettyacademy.com/loginpagePractise/");
			driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
			driver.findElement(By.id("password")).sendKeys("learning");
			driver.findElement(By.xpath("(//*[@class='radiotextsty'])[2]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[text()='You will be limited to only fewer functionalities of the app. Proceed?']")));
			driver.findElement(By.xpath("//button[@id='okayBtn']")).click();
			Select sl = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
			sl.selectByValue("consult");
			driver.findElement(By.id("terms")).click();
			driver.findElement(By.id("signInBtn")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-card[contains(@class,'col-lg-3')]")));
			List<WebElement> products = driver.findElements(By.xpath("//app-card[contains(@class,'col-lg-3')]"));
			for (WebElement e : products) {
				e.findElement(By.tagName("button")).click();
			}
			driver.findElement(By.xpath("//*[contains(text(),'Checkout')]")).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testActionClass() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		action = new Actions(driver);
		// Thread.sleep(5000);
		action.scrollToElement(driver.findElement(By.xpath("//*[text()='iFrame Example']"))).build().perform();
		action.moveToElement(driver.findElement(By.id("mousehover"))).build().perform();

		action.scrollToElement(driver.findElement(By.id("autocomplete")))
				.moveToElement(driver.findElement(By.id("autocomplete"))).click().keyDown(Keys.SHIFT).sendKeys("INDIA")
				.doubleClick().build().perform();

		action.moveToElement(driver.findElement(By.id("openwindow"))).contextClick().build().perform();
	}

	@Test
	public void testWindowHandle() {
		try {
			driver.get("https://rahulshettyacademy.com/loginpagePractise/");
			driver.findElement(By.className("blinkingText")).click();
			Set<String> Windows = driver.getWindowHandles();
			Iterator<String> it = Windows.iterator();
			String parentwindow = it.next();
			String childwindow = it.next();
			driver.switchTo().window(childwindow);
			String passwordString = driver.findElement(By.xpath("//*[@class='im-para red']")).getText();
			System.out.println(passwordString);
			String password = passwordString.split(" ")[4];
			System.out.println(password);
			driver.switchTo().window(parentwindow);
			driver.findElement(By.id("password")).sendKeys(password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testFrames() {
		driver.get("https://jqueryui.com/droppable/");
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		action = new Actions(driver);
		action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable"))).build()
				.perform();
		driver.switchTo().defaultContent();
	}

	@Test
	public void testJavascriptexecutor() {
		try {
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("mousehover")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(options);
		driver.get("https://expired.badssl.com/");
		System.out.println(driver.getTitle());

		// Add the WebDriver proxy capability.

		Proxy proxy = new Proxy();
		proxy.setHttpProxy("myhttpproxy:3337");
		options.setCapability("proxy", proxy);

		// to block pop ups
		// ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		
		//To set default download directory
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", "/directory/path");
		options.setExperimentalOption("prefs", prefs);

	}
	
	@Test
	public void testTakeScreenshot() throws IOException {
		//Full page screenshot
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		File fs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fs, new File("D:\\Automation Workspace\\JavaSelenium\\SeleniumJavaPractice\\AutomationUIFramework\\Screenshots\\testScreenshot.png"));
		
		//Particular Webelement screenshot
		
	}
	
	@Test
	public void testBrokenLinks() throws MalformedURLException, IOException {
		try {
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			WebElement footer =driver.findElement(By.xpath("//div[@class=' footer_top_agile_w3ls gffoot footer_style']"));
			List<WebElement> links = footer.findElements(By.tagName("a"));
			//System.out.println(links.size());
			for(WebElement e: links) {
				//System.out.println(e.getAttribute("href"));
				HttpURLConnection url = (HttpURLConnection) new URL(e.getAttribute("href")).openConnection();
				url.setRequestMethod("HEAD");
				url.connect();
				//System.out.println(url.getResponseCode());
				if(url.getResponseCode()>400) {
					System.out.println("The broken links are "+ e.getAttribute("href"));
					softassert.assertTrue(false);
				}
			}
			softassert.assertAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testJavaStreams() {
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		//validate that sorting functionality is working properly on the web table
		driver.findElement(By.xpath("//tr/th[1]")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//tr/td[1]"));
		List<String> originalList = elements.stream().map(S-> S.getText()).toList();
		List<String> SortedList = originalList.stream().sorted().toList();
		Assert.assertTrue(originalList.equals(SortedList),"The Webtable is not in sorted order");
		
		//Get the price of the item passed
		String item = "Beans";
		List<String> Prices= elements.stream().filter(s->s.getText().contains(item)).map(s->
		s.findElement(By.xpath("following-sibling::td[1]")).getText()).toList();
		System.out.println("Price of item "+item+ " is: "+ Prices);
		
		
	}
	
	@Test
	public void testNewWindowOrTab() {
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		//driver.navigate().to("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.cssSelector("#search-field")).sendKeys("Rice");
		driver.close();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentwindow = it.next();
		System.out.println("Current windowname: "+windows);
		driver.switchTo().window(parentwindow);
		driver.findElement(By.id("alertbtn")).click();
	}
	
	@Test
	public void testElementScreenshot() throws IOException {
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.cssSelector("#search-field")).sendKeys("Rice");
		File screenshot =driver.findElement(By.cssSelector("#search-field")).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("D:\\Automation Workspace\\JavaSelenium\\AutomationUIFramework\\Screenshots\\partial.png"));
	}
	
	@Test(dataProvider="getData")
	public void testDataProvider(String Username, String Password) {
		System.out.println("Username: "+Username);
		System.out.println("Password: "+ Password);
		
	}
	
	
	@DataProvider
	public Object[][] getData(){
		Object[][] data = new Object[3][2];
		data[0][0]="FirstUsername";
		data[0][1]="FirstPassword";
		data[1][0]="SecondUsername";
		data[1][1]="SecondPassword";
		data[2][0]="ThirdUsername";
		data[2][1]="ThirdPassword";
		
		return data;
	}

	@BeforeMethod
	public void beforeEachtest() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		softassert = new SoftAssert();
	}

	@AfterMethod
	public void afterTest() {
		 driver.quit();
	}

}
