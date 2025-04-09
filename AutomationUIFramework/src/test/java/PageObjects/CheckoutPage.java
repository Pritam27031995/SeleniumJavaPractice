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

public class CheckoutPage {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By countryList = By.xpath("//*[@class='ta-results list-group ng-star-inserted']");

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countrySelection;

	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> countries;

	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement PlaceOrder;

	public void provideCountryAndPlaceOrder(WebDriverWait wait) {

		countrySelection.sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(countryList));
		List<WebElement> Countries = countries;
		WebElement element = Countries.stream()
				.filter(country -> country.findElement(By.tagName("span")).getText().equals("India")).findFirst()
				.orElse(null);
		element.click();
		PlaceOrder.click();
	}

}
