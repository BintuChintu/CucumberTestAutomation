package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
WebDriver driver;
	
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement pwd;
	
	@FindBy(id="submit")
	WebElement login;
	
	public void login(String userName,String passWord)
	{
		username.sendKeys(userName);
		pwd.sendKeys(passWord);
		login.click();
	}
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

}
