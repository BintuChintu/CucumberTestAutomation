package com.test.runner;


import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/java/com/test/features",
	glue= "com.test.stepDefinitions",
	plugin = {"html:reports/cucumber-report.html","json:reports/cucumber-report.json"},
	monochrome = true,
	dryRun = false
	)

public class TestRunner {
	
}
