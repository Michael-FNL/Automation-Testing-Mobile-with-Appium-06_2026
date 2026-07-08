Feature: Wikipedia search result

    As a regular user of the Wikipedia application, I want to be able to see the search result screen.

    @smoke
    @regression
    Scenario: Open application on the search result screen
        Given user open application
        When welcome screen ready
        When user type in search line "Appium"
        Then user can see search results visible
        When user open search result "Automation for Apps"
        Then user can see Retry button