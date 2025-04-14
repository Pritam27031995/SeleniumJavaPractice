package StepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.LoginPage;
import PageObjects.OrderConfirmationPage;
import PageObjects.ProductsPage;
import Utilities.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseOrder extends BaseClass{
	
	@Before
	public void BeforeEveryTest() throws IOException {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\GlobalData.properties");
		props.load(fis);
		String url = (String) props.get("url");
		options = new ChromeOptions();
		options.addArguments("--disable-extensions");
        options.addArguments("test-type");
        //options.addArguments("headless"); // To run the browser in headless mode
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
		//driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440,900)); //To set the browser to fullscreen in headless mode
		driver.get(url);
	}
	
	@After
	public void TearDown() {
		driver.quit();
	}

	@Given("User login to E-commerce website using {string} and {string}")
	public void User_login_to_Ecommerce_website(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}
	
	@When("User adds {string} to cart")
	public void User_adds_product_to_cart(String product) {
		ProductsPage pdpage = new ProductsPage(driver);
		pdpage.AddProductToCart(product, wait);
	}
	
	@And("User validates the {string} is added in the cart")
	public void User_validates_the_product_added_in_cart(String product) {
		CartPage cartpage = new CartPage(driver);
		cartpage.validateCart(product, wait);
	}
	
	@And("User plcaes the order from the checkout page")
	public void User_places_the_order_from_the_checkout_page() {
		CheckoutPage chkout = new CheckoutPage(driver);
		chkout.provideCountryAndPlaceOrder(wait);
	}
	
	@Then("User should get the confirmation message")
	public void User_should_get_the_confirmation_message() {
		OrderConfirmationPage ordConfPage = new OrderConfirmationPage(driver);
		ordConfPage.checkConfirmation(wait);
	}
}
