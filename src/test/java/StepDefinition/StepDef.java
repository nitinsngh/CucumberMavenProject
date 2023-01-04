package StepDefinition;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDef extends BaseClass {
	
	@Before
	public void setup()
	{
		readConfig = new ReadConfig();
		
		//initialise logger
		log = LogManager.getLogger("StepDef");

		System.out.println("Setup-Sanity method executed..");

		String browser = readConfig.getBrowser();
		
		//launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}

	
		log.fatal("Setup executed...");


	}

//	public WebDriver driver;
//	public LoginPage loginPg;
//	public AddNewCustomerPage addNewCustPg;
//	public SearchCustomerPage SearchCustPg ;
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
//		WebDriverManager.chromedriver().setup();
//		driver= new ChromeDriver();
		loginPg= new LoginPage(driver);
		addNewCustPg= new AddNewCustomerPage(driver);
		SearchCustPg= new SearchCustomerPage(driver);
		
		log.info("chrome browser launched");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		
		log.info("url opened");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailadd, String password) {
		loginPg.enterEmail(emailadd);
		loginPg.enterPassword(password);
		
		log.info("email address and password entered");
	}

	@When("Click on Login")
	public void click_on_login() {
		loginPg.clickOnLoginButton();
		
		log.info("Clicked on login button");
	}
	
//////////Login feature///////////////////////////
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		String actualTitle = driver.getTitle();
		if(actualTitle.equals(expectedTitle))
		{
			log.warn("Test passed: Login feature :Page title matched.");
			Assert.assertTrue(true);//pass
		}
		else {
			log.warn("Test failed: Login feature :Page title not matched.");
			Assert.assertTrue(false);//fail
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		loginPg.clickOnLogOutButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		log.info("user clicked on logout link.");
	}

	/*
	 * @Then("close browser") public void close_browser() {
	 * 
	 * driver.quit(); }
	 */	/////////////////////////////////////////// Add New Customer /////////////////////////////////////////////////
	
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
	    String actualTitle = addNewCustPg.getPageTitle();
	    Object expectedTitle = "Dashboard / nopCommerce administration";
	    if(actualTitle.equals(expectedTitle))
	    {
	    	log.info("user can view dashboard test passed.");
	    	Assert.assertTrue(true);
	    }
	    else
	    {
	    	Assert.assertTrue(false);
	    	log.warn("user can view dashboard test failed.");
	    }
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
	    addNewCustPg.clickOnCustomersMenu();
	    log.info("customer menu clicked");
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
	    addNewCustPg.clickOnCustomersMenuItem();
	    log.info("customer menu item clicked");
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
	   addNewCustPg.clickOnAddnew();
	   
	   log.info("clicked on add new button.");
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("User can view Add new customer page- passed");

			Assert.assertTrue(true);//pass
		}
		else
		{
			log.info("User can view Add new customer page- failed");

			Assert.assertTrue(false);//fail
		}
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
		addNewCustPg.enterEmail(generateEmailId() + "@gmail.com");
		addNewCustPg.enterPassword("test1");
		addNewCustPg.enterFirstName("Nitin");
		addNewCustPg.enterLastName("Singh");
		addNewCustPg.enterGender("Male");
		addNewCustPg.enterDob("1/25/1988");
		addNewCustPg.enterCompanyName("Crocus Robotics Pvt Ltd");
		addNewCustPg.enterAdminContent("Admin content");
		addNewCustPg.enterManagerOfVendor("Vendor 1");
		
		log.info("customer information entered");
	}

	@When("click on Save button")
	public void click_on_save_button() {
		addNewCustPg.clickOnSave();
		
		log.info("clicked on save button");
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {
		String bodyTagText = driver.findElement(By.tagName("Body")).getText();
		if(bodyTagText.contains(exptectedConfirmationMsg))
		{
			Assert.assertTrue(true);//pass
			log.info("User can view confirmation message - passed");

		}
		else
		{
			log.warn("User can view confirmation message - failed");

			Assert.assertTrue(false);//fail

		}

	}
    
	////////////Search Customer//////////////////////////
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		SearchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
		
		log.info("Email address entered");
	}

	@When("Click on search button")
	public void click_on_search_button() {
		SearchCustPg.clickOnSearchButton();
		log.info("Clicked on search button.");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		String expectedEmail = "victoria_victoria@nopCommerce.com";

		//   Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail));

		if( SearchCustPg.searchCustomerByEmail(expectedEmail) ==true)
		{
			Assert.assertTrue(true);
			log.info("User should found Email in the Search table - passed");

		}
		else {
			log.info("User should found Email in the Search table - passed");
			Assert.assertTrue(false);

		}
	}
   
	
	///////////////search customer by name////////////////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		SearchCustPg.enterFirstName("Victoria");
		log.info("Entered First Name");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		SearchCustPg.enterLastName("Terces");
		log.info("Entered Last Name");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		String expectedName = "Victoria Terces";


		if( SearchCustPg.searchCustomerByName(expectedName) ==true)
		{
			Assert.assertTrue(true);
		}
		else
			Assert.assertTrue(false);

	}
	@After
	public void teardown(Scenario sc)
	{
		System.out.println("Tear Down method executed..");
		if(sc.isFailed()==true)
		{
			//Convert web driver object to TakeScreenshot

			String fileWithPath = "C:\\Users\\hp\\eclipse-workspace\\CucumberFramework\\Screenshot\\failedScreenshot.png";
			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination
			File DestFile=new File(fileWithPath);

			//Copy file at destination

			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

		driver.quit();
	}

}
