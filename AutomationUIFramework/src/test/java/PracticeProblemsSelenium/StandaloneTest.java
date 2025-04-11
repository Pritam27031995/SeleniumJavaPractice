package PracticeProblemsSelenium;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.LoginPage;
import PageObjects.OrderConfirmationPage;
import PageObjects.ProductsPage;
import Utilities.BaseClass;

public class StandaloneTest  extends BaseClass {

	
		@Test(groups = {"Positive"},dataProvider = "getData")
		public void positiveE2EOrderCreation(String Username, String password, String product) {

			//String Username="pritam.debnath@gmail.com";//pritamkol1995@gmail.com  Test@12345
			//String password = "Test@1234";
			//String product = "ADIDAS ORIGINAL";
			// TODO Auto-generated method stub

			//driver.get(URL);

			LoginPage loginPage = new LoginPage(driver);
			ProductsPage pdpage = new ProductsPage(driver);
			CartPage cartpage = new CartPage(driver);
			CheckoutPage chkout = new CheckoutPage(driver);
			OrderConfirmationPage ordConfPage = new OrderConfirmationPage(driver);
			
			loginPage.login(Username, password);
			pdpage.AddProductToCart(product, wait);
			cartpage.validateCart(product, wait);
			chkout.provideCountryAndPlaceOrder(wait);
			ordConfPage.checkConfirmation(wait);

		}
		
		@DataProvider
		public Object[][] getData(){
			Object[][] data = {{"pritam.debnath@gmail.com","Test@1234","ADIDAS ORIGINAL"},{"pritamkol1995@gmail.com","Test@12345","ZARA COAT 3"}};
			return data;
		}
		

		
	

}
