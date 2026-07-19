package com.mobile.testing.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import com.mobile.testing.annotations.Step;
import com.mobile.testing.utils.services.SauceLabsService;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static com.mobile.testing.utils.parameters.TestDataParams.testConfig;

public class AppiumSauceLabsDriver {

    private final String appStorageId;

    public AppiumSauceLabsDriver() {
        this.appStorageId = new SauceLabsService().uploadAppIfNotPresentToStorage();
    }

    @Step("Create remote Sauce Labs Appium driver based on OS")
    public AppiumDriver createDriver() {

        AppiumDriver driver;
        String sauceUrl = "https://ondemand.%s.saucelabs.com/wd/hub"
                .formatted(testConfig().getSauceDataCenter());

        try {
            switch (testConfig().getOSType()) {
                case ANDROID -> {
                    UiAutomator2Options options = new UiAutomator2Options();
                    options.setApp("storage:%s".formatted(appStorageId));
                    options.setPlatformVersion(testConfig().getPlatformVersion());
                    options.setDeviceName(testConfig().getDeviceName());
                    options.setNewCommandTimeout(Duration.ofSeconds(60));
                    options.setFullReset(false);
                    options.setCapability("sauce:options", getSauceOptions());

                    driver = new AndroidDriver(new URL(sauceUrl), options);
                }
                case IOS -> {
                    XCUITestOptions options = new XCUITestOptions();
                    options.setApp("storage:%s".formatted(appStorageId));
                    options.setPlatformVersion(testConfig().getPlatformVersion());
                    options.setDeviceName(testConfig().getDeviceName());
                    options.setNewCommandTimeout(Duration.ofSeconds(60));
                    options.setFullReset(false);
                    options.setCapability("sauce:options", getSauceOptions());

                    driver = new IOSDriver(new URL(sauceUrl), options);
                }
                default -> throw new IllegalStateException("Unsupported OS type for Sauce Labs driver");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return driver;
    }

    @Step("Build Sauce Labs specific capabilities")
    private MutableCapabilities getSauceOptions() {
        MutableCapabilities sauceOptions = new MutableCapabilities();

        sauceOptions.setCapability("username", testConfig().getSauceUsername());
        sauceOptions.setCapability("accessKey", testConfig().getSauceAccessKey());
        sauceOptions.setCapability("build", testConfig().getSauceBuildName());
        sauceOptions.setCapability("name", "Wikipedia mobile test - " + testConfig().getOSType().getOsType());

        return sauceOptions;
    }
}