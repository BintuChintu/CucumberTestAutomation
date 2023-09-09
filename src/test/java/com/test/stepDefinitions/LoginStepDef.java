package com.test.stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.test.pages.LoginPage;
import com.test.utilities.ConfigFileReader;
import com.test.utilities.ExcelRead;

import cucumber.api.DataTable;
import dev.failsafe.internal.util.Assert;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {

	WebDriver driver;
	ConfigFileReader configReader;
	LoginPage loginPage;
	ExcelRead excelRead;

	@Before
	public void beforeHook() {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		configReader = new ConfigFileReader();
		loginPage = new LoginPage(driver);
		excelRead = new ExcelRead();
	}

	@Given("^User is on swag home page$")
	public void user_is_swag_home_page() {

		driver.get(configReader.readProperty("url"));
		System.out.println("page title:"+driver.getTitle());
		org.testng.Assert.assertEquals(driver.getTitle().contains("Test Login"), true,"User is not in the home page...!");
	}

	@When("^User enter (.*) and (.*)$")
	public void user_enter_username_and_password(String userName,String passWord) throws IOException {
		//loginPage.login("standard_user", "standard_user");
		//Reading test data from excel by column name 
		//System.out.println("user name:"+excelRead.getCellValue(0, "UserName"));
		//System.out.println("password:"+excelRead.getCellValue(0, "PassWord"));
		loginPage.login(userName, passWord);
		
	}

	@Then("^Validate login$")
	public void validate_login() {
		org.testng.Assert.assertEquals(driver.getTitle().contains("Logged In Successfully"), true,"User is not logged into the application...!");
		}

}
