package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_MY_LIST_BUTTON = "css:#page-actions li#page-actions-watch .watch-this-article";
        OPTIONS_ADD_TO_MY_LIST = "css:#page-actions li#page-actions-watch .watch-this-article:not(.watched)";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#page-actions-watch .watch-this-article.watched";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {

        super(driver);
    }
}
