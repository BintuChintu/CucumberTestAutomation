package com.test.runner;


import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/resources/features/LoginTest.feature",
	glue= "com.test.stepDefinitions",
	plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
	monochrome = true,
	dryRun = false
	)

public class TestRunner {
	
}
