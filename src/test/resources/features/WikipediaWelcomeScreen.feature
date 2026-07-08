Feature: Wikipedia welcome screen

  As a regular user of the Wikipedia application, I want to be able to see the Welcome screen.

  @smoke
  @regression
  Scenario: Open application on the welcome screen
    Given user open application
    When welcome screen ready
    Then user can see Explore icon

  @smoke
  @regression
  Scenario: Search field accepts user input
    Given user open application
    When welcome screen ready
    When user type in search line "Appium"
    Then user can see search results visible
