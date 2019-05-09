package exercise;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class Ex12 extends CoreTestCase {

    @Test
    public void testForExercise12() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        List result_list = SearchPageObject.getResultListOfSearch();
        assertTrue(
                "Quantity of ResultSearchList is less than 3",
                result_list.size() >= 3
        );

        List<String> list_of_title = asList("Java", "JavaScript", "Java (programming language)");
        List<String> list_of_description = asList("Island of Indonesia", "Programming language", "Object-oriented programming language");

        for (int i = 0; i < list_of_title.size(); i++) {
            SearchPageObject.waitForElementByTitleAndDescription(list_of_title.get(i), list_of_description.get(i));
        }
    }
}
