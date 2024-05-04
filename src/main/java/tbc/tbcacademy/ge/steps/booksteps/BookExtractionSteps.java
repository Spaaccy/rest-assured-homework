package tbc.tbcacademy.ge.steps.booksteps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import tbc.tbcacademy.ge.steps.CommonSteps;

import static tbc.tbcacademy.ge.data.constants.BookStoreData.BOOKS_ENDPOINT;
import static tbc.tbcacademy.ge.data.constants.BookStoreData.BOOK_STORE_URL;
import static io.restassured.RestAssured.given;

public class BookExtractionSteps extends CommonSteps {
    private Response bookStoreResponse;



    // i have to use constructor if i want to call the common step method(because provider creates instance not test class), idk if thats allowed, hopefully it is :)
    public BookExtractionSteps() {
        setRequestSpecification(BOOK_STORE_URL);
    }

    @Step("get book response with no query")
    public BookExtractionSteps getBookResponseNoQuery() {
        bookStoreResponse = requestSpecification
                .get(BOOKS_ENDPOINT);
        return this;
    }
    @Step("extract isbn from book response json")
    public String extractBookISBNUsingIndex(int index) {
        return bookStoreResponse.jsonPath().getString("books[" + index + "].isbn");
    }
    @Step("extract index from book response json")
    public String extractBookAuthorUsingIndex(int index) {
        return bookStoreResponse.jsonPath().getString("books[" + index + "].author");
    }
}
