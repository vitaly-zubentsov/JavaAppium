package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MVSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input.search";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TITLE_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        // SEARCH_RESULT_BY_SUBSTRINGS_TITLE_AND_DESCRIPTION_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(),'{TITLE}')]//..//*[contains(@text,'{DESCRIPTION}')]"; необходимо изменить локатор, так как используется выделение текста для поиска Java
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
    }

    public MVSearchPageObject(RemoteWebDriver driver) {

        super(driver);
    }
}
