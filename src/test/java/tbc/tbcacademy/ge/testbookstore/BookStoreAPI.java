package tbc.tbcacademy.ge.testbookstore;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.restfulbookersteps.GetBooksSteps;
import static tbc.tbcacademy.ge.data.constants.BookStoreData.*;

public class BookStoreAPI {
    GetBooksSteps getBooksSteps;
    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        getBooksSteps = new GetBooksSteps();
    }
    @Test(priority = 1)
    public void validateBookingTest() {
        getBooksSteps
                .getBooksResponse()
                .getValidatableResponse()
                .checkStatusCode()
                .validateAllBookPagesAreLessThan(PAGES)
                .validateFirstBookAuthor(FIRST_AUTHOR)
                .validateSecondBookAuthor(SECOND_AUTHOR);
    }
}
