package com.mobile.testing.configuration;

import com.mobile.testing.annotations.Step;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.mobile.testing.drivers.AppiumDriverProvider;
import com.mobile.testing.drivers.AppiumLocalServer;
import com.mobile.testing.utils.variables.RunningPlatform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Optional;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.mobile.testing.utils.parameters.TestDataParams.testConfig;

public class MobileAppiumDriverFactory {

    @Step("Configure Selenide with Appium")
    public void getWebDriverInstance() {

        Configuration.browserSize = null;
        Configuration.browser = AppiumDriverProvider.class.getName();

        WebDriverRunner.addListener(new AbstractWebDriverEventListener() {
        });
    }

    @Step("Run Appium Server")
    public void appiumServerSetup() {
        if (testConfig().getRunningPlatform().equals(RunningPlatform.LOCAL)) {
            AppiumLocalServer.startServer();
            if (AppiumLocalServer.getServerUrl() == null) {
                throw new RuntimeException("""
                        Unable to start Appium server for: device %s
                        type OS: %s
                        """.formatted(testConfig().getDeviceName(), testConfig().getOSType().getOsType())
                );
            }
        }
    }

//    @Step("Shut down the driver")
//    public void closeDriver() {
//        try {
//            if (testConfig().getRunningPlatform() == RunningPlatform.EPAM_CLOUD) {
//                Optional.of(WebDriverRunner.driver().getWebDriver()).ifPresent(WebDriver::quit);
//            }
//            closeWebDriver();
//        } catch (WebDriverException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Step("Shut down the driver")
    public void closeDriver(boolean testPassed) {
        try {
            if (testConfig().getRunningPlatform() == RunningPlatform.EPAM_CLOUD
                    || testConfig().getRunningPlatform() == RunningPlatform.SAUCELABS) {

                if (!WebDriverRunner.hasWebDriverStarted()) {
                    return;
                }

                WebDriver driver = WebDriverRunner.driver().getWebDriver();

                if (testConfig().getRunningPlatform() == RunningPlatform.SAUCELABS) {
                    ((JavascriptExecutor) driver)
                            .executeScript("sauce:job-result=" + (testPassed ? "passed" : "failed"));
                }

                Optional.of(driver).ifPresent(WebDriver::quit);
            }
            closeWebDriver();
        } catch (WebDriverException e) {
            throw new RuntimeException(e);
        }
    }

}
