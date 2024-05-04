package tbc.tbcacademy.ge.testbooks;

import tbc.tbcacademy.ge.data.DataProviders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.steps.booksteps.BookSteps;
import tbc.tbcacademy.ge.steps.CommonSteps;
import static tbc.tbcacademy.ge.data.constants.BookStoreData.BOOK_STORE_URL;
import static tbc.tbcacademy.ge.data.constants.BookStoreData.NOT_AUTHORIZED_MSG;
import static tbc.tbcacademy.ge.data.constants.CommonData.NO_VALID_SESSION;
import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;


@Test
public class TestBooksAPI {
    BookSteps bookSteps;

    @BeforeClass(alwaysRun = true)
    void beforeClass() {
        bookSteps = new BookSteps();
    }
    @BeforeMethod(alwaysRun = true)
    void beforeMethod() {
        CommonSteps.setRequestSpecification(BOOK_STORE_URL);
    }

    @Test(dataProvider = "IsbnAuthorProviderUsingIndex", dataProviderClass = DataProviders.class, priority = 1)
    void booksTestWithParams(String isbn, String author){
        bookSteps
                .getBookResponseWithQuery(isbn)
                .getValidateResponseBookData();
        bookSteps
                .checkStatusCode(SUCCESS_CODE);
        bookSteps
                .checkAuthorValue(author)
                .checkHasTitle()
                .checkHasPublishDate()
                .checkHasPages()
                .checkIsbnValue(isbn);
    }

    @Test(priority = 2)
    void removeBook() {
        bookSteps
                .deleteBook()
                .getValidateResponseBookData();
        bookSteps.checkStatusCode(NO_VALID_SESSION);
        bookSteps.checkStatusCodeMessage(NOT_AUTHORIZED_MSG);
    }
}
