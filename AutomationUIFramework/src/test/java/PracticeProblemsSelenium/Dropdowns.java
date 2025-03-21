package PracticeProblemsSelenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.BaseClass;


public class Dropdowns extends BaseClass{
	
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
