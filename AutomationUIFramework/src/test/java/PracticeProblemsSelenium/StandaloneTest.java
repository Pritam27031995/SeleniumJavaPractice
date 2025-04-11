package PracticeProblemsSelenium;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		public void positiveE2EOrderCreation(HashMap<String, String> data) {

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
			
			loginPage.login(data.get("username"), data.get("password"));
			pdpage.AddProductToCart(data.get("product"), wait);
			cartpage.validateCart(data.get("product"), wait);
			chkout.provideCountryAndPlaceOrder(wait);
			ordConfPage.checkConfirmation(wait);

		}
		
		//Dataprovider using Object[][]
		
		/*
		 * @DataProvider public Object[][] getData(){ Object[][] data =
		 * {{"pritam.debnath@gmail.com","Test@1234","ADIDAS ORIGINAL"},{
		 * "pritamkol1995@gmail.com","Test@12345","ZARA COAT 3"}}; return data; }
		 */
		
		//Dataprovider using hashmap
		
		/*
		 * @DataProvider public Object[][] getData(){ Map<String,String> map = new
		 * HashMap<String,String>(); map.put("username", "pritam.debnath@gmail.com");
		 * map.put("password", "Test@1234"); map.put("product", "ADIDAS ORIGINAL");
		 * 
		 * Map<String,String> map1 = new HashMap<String,String>(); map1.put("username",
		 * "pritamkol1995@gmail.com"); map1.put("password", "Test@12345");
		 * map1.put("product", "ZARA COAT 3");
		 * 
		 * return new Object[][] {{map},{map1}}; }
		 */
		
		//Dataprovider using hasmop retrieved from json file
		@DataProvider
		public Object[][] getData() throws IOException{
			List<HashMap<String,String>> data= getDataUsingJsonAsMap();
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
	

}
