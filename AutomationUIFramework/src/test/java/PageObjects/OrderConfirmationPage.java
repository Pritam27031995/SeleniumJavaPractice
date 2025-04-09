package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OrderConfirmationPage {

	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By messageheader = By.className("hero-primary");
	
	@FindBy(className="hero-primary")
	WebElement ConfirmationMessage;
	
	public void checkConfirmation(WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(messageheader));
		Assert.assertTrue(ConfirmationMessage.getText().equalsIgnoreCase("Thankyou for the order."));
	}
	

}
