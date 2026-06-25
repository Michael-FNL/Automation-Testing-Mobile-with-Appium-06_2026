package com.mobile.testing.steps.search;

import com.codeborne.selenide.Condition;
import com.mobile.testing.annotations.Step;
import com.mobile.testing.screens.search.SearchScreen;
import com.mobile.testing.screens.search.SearchScreenObjectFactory;

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
    public void userTypeInSearchLine(String searchInput) {
        searchScreen.clickOnSearchField();
        searchScreen.searchValue(searchInput);
    }

    /**
     * <b> Verify if the user can see search results </b>
     * @param condition - condition to check the visibility of the first search result
     */
    @Step()
    public void userCanSeeSearchResults(final Condition condition) {
        searchScreen.verifyFirstSearchResultIsVisible(condition);
    }

    /**
     * <b> Clear the search field </b>
     */
    @Step()
    public void clearSearchField() {
        searchScreen.clearSearchField();
    }
}
