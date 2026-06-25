package com.mobile.testing.steps.welcome;

import com.mobile.testing.annotations.Step;
import com.mobile.testing.screens.main.MainScreen;
import com.mobile.testing.screens.main.MainScreenObjectFactory;

public class WelcomeStepDefinitionsSteps {

    private final MainScreen mainScreen = MainScreenObjectFactory.get();

    /**
     * <b>Wait for welcome screen ready </b>
     */
    @Step()
    public void welcomeScreenReady() {
        mainScreen.waitForMainContainerToAppear();
    }

    /**
     * <b> Verify if the user can see Explore icon </b>
     */
    @Step
    public void userCanSeeExploreIcon() {
        mainScreen.checkExploreIcon();
    }

    /**
     * <b> User tap on the search field </b>
     */
    @Step
    public void userTypeInSearchLine() {
        mainScreen.clickOnSearchField();
    }
}
