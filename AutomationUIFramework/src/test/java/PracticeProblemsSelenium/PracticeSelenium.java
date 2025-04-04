package PracticeProblemsSelenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
		action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable"))).build().perform();
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void testJavascriptexecutor() {
		try {
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			js =  (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.id("mousehover")));
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	}

	@BeforeTest
	public void beforeEachtest() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@AfterTest
	public void afterTest() {
		//driver.quit();
	}

}
