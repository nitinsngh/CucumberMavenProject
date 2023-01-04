package TestRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {".//Features/LoginFeature.feature"},
		//features = ".//Features" run all features files 
		glue = "StepDefinition",
		dryRun = false,
		monochrome = true,
				tags = "@Sanity",//scenarios under @sanity tag will be executed. Does not matter if it has other tags as well
				//tags = "@Sanity or @Regression",//will run scenarios  tag under @sanity or @Regression  will be executed
				//tags = "@Sanity and @Regression",//will run scenarios which is tag with sanity as well as regression
				//tags = "@Sanity and not @Regression",//will run scenarios which is tag with sanity but not regression
		//plugin = {"pretty","junit:target/cucumber-reports/reports_xml.xml"}
		//plugin = {"pretty","json:target/cucumber-reports/reports_json.json"} //json format
		plugin = {"pretty","html:target/cucumber-reports/reports_html.html"}
		
		
		
		)
public class Run extends AbstractTestNGCucumberTests{
// Class will be empty
}
