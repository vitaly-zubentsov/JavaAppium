package exercise;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex17 extends CoreTestCase {

    private static String NAME_OF_FOLDER = "Learning programming";
    private static String
            login = "Vitaly.zubentsov",
            password = "98511985";


    @Test
    public void testForExercise17() {


        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        String article_description_substring_first = "bject-oriented programming language";
        String article_title_from_search_list_first = SearchPageObject.getArticleTitleFromSearchList(article_description_substring_first);
        SearchPageObject.clickByArticleWithSubstring(article_description_substring_first);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyListInNewFolder(NAME_OF_FOLDER);
        } else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeAuthWindow();
        } else {
            ArticlePageObject.addArticlesToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            String article_title = ArticlePageObject.getArticleTitle();
            ArticlePageObject.waitForTitleElement();
            assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        if ((Platform.getInstance().isAndroid() || Platform.getInstance().isMW())) {
            SearchPageObject.typeSearchLine(search_line);
        }

        String article_description_substring_second = "ikimedia list article";
        String article_title_from_search_list_second = SearchPageObject.getArticleTitleFromSearchList(article_description_substring_second);
        SearchPageObject.clickByArticleWithSubstring(article_description_substring_second);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyListInExistFolder(NAME_OF_FOLDER);
        } else {

            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title_from_search_list_second);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_title_from_search_list_second);
        MyListsPageObject.waitForArticleToAppearByTitle(article_title_from_search_list_first);

        String article_title_from_my_list = MyListsPageObject.getTitleOfElementBySubstring(article_title_from_search_list_first);

        assertEquals(
                "We see unexpected title",
                article_title_from_search_list_first,
                article_title_from_my_list
        );
    }

}