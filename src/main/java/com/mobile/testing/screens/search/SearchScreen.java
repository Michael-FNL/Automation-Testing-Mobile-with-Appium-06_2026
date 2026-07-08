package com.mobile.testing.screens.search;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import com.mobile.testing.utils.variables.OSType;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.mobile.testing.utils.parameters.TestDataParams.testConfig;

@SuppressWarnings("unused")
// TODO -->
// -- This screen should be changed and finished in courses.
// -- You need to change the selectors to the correct ones
// -- and think over the logic of using this screen.
public class SearchScreen {

    private final By SKIP_BUTTON = By.id("Skip");

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Explore\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Explore\"]")
    private SelenideElement exploreIcon;

    @AndroidFindBy(id = "org.wikipedia:id/fragment_feed_header")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeAny[@name='fragment_feed_header']")
    private SelenideElement searchWikipediaField;

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='search_src_text']")
    private SelenideElement searchField;

    @AndroidFindBy(id = "org.wikipedia:id/fragment_feed_header")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='fragment_feed_header']")
    private SelenideElement firstSearchResult;

    @AndroidFindBy(id = "org.wikipedia:id/search_close_btn")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='Clear query']")
    private SelenideElement clearSearchFieldButton;

    @AndroidFindBy(id = "org.wikipedia:id/view_wiki_error_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Retry']")
    private SelenideElement retryButton;

    private static final String DESCRIPTION_XPATH =
            "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='%s']";


    public void waitForMainContainerToAppear() {
        if (testConfig().getOSType() == OSType.IOS) {
            if ($(SKIP_BUTTON).is(Condition.visible)) {
                $(SKIP_BUTTON).click();
            }
        }

        exploreIcon.shouldBe(Condition.visible);
    }

    public void clickOnSearchField() {
        searchWikipediaField.shouldBe(Condition.visible).click();
    }

    public void searchValue(final String searchInput) {
        searchField.shouldBe(Condition.visible).setValue(searchInput);
    }

    public void verifyFirstSearchResultIsVisible(final Condition condition) {
        firstSearchResult.shouldBe(condition);
    }

    public void clearSearchField() {
        clearSearchFieldButton.shouldBe(Condition.visible).click();
    }

    public SelenideElement descriptionByText(String text) {
        return $(By.xpath(String.format(DESCRIPTION_XPATH, text)));
    }

    public void clickOnSearchResult(final String searchInput) {
        descriptionByText(searchInput).shouldBe(Condition.visible).click();
    }

    public void verifyRetryButtonIsVisible() {
        retryButton.shouldBe(Condition.visible);
    }
}
