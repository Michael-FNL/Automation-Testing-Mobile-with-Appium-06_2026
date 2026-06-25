package com.mobile.testing;

import com.codeborne.selenide.logevents.SimpleReport;
import com.mobile.testing.configuration.MobileAppiumDriverFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    private final SimpleReport report = new SimpleReport();
    private final MobileAppiumDriverFactory driverFactory = new MobileAppiumDriverFactory();

    @BeforeClass
    public void createSession() {
        report.start();
        driverFactory.getWebDriverInstance();
    }

    public void userOpenApplication() {
        open();
    }

    @AfterClass
    public void closeSession(ITestContext context) {
        driverFactory.closeDriver();
        report.finish(context.getName());
    }

}
