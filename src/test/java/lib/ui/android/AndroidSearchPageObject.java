package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]//..//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_RESULT_BY_SUBSTRINGS_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{TITLE}')]//..//*[contains(@text,'{DESCRIPTION}')]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_container']//*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_RESULT_ELEMENTS = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_EMPTY_IMAGE = "id:org.wikipedia:id/search_empty_image";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {

        super(driver);
    }
}
