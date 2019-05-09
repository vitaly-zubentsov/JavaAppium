package exercise;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex3 extends CoreTestCase {


    @Test
    public void testForExercise3() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        assertTrue(
                "Quantity of ResultSearchList is less than 2",
                SearchPageObject.getQuantityOfResultList() > 1);

        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForEmptySearchImage();
    }

}
