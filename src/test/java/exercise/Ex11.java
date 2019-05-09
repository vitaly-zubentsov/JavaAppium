package exercise;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex11 extends CoreTestCase {

    private static String NAME_OF_FOLDER = "Learning programming";

    @Test
    public void testForExercise11() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        String article_substring_first = "Java (programming language)";
        String article_title_from_search_list_first = SearchPageObject.getArticleTitleFromSearchList(article_substring_first);
        SearchPageObject.clickByArticleWithSubstring(article_substring_first);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyListInNewFolder(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeAuthWindow();
        }
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.typeSearchLine(search_line);
        }

        String article_substring_second = "JavaScript";
        String article_title_from_search_list_second = SearchPageObject.getArticleTitleFromSearchList(article_substring_second);
        SearchPageObject.clickByArticleWithSubstring(article_substring_second);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyListInExistFolder(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title_from_search_list_second);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_title_from_search_list_second);
        MyListsPageObject.waitForArticleToAppearByTitle(article_title_from_search_list_first);

        String article_title_from_my_list = MyListsPageObject.getTitleOfElementBySubstring(article_substring_first);

        assertEquals(
                "We see unexpected title",
                article_title_from_search_list_first,
                article_title_from_my_list
        );
    }

}