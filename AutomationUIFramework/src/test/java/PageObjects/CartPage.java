package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By CartItem = By.xpath("//div[@class='infoWrap']");

	@FindBy(xpath = "//div[@class='infoWrap']")
	List<WebElement> addedproducts;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkOut;

	public void validateCart(String prductname, WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(CartItem));
		List<WebElement> Addedproducts = addedproducts;
		boolean addResult = Addedproducts.stream().anyMatch(
				addedproducts -> addedproducts.findElement(By.tagName("h3")).getText().equalsIgnoreCase(prductname));
		Assert.assertTrue(addResult);
		checkOut.click();
	}

}
