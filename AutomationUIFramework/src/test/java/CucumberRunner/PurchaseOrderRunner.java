package CucumberRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Features",glue = "StepDefinitions",monochrome = true,plugin = {"html:target/CucumberReport.html"})
public class PurchaseOrderRunner extends AbstractTestNGCucumberTests{

}
