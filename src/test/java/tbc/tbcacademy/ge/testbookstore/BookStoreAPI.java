package tbc.tbcacademy.ge.testbookstore;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.bookstoresteps.GetBooksSteps;
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
                .extractBookStoreResponseAsClass()
                .validateAllBookPagesAreLessThan(PAGES)
                .validateLastTwoBookAuthors(BEFORE_LAST, LAST_AUTHOR);
    }
}
