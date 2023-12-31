/**
 * 
 * @author: RaviKumar Mogulluru
 *	
 */
package com.test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features = "src/test/resources/features/AmazonTest.feature",
	glue= "com.test.stepDefinitions",
	plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
	tags = "@sanity",
	monochrome = true,
	dryRun = false
	)

public class TestRunner extends AbstractTestNGCucumberTests  {

}
