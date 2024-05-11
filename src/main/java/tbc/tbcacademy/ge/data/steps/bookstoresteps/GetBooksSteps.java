package tbc.tbcacademy.ge.data.steps.bookstoresteps;
import io.qameta.allure.Step;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.BookStoreData.BOOKS_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForBooking;

public class GetBooksSteps extends CommonSteps<GetBooksSteps> {
    public GetBooksSteps() {
        requestSpecification = getBaseRequestSpecForBooking();
    }
    @Step("get book response")
    public GetBooksSteps getBooksResponse() {
        response =
                given(requestSpecification)
                        .when()
                        .get(BOOKS_ENDPOINT);
        return this;
    }
    @Step("validate are book pages are less than: {0}")
    public GetBooksSteps validateAllBookPagesAreLessThan(int pages) {
        assertThat(bookStoreResponseClass.getBooks(), everyItem(hasProperty("pages", lessThan(pages))));
        return this;
    }
    @Step("validate first and second book authors equal: {0}")
    public void validateLastTwoBookAuthors(String authorBeforeLast, String authorLast) {
        assertThat(
                bookStoreResponseClass.getBooks()
                                .subList(bookStoreResponseClass.getBookSize()-2, bookStoreResponseClass.getBookSize())
                ,contains(
                    hasProperty("author", equalTo(authorBeforeLast)),
                    hasProperty("author", equalTo(authorLast))
        ));
    }
}
