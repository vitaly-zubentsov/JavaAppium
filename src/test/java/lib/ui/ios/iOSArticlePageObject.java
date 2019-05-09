package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_AUTH_PLACE = "id:places auth close";
    }

    public iOSArticlePageObject(AppiumDriver driver) {

        super(driver);
    }
}
