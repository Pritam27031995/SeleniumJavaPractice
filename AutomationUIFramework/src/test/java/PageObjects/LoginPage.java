package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement Username;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement Login;
	
	@FindBy(css="[class*='ng-star-inserted']")
	WebElement ErrorMessage;
	
	public void login(String username, String password) {
		Username.sendKeys(username);
		Password.sendKeys(password);
		Login.click();
	}
	
	public String getErrorMessage(WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOf(ErrorMessage));
		return ErrorMessage.getText();
	}
	
}
