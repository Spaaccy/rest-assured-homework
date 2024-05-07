package tbc.tbcacademy.ge.steps.openlibrarysteps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import tbc.tbcacademy.ge.steps.CommonSteps;

import static tbc.tbcacademy.ge.data.constants.OpenLibraryData.MIN_BOOK_PAGES;
import static tbc.tbcacademy.ge.data.constants.OpenLibraryData.SEARCH_JSON_ENDPOINT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OpenLibrarySteps extends CommonSteps {
    Response response;

    @Step("get open library books response")
    public OpenLibrarySteps getLibraryBookResponse() {
        response =
                requestSpecification
                .queryParam("q", "Harry Potter")
                .get(SEARCH_JSON_ENDPOINT);
        return this;
    }
    @Step("get open library books validatable response")
    public void getLibraryBookValidatableResponse() {
        validatableResponse =
                response.then();
    }
    @Step("check open library books response contains books")
    public OpenLibrarySteps validateResponseContainsBooks() {
        validatableResponse.body("numFound", greaterThan(MIN_BOOK_PAGES));
        return this;
    }
    @Step("check first book title equals: {0}")
    public OpenLibrarySteps validateFirstBookTitle(String bookTitle) {
        validatableResponse.body("docs[0].title", equalTo(bookTitle));
        return this;
    }
    @Step("check first book author equals: {0}")
    public OpenLibrarySteps validateFirstBookAuthor(String bookAuthor) {
        validatableResponse.body("docs[0].author_name[0]", equalTo(bookAuthor));
        return this;
    }
    @Step("check first book place array equals: {0}")
    public void validateFirstBookPlaceArray(String[] places) {
        validatableResponse.body("docs[0].place", hasItems(places));
    }
}
