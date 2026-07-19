package com.mobile.testing;

import com.codeborne.selenide.logevents.SimpleReport;
import com.mobile.testing.configuration.MobileAppiumDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.jetbrains.annotations.NotNull;

public class BaseTest {

    private final SimpleReport report = new SimpleReport();
    private final MobileAppiumDriverFactory driverFactory = new MobileAppiumDriverFactory();

    @Before
    public void createSession(@NotNull Scenario scenario) {
        scenario.log("Starting Wikipedia Tests with " + scenario.getName());
        report.start();

        driverFactory.getWebDriverInstance();
    }

    @After
    public void closeSession(@NotNull Scenario scenario) {
        scenario.log("Finished Wikipedia Tests with " + scenario.getName());

        driverFactory.closeDriver(!scenario.isFailed());

        report.finish(scenario.getName());
    }

}
