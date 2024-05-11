package tbc.tbcacademy.ge.data.steps.restfulbookersteps;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerUserRequest;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerAuthResponse;
import tbc.tbcacademy.ge.data.specbuilder.RequestSpecs;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import static io.restassured.RestAssured.given;
import static tbc.tbcacademy.ge.data.constants.BookerData.AUTH_ENDPOINT;

public class GetBookerTokenSteps extends CommonSteps<GetBookerTokenSteps> {
    RequestSpecification requestSpecification;
    public GetBookerTokenSteps setUserInfo(){
        bookerUserRequest = new BookerUserRequest();
        bookerUserRequest.setUserName("admin");
        bookerUserRequest.setPassword("password123");
        return this;
    }
    public GetBookerTokenSteps() {
        requestSpecification = RequestSpecs.getRequestSpecForBookerToken();
    }
    public GetBookerTokenSteps getRequestToken(){
        response = given(requestSpecification)
                .given()
                .body(bookerUserRequest)
                .when()
                .post(AUTH_ENDPOINT);
        return this;
    }
    public void extractTokenResponseAsClass() {
        bookerAuthResponse= validatableResponse
                .extract()
                .body()
                .as(BookerAuthResponse.class);
    }
}
