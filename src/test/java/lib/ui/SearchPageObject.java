package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TITLE_TPL,
            SEARCH_RESULT_BY_SUBSTRINGS_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_EMPTY_IMAGE,
            SEARCH_RESULT_ELEMENTS;


    public SearchPageObject(AppiumDriver driver) {

        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElementByTitle(String substring) {

        return SEARCH_RESULT_BY_SUBSTRING_TITLE_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {

        return SEARCH_RESULT_BY_SUBSTRINGS_TITLE_AND_DESCRIPTION_TPL.
                replace("{TITLE}", title).
                replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {

        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5
        );
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element"
        );

    }

    public void typeSearchLine(String search_line) {

        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                5
        );
    }

    public WebElement waitForSearchResult(String substring) {

        String search_result_xpath = getResultSearchElementByTitle(substring);
        return this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring " + substring
        );
    }

    public void waitForCancelButtonToAppear() {

        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5
        );
    }

    public void waitForCancelButtonToDisappear() {

        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    public void clickCancelSearch() {

        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                5
        );

    }

    public void clickByArticleWithSubstring(String substring) {

        String search_result_xpath = getResultSearchElementByTitle(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                10
        );
    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request - ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {

        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element ",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {


        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results"
        );
    }


    public void waitForEmptySearchImage() {
        waitForElementPresent(
                SEARCH_EMPTY_IMAGE,
                "Result list of search is still present on the page",
                5
        );
    }

    public List getResultListOfSearch() {

        return waitForElementsIsPresentAndGetListOfThem(
                SEARCH_RESULT_ELEMENTS,
                "Result List Of Search is not present",
                5);
    }

    public int getQuantityOfResultList() {

        return getResultListOfSearch().size();
    }

    public void waitForElementByTitleAndDescription(String title, String description) {

        String search_result_xpath = getResultSearchElementByTitleAndDescription(title, description);
        waitForElementPresent(
                search_result_xpath,
                "Result element with title " + title + " and description " + description + " is not present",
                5);
    }

    public String getArticleTitleFromSearchList(String substring) {

        WebElement title_element = waitForSearchResult(substring);
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    ;
}
