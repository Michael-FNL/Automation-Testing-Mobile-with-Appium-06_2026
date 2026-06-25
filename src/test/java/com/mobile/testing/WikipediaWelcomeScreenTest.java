package com.mobile.testing;

import com.codeborne.selenide.Condition;
import com.mobile.testing.steps.search.SearchStepDefinitionsSteps;
import com.mobile.testing.steps.welcome.WelcomeStepDefinitionsSteps;
import org.testng.annotations.Test;

public class WikipediaWelcomeScreenTest extends BaseTest {

    @Test
    public void openApplicationOnTheWelcomeScreen() {
        userOpenApplication();
        WelcomeStepDefinitionsSteps welcomeStepDefinitionsSteps = new WelcomeStepDefinitionsSteps();
        welcomeStepDefinitionsSteps.welcomeScreenReady();
        welcomeStepDefinitionsSteps.userCanSeeExploreIcon();
    }

    @Test
    public void userCanTypeInSearchLine() {
        userOpenApplication();
        WelcomeStepDefinitionsSteps welcomeStepDefinitionsSteps = new WelcomeStepDefinitionsSteps();
        welcomeStepDefinitionsSteps.welcomeScreenReady();
        SearchStepDefinitionsSteps searchStepDefinitionsSteps = new SearchStepDefinitionsSteps();
        searchStepDefinitionsSteps.userTypeInSearchLine("august");
        searchStepDefinitionsSteps.userCanSeeSearchResults(Condition.visible);
    }

    @Test
    public void userCanTypeInSearchAndCleanTheResultAndGoBackToStartPage() {
        userOpenApplication();
        WelcomeStepDefinitionsSteps welcomeStepDefinitionsSteps = new WelcomeStepDefinitionsSteps();
        welcomeStepDefinitionsSteps.welcomeScreenReady();
        SearchStepDefinitionsSteps searchStepDefinitionsSteps = new SearchStepDefinitionsSteps();
        searchStepDefinitionsSteps.userTypeInSearchLine("august");
        searchStepDefinitionsSteps.userCanSeeSearchResults(Condition.visible);
        searchStepDefinitionsSteps.clearSearchField();
        searchStepDefinitionsSteps.clearSearchField();
        welcomeStepDefinitionsSteps.welcomeScreenReady();
    }
}
