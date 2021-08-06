package com.bestbuy.model.cucumber;

import com.bestbuy.model.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
//@CucumberOptions(features = "src/test/java/resources/feature/services.feature")
@CucumberOptions(features = "src/test/java/resources/feature/products.feature")
public class CucumberRunner extends TestBase
{

}
