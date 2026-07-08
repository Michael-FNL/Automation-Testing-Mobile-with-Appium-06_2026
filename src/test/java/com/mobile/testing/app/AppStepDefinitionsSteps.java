package com.mobile.testing.app;

import io.cucumber.java.en.Given;

import static com.codeborne.selenide.Selenide.open;

public class AppStepDefinitionsSteps {

    @Given("user open application")
    public void userOpenApplication() {
        open();
    }
}