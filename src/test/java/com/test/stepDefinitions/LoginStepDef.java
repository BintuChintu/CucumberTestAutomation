package com.test.stepDefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.test.pages.LoginPage;
import com.test.utilities.ConfigFileReader;
import com.test.utilities.ExcelRead;

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
	public void beforeHook() {
		configReader = new ConfigFileReader();
		excelRead = new ExcelRead(); 
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + configReader.readProperty("gecodriverpath"));
		driver = new FirefoxDriver();
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

	@After
	public void afterHook() {
		driver.close();
		}
}
