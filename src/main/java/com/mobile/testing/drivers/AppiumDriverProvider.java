package com.mobile.testing.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.AppiumDriver;
import com.mobile.testing.annotations.Step;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.mobile.testing.utils.parameters.TestDataParams.testConfig;

@ParametersAreNonnullByDefault
public class AppiumDriverProvider implements WebDriverProvider {

    @Override
    @CheckReturnValue
    @Nonnull
    @Step("Create Appium Driver")
    public WebDriver createDriver(Capabilities capabilities) {

        AppiumDriver driver = null;

        switch (testConfig().getRunningPlatform()) {
            case LOCAL -> driver = new AppiumLocalDriver().createDriver();
            case EPAM_CLOUD -> driver = new AppiumRemoteDriver().createDriver();
        }

        assert driver != null;
        return driver;
    }
}
