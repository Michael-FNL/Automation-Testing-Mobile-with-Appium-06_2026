package com.mobile.testing.steps.welcome;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.mobile.testing.annotations.Step;
import com.mobile.testing.screens.main.MainScreen;
import com.mobile.testing.screens.main.MainScreenObjectFactory;

public class WelcomeStepDefinitionsSteps {

    private final MainScreen mainScreen = MainScreenObjectFactory.get();

    /**
     * <b>Wait for welcome screen ready </b>
     */
    @When("welcome screen ready")
    public void welcomeScreenReady() {
        mainScreen.waitForMainContainerToAppear();
    }

    /**
     * <b> Verify if the user can see Explore icon </b>
     */
    @Step
    @Then("user can see Explore icon")
    public void userCanSeeExploreIcon() {
        mainScreen.checkExploreIcon();
    }

    /**
     * <b> User tap on the search field </b>
     */
    @Step
    @Then("user tap on the search field")
    public void userTypeInSearchLine() {
        mainScreen.clickOnSearchField();
    }
}
