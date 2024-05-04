package tbc.tbcacademy.ge.steps.booksteps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import tbc.tbcacademy.ge.steps.CommonSteps;

import static tbc.tbcacademy.ge.data.constants.BookStoreData.BOOK_ENDPOINT;
import static tbc.tbcacademy.ge.data.constants.BookStoreData.MINIMUM_BOOK_PAGES;
import static org.hamcrest.Matchers.*;

public class BookSteps extends CommonSteps {
    private Response bookStoreResponse;


    @Step("get book response with query")
    public BookSteps getBookResponseWithQuery(String isbn) {
        bookStoreResponse = requestSpecification
                .queryParam("ISBN", isbn)
                .get(BOOK_ENDPOINT);
        return this;
    }
    @Step("delete book")
    public BookSteps deleteBook() {
        bookStoreResponse = requestSpecification
                .given()
                .delete(BOOK_ENDPOINT);
        return this;
    }
    @Step("get validatable response for books")
    public void getValidateResponseBookData() {
        validatableResponse =
                bookStoreResponse.then();
    }
    @Step("check book status code message equals: {0}")
    public void checkStatusCodeMessage(String message) {
        validatableResponse.body("message", equalTo(message));
    }
    @Step("check book author value equals: {0}")
    public BookSteps checkAuthorValue(String author) {
        validatableResponse.body("author", equalTo(author));
        return this;
    }
    @Step("check book has title")
    public BookSteps checkHasTitle() {
        validatableResponse.body("title",  not(emptyOrNullString()));
        return this;
    }
    @Step("check book has publisher")
    public BookSteps checkHasPublishDate() {
        validatableResponse.body("publish_date",  not(emptyOrNullString()));
        return this;
    }
    @Step("check book has pages greater than 0")
    public BookSteps checkHasPages() {
        validatableResponse.body("pages", greaterThan(MINIMUM_BOOK_PAGES));
        return this;
    }
    @Step("check response isbn value equals: {0}")
    public void checkIsbnValue(String isbn) {
        validatableResponse.body("isbn", equalTo(isbn));
    }
}
