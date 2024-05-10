package tbc.tbcacademy.ge.data.steps.restfulbookersteps;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.BookStoreData.BOOKS_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForBooking;

public class GetBooksSteps extends CommonSteps<GetBooksSteps> {
    public GetBooksSteps() {
        requestSpecification = getBaseRequestSpecForBooking();
    }
    @Step("get book response")
    public GetBooksSteps getBooksResponse(){
        response =
                given(requestSpecification)
                        .when()
                        .get(BOOKS_ENDPOINT);
        return this;
    }
    @Step("validate are book pages are less than: {0}")
    public GetBooksSteps validateAllBookPagesAreLessThan(int pages) {
        validatableResponse
                .body("books.pages", everyItem(lessThan(pages)));
        return this;
    }
    @Step("validate first book author equals: {0}")
    public GetBooksSteps validateFirstBookAuthor(String author) {
        validatableResponse
                .body("books.author[0]", equalTo(author));
        return this;
    }
    @Step("validate second book author equals: {0}")
    public void validateSecondBookAuthor(String author) {
        validatableResponse
                .body("books.author[1]", equalTo(author));
    }

}
