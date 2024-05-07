package tbc.tbcacademy.ge.steps;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CommonSteps {
    protected static RequestSpecification requestSpecification;
    protected ValidatableResponse validatableResponse;

    @Step("set base url and json")
    public static void setRequestSpecification(String url) {
        requestSpecification = given()
                .baseUri(url)
                .accept("application/json")
                .when();
    }
    @Step("check response status code equals: {0}")
    public void checkStatusCode(int statusCode) {
        validatableResponse.statusCode(statusCode);
    }
}
