package PracticeProblemsSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import Utilities.BaseClass;
import Utilities.Retry;

public class NegativeScenarios extends BaseClass{
	
	@Test(retryAnalyzer = Retry.class)
	public void validateErrorMessageIncorrectUsernamePassword() {
		
		String Username="pritam.debnath@gmail.com";
		String password = "Test@123456";
		String product = "ADIDAS ORIGINAL";
		// TODO Auto-generated method stub

		//driver.get(URL);

		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.login(Username, password);
		String Errormessage =loginPage.getErrorMessage(wait);
		Assert.assertEquals(Errormessage, "Incorrecto email or password.");
	}


	
	
}
