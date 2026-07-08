package com.mobile.testing.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Entry point TestNG uses to launch Cucumber.
 * Surefire finds this class via the suite XML (cucumber.xml) below,
 * not by name-matching — that's what makes it "the run".
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.mobile.testing"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true,
        tags = "@smoke"
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
