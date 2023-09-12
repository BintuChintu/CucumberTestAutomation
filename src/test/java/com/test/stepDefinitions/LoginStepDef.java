package com.test.stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.test.pages.LoginPage;
import com.test.utilities.ConfigFileReader;
import com.test.utilities.ExcelRead;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginStepDef {

	WebDriver driver;
	ConfigFileReader configReader;
	LoginPage loginPage;
	ExcelRead excelRead;
	 private static final Logger log = LogManager.getLogger(LoginStepDef.class);
	@Before
	public void beforeHook() throws Exception {
		configReader = new ConfigFileReader();
		excelRead = new ExcelRead(); 
		String browserName = configReader.readProperty("browserName");
		if(browserName.equalsIgnoreCase("firefox"))
		{
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + configReader.readProperty("gecodriverpath"));
		driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + configReader.readProperty("chromedriverpath"));
			driver = new ChromeDriver();
		}
		else
		{
			throw new Exception("Choose correct browser to run the test scripts");
		}
		loginPage = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}

	@Given("^User is on swag home page$")
	public void user_is_swag_home_page() {

		driver.get(configReader.readProperty("url"));
//		logger implementation 
		log.debug("This is debug message");
        log.info("This is info message");
        log.warn("This is warn message");
        log.fatal("This is fatal message");
        log.error("This is error message");

		org.testng.Assert.assertEquals(driver.getTitle().contains("Test Login"), true,
				"User is not in the home page...!");
	}

	@When("^User enter (.*) and (.*)$")
	public void user_enter_username_and_password(String userName, String passWord) throws IOException {
// 	loginPage.login("standard_user", "standard_user");
// 	Reading test data from excel by column name
// 	System.out.println("user name:"+excelRead.getCellValue(0, "UserName"));
// 	System.out.println("password:"+excelRead.getCellValue(0, "PassWord"));
		loginPage.login(userName, passWord);
	}

	@Then("^Validate login$")
	public void validate_login() {
		org.testng.Assert.assertEquals(driver.getTitle().contains("Logged In Successfully"), true,
				"User is not logged into the application...!");
		log.info("User validated login successfully....!");
	}
	
	//data table implementation
	
	@When("User enters login credentials")
	public void user_enters_login_credentials(DataTable userCredentials) {	    
//By using List		
//	  List<String> data = userCredentials.row(0);
//	  loginPage.login(data.get(0), data.get(1));
//List<Map<K,V>>		
		List<Map<String,String>> data = userCredentials.asMaps(String.class,String.class);
		loginPage.login(data.get(0).get("username"), data.get(0).get("password"));
	}

	@After
	public void afterHook() {
		driver.close();
		}
}
