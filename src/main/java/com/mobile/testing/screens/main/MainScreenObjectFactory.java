package com.mobile.testing.screens.main;

import com.mobile.testing.utils.variables.OSType;

import static com.codeborne.selenide.appium.ScreenObject.screen;
import static com.mobile.testing.utils.parameters.TestDataParams.testConfig;

public class MainScreenObjectFactory {

    public static MainScreen get() {
        return testConfig().getOSType().equals(OSType.ANDROID) ?
                screen(AndroidMainScreen.class) :
                screen(IOSMainScreen.class);
    }
}
