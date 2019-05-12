package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_MY_LIST_BUTTON,
            OPTIONS_ADD_TO_MY_LIST,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_EXIST_FOLDER_TPL,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            CLOSE_AUTH_PLACE;


    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder) {

        return ADD_TO_EXIST_FOLDER_TPL.replace("{EXIST_FOLDER}", name_of_folder);
    }

    /* TEMPLATES METHODS */

    public ArticlePageObject(RemoteWebDriver driver) {

        super(driver);
    }

    public WebElement waitForTitleElement() {

        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title on page!",
                15
        );
    }

    public String getArticleTitle() {

        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }

    }

    public void swipeToFooter() {

        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    100
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    100
            );
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    100
            );
        }

    }

    public void addArticleToMyListInNewFolder(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );


        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle() {

        if ((Platform.getInstance().isAndroid() || Platform.getInstance().isIOS())) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot find close article, cannot find X link",
                    5
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platfom " + Platform.getInstance().getPlatformVar());
        }
    }

    public void addArticleToMyListInExistFolder(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option to add article to reading list",
                5
        );


        this.waitForElementAndClick(
                getFolderXpathByName(name_of_folder),
                "Cannot find created folder '" + name_of_folder + "' in reading list",
                5
        );
    }

    public void assertTitleIsPresent() {

        assertElementPresent(
                TITLE,
                "Cannot find title of article"
        );
    }

    public void addArticlesToMySaved() {

        if (Platform.getInstance().isMW()) {
            removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST, "Cannot find option to add article to reading list", 5);
    }

    public void removeArticleFromSavedIfItAdded() {

        this.waitForElementPresent(
                OPTIONS_MY_LIST_BUTTON,
                "Cannot find button to add or remove article from My List",
                10
        );


          if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    10
            );
            this.waitForElementNotPresent(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Remove button an article from saved is still on page",
                    10
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST,
                    "Cannot find button to add an article to saved list after removing it from list before",
                    10
            );
        }
    }


    public void closeAuthWindow() {

        this.waitForElementAndClick(
                CLOSE_AUTH_PLACE,
                "Cannot find place to close auth window",
                5
        );

    }

    ;
}
