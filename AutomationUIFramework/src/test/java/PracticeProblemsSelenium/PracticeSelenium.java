package PracticeProblemsSelenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.BaseClass;


public class PracticeSelenium extends BaseClass{
	
	@Test
	public void testDropdowns() {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		Select staticDropdown = new Select(driver.findElement(By.cssSelector("#ctl00_mainContent_DropDownListCurrency")));
		staticDropdown.selectByIndex(1);
		staticDropdown.selectByValue("USD");
		staticDropdown.selectByVisibleText("AED");
		List<WebElement> dropdownValues = staticDropdown.getOptions();
		for (WebElement element :dropdownValues) {
			System.out.println(element.getText()); 
		}
		
	}
	//to get attribute value of an element: .getDomAttribute("<AttributeName>")
	
	@Test
	public void testCheckboxes() {
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		int count = driver.findElements(By.cssSelector("[type='checkbox']")).size();
		System.out.println("Number of checkboxes in Page: "+ count);
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
	
	@BeforeTest
	public void beforeEachtest() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
