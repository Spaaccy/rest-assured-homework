package tbc.tbcacademy.ge.testopenlibrary;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.steps.CommonSteps;
import tbc.tbcacademy.ge.steps.openlibrarysteps.OpenLibrarySteps;
import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;
import static tbc.tbcacademy.ge.data.constants.OpenLibraryData.*;

public class TestOpenLibraryAPI {
    OpenLibrarySteps openLibrarySteps;
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        openLibrarySteps = new OpenLibrarySteps();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        CommonSteps.setRequestSpecification(OPEN_LIBRARY_URL);
    }
    @Test
    public void testOpenLibraryAPI() {
        openLibrarySteps
                .getLibraryBookResponse()
                .getLibraryBookValidatableResponse();
        openLibrarySteps
                .checkStatusCode(SUCCESS_CODE);
        openLibrarySteps
                .validateResponseContainsBooks()
                .validateFirstBookTitle(BOOK_TITLE)
                .validateFirstBookAuthor(BOOK_AUTHOR)
                .validateFirstBookPlaceArray(OPEN_LIBRARY_PLACES);
    }
}
