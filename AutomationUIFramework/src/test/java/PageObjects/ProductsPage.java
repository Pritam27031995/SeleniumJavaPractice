package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
	
	WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By cardbody = By.cssSelector(".card-body b");
	By AddCart = By.cssSelector("button:last-of-type");
	By Spinner = By.className("ngx-spinner-overlay");
	By productsAddedMessage = By.id("toast-container");
	
	@FindBy(css="div[class='card-body']")
	List<WebElement> cartbody;
	
	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement CartIcon;
	
	
	public void AddProductToCart(String productname, WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardbody));
		List<WebElement> products = cartbody;
		WebElement productSelected = products.stream().filter(prduct-> prduct.findElement(By.tagName("b")).getText().equals(productname)).findFirst().orElse(null);
		productSelected.findElement(AddCart).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productsAddedMessage));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Spinner));
		CartIcon.click();
	}
	


}
