package com.mobile.testing.steps.search;

import io.cucumber.java.en.Then;
import com.codeborne.selenide.Condition;
import com.mobile.testing.annotations.Step;
import com.mobile.testing.screens.search.SearchScreen;
import com.mobile.testing.screens.search.SearchScreenObjectFactory;
import io.cucumber.java.en.When;

// TODO -->
// -- This step for the Search screen should be changed and finished in courses.
// -- You need to think over the logic of using this Search screen.
public class SearchStepDefinitionsSteps {

    private final SearchScreen searchScreen = SearchScreenObjectFactory.get();

    /**
     * <b> Search details by user in search line </b>
     * @param searchInput - search details by user in search line
     */
    @Step()
    @Then("user type in search line {string}")
    public void userTypeInSearchLine(String searchInput) {
        searchScreen.clickOnSearchField();
        searchScreen.searchValue(searchInput);
    }

    /**
     * <b> Verify if the user can see search results </b>
     */
    @Step()
    @Then("user can see search results visible")
    public void userCanSeeSearchResults() {
        searchScreen.verifyFirstSearchResultIsVisible(Condition.visible);
    }

    /**
     * <b> Clear the search field </b>
     */
    @Step()
    @Then("clear search field")
    public void clearSearchField() {
        searchScreen.clearSearchField();
    }

    @When("user open search result {string}")
    public void openSearchResult(final String searchInput) {
        searchScreen.clickOnSearchResult(searchInput);
    }

    @Then("user can see Retry button")
    public void verifyRetryButtonIsVisible() {
        searchScreen.verifyRetryButtonIsVisible();
    }
}
