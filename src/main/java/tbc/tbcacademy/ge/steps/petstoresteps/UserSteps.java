package tbc.tbcacademy.ge.steps.petstoresteps;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import tbc.tbcacademy.ge.model.UserData;
import tbc.tbcacademy.ge.steps.CommonSteps;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.*;

public class UserSteps extends CommonSteps {
    Response response;
    UserData userData;
    JsonPath jsonPath;

    @Step("create order body using: {0}, {1} values")
    public UserSteps createUserBody(String username, String password) {
        userData = new UserData();
        userData.username = username;
        userData.password = password;
        return this;
    }

    @Step("get user using params")
    public UserSteps createUser() {
        response = requestSpecification
                .basePath(USER_ENDPOINT)
                .queryParam("username", userData.username)
                .queryParam("password", userData.password)
                .when()
                .get("login");
        return this;
    }
    @Step("get validatable user response")
    public void getUserValidatableResponse() {
        validatableResponse =
                response.then();
    }
    @Step("get user response json")
    public void getUserJsonPath() {
        jsonPath = response.jsonPath();
    }
    @Step("validate user message using regex, that it contains atlest 10 digits")
    public UserSteps validateUserMessage() {
        validatableResponse
                .body("message", matchesPattern(CONTAINS_TEN_DIGIT_REGEX));
        return this;
    }
    @Step("extract numbers from response and return it")
    public String extractNumbersFromUserMessage(){
        String responseMessage = jsonPath.getString("message");
        Pattern pattern = Pattern.compile(ONLY_NUMBERS_REGEX);
        Matcher matcher = pattern.matcher(responseMessage);
        if(matcher.find()){
            return matcher.group();
        }
        return null;
    }
}
