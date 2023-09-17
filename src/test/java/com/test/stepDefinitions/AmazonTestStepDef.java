/*
 * *Author : RaviKumar Mogulluru
 */
package com.test.stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.test.pages.AmazonTestPage;
import com.test.utilities.AmazonTestConstants;
import com.test.utilities.ConfigFileReader;
import com.test.utilities.ExcelRead;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;

public class AmazonTestStepDef {

	WebDriver driver;
	ConfigFileReader configReader;
	AmazonTestPage amazonTestPage;
	ExcelRead excelRead;
	WebDriverWait wait = null;
	private static final Logger log = LogManager.getLogger(AmazonTestStepDef.class);
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
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		amazonTestPage = new AmazonTestPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Given("^Launch the aplication$")
	public void launch_the_application()
	{
		log.info("User launchs the application");
		driver.get(configReader.readProperty("url"));
	}

	@Given("^User is on google home page$")
	public void user_is_on_google_home_page() {
		log.info("Title of the page:"+driver.getTitle());
		log.info("URL of the page:"+driver.getCurrentUrl());
		Assert.assertEquals(driver.getTitle().contains("Google"), true,"User is not in the home page...!");
		Assert.assertEquals(driver.getCurrentUrl().contains("www.google.com"), true,"User is not in the home page...!");
	}

	@When("^User enters keyword in search$")
	public void user_enter_username_and_password() throws IOException, InterruptedException {
		amazonTestPage.performSearch(excelRead.getCellValue(AmazonTestConstants.keyword));
		log.debug("user enters search keyword");
	}

	@Then("^Print all the search results$")
	public void validateSearch() {
		amazonTestPage.printAllTheSugesstions();
		log.info("User Printed all the search results !");
	}
	
	@Then("^Click on the link which takes you to the amazon login page$")
	public void clicksOnTheLinkWhichNavigatesToAmazonLogin() {
		amazonTestPage.selectSuggestionToNavigateAmazonLogin();
		amazonTestPage.clickOnAmazonLink();
		Assert.assertEquals(true, (driver.getCurrentUrl()).contains("https://www.amazon.in"),"User is not in amazon login page!");
		log.info("Login page url:"+driver.getCurrentUrl());
		log.info("User Click on the link which takes you to the amazon login page !");
	}
	
	@Then("^Click on all buttons on search and select Electronics$")
	public void clickOnAllButtonsOnSearchAndSelectElectronics() throws InterruptedException {
		amazonTestPage.clickOnAllAndSearchForElectronicsAndSelect(wait);
		log.info("User Click on all buttons on search and select Electronics!");
	}
	@And("Search for product$")
	public void searchForDellComputers() throws InterruptedException, IOException {
		amazonTestPage.searchProduct(excelRead.getCellValue(AmazonTestConstants.product));
		log.info("User Search for "+AmazonTestConstants.product);
	}
	
	@Then("Apply the filter of range Rs minprice to maxprice$")
	public void applyFilterWithRange() throws InterruptedException, IOException {
		amazonTestPage.applyPriceFilter(excelRead.getCellValue(AmazonTestConstants.minprice),excelRead.getCellValue(AmazonTestConstants.maxPrice));
		log.info("User applied the filter of range Rs minprice to maxprice");
	}
	
	@And("Validate all the products shown in the range of Rs minprice to maxprice$")
	public void validateApplyFilterWithRange() throws InterruptedException, IOException {
		amazonTestPage.validateAppledPriceFilter(excelRead.getCellValue(AmazonTestConstants.minprice),excelRead.getCellValue(AmazonTestConstants.maxPrice));
		log.info("User validated Validate all the products shown in the range of Rs minprice to maxprice");
	}
	
	@Then("Print all the products whose rating is 5.0$")
	public void print5RatingItems() throws InterruptedException, IOException {
		amazonTestPage.print5RatingItemsFromFilteredList(excelRead.getCellValue(AmazonTestConstants.rating));
		log.info("User Print all the products whose rating is 5 outof 5");
	}
	//avoiding login to add fileterd item to withlist due login
	@Then("user tries to add item to the wishlist$")
	public void addItemsToWishList() throws InterruptedException {
		amazonTestPage.performWishList();
		log.info("user tries to add item to the wishlist whose rating is 5 outof 5");
	}
	
	@After
	public void afterHook() {
		driver.close();
		log.info("user performed tear down openration...!");
		}
}
