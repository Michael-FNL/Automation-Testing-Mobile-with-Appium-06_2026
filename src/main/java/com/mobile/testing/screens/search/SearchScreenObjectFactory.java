package com.mobile.testing.screens.search;

import com.mobile.testing.utils.variables.OSType;

import static com.codeborne.selenide.appium.ScreenObject.screen;
import static com.mobile.testing.utils.parameters.TestDataParams.testConfig;

public class SearchScreenObjectFactory {

    public static SearchScreen get() {
        return testConfig().getOSType().equals(OSType.ANDROID) ?
                screen(AndroidSearchScreen.class) :
                screen(IOSSearchScreen.class);
    }
}
