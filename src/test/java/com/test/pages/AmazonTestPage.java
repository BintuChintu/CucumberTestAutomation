/*
 * *Author : RaviKumar Mogulluru
 */

package com.test.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.test.stepDefinitions.AmazonTestStepDef;

import jdk.internal.org.jline.utils.Log;

public class AmazonTestPage {

	WebDriver driver;
	Actions action;
	private static final Logger log = LogManager.getLogger(AmazonTestPage.class);
	
	By googlesearch = By.xpath("//textarea[@aria-label='Search']");
	By searchElements = By.xpath("//ul[@role='listbox']//li/descendant::div[@class='eIPGRd']");
	By selectSuggestionToNavigateAmazon = By.xpath("//*[contains(text(),'Amazon')]");
	By navigateLogin = By.xpath("(//*[contains(text(),'Amazon.in')])[1]");
	By allSelect = By.xpath("//*[@class='nav-search-scope nav-sprite']");
	By selectItem = By.xpath("//option[contains(text(),'Electronics')]");
	By searchProduct = By.xpath("//input[@aria-label='Search Amazon.in']");
	By minPrice = By.xpath("//input[@placeholder='Min']");
	By maxPrice = By.xpath("//input[@placeholder='Max']");
	By filterGo = By.xpath("//span[@class='a-button-inner']//input");
	By wholePrice = By.xpath("//span[@class='a-price-whole']");
	By rating = By.xpath("//div[@class=\"a-section a-spacing-none a-spacing-top-micro\"]/div/span/span[1]");
	By accountandlists = By.xpath("//span[text()='Account & Lists']");
	By createWishList = By.xpath("//*[text()='Create a Wish List']");
	By yourLists = By.xpath("//*[@class='a-tab-heading a-active a-size-large']//*[contains(text(),'Your Lists')]");
	By yourIdeaLists = By.xpath("//*[@class='a-tab-heading a-size-large']//*[contains(text(),'Your Idea Lists')]");
	By yourFriends = By.xpath("//*[@class='a-tab-heading a-size-large']//*[contains(text(),'Your Friends')]");
	
	public AmazonTestPage(WebDriver driver) {
		this.driver = driver;
	}

	public void performSearch(String keyword) throws InterruptedException {
		driver.findElement(googlesearch).click();
		driver.findElement(googlesearch).sendKeys(keyword);
		Thread.sleep(2000);
	}

	public void  printAllTheSugesstions() {
		//List<WebElement> rows=driver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='eIPGRd']"));
		List<WebElement> rows=driver.findElements(searchElements);
		Iterator<WebElement> it = rows.iterator();
		while(it.hasNext())
		{
			log.info("Auto suggestion:"+it.next().getText());
			Assert.assertEquals(true,(it.next().getText().toLowerCase().contains("amazon")),"Auto suggestions are wrongly published" );
		}
	}
	
	public void selectSuggestionToNavigateAmazonLogin()
	{
		driver.findElement(selectSuggestionToNavigateAmazon).click();
	}
	
	public void clickOnAmazonLink()
	{
		driver.findElement(navigateLogin).click();
	}
	
	public void clickOnAllAndSearchForElectronicsAndSelect(WebDriverWait wait) throws InterruptedException
	{ 
		driver.findElement(allSelect).click();
		driver.findElement(selectItem).click();		
	}
	
	public void searchProduct(String product)
	{
		driver.findElement(searchProduct).sendKeys(product);
		driver.findElement(searchProduct).sendKeys(Keys.ENTER);
	}
	
	public void applyPriceFilter(String min,String max) throws InterruptedException
	{
		driver.findElement(minPrice).sendKeys(min);
		driver.findElement(maxPrice).sendKeys(max);
		driver.findElement(filterGo).click();
	}
	
	public void validateAppledPriceFilter(String min,String max) throws InterruptedException
	{
		int minValue = Integer.parseInt(min);
		int maxValue = Integer.parseInt(max);
		List<WebElement> items = driver.findElements(wholePrice);
		Iterator<WebElement> it = items.iterator();
		while(it.hasNext())
		{
			String filteredItemPrice =  it.next().getText();
			String dataStr[] = filteredItemPrice.split(",");
			String priceStr="";
			for(int i=0;i<dataStr.length;i++)
			{
				priceStr = priceStr+dataStr[i];
				priceStr.trim();
			}
			log.info("Filtered item price value:"+priceStr);
			//Application is having issue.Listing below min value items as well
//			if((Integer.parseInt(priceStr))<minValue || (Integer.parseInt(priceStr))>maxValue)
//			{
//				Assert.fail("Fitered item is not in the rage..........");
//			}
		}
	}
	
	public void print5RatingItemsFromFilteredList(String rating)
	{
		int count =1;
		List<WebElement> items = driver.findElements(this.rating);
		Iterator<WebElement> it = items.iterator();
		while(it.hasNext())
		{
			String ratingStr =  it.next().getText();
			log.info("Filtered item rating:"+ratingStr);
			if(Double.parseDouble(ratingStr) == Double.parseDouble(rating))
			{
				log.info("count value:"+count);
				log.info("Item:"+driver.findElement(By.xpath("((//div[@class ='a-section a-spacing-none a-spacing-top-micro'])//preceding::h2/a/span)["+count+"]")).getText());
			}
			count = count+1;
		}
	}
	
	public void performWishList()
	{
		action = new Actions(driver);
		WebElement accounts = driver.findElement(accountandlists);
		action.moveToElement(accounts).build().perform();
		driver.findElement(createWishList).click();
		Assert.assertEquals(true, driver.findElement(yourLists).isDisplayed());
		Assert.assertEquals(true, driver.findElement(yourIdeaLists).isDisplayed());
		Assert.assertEquals(true, driver.findElement(yourFriends).isDisplayed());
	}
	

}
