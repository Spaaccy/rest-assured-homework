package tbc.tbcacademy.ge.data.steps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import tbc.tbcacademy.ge.data.models.responses.Pet;

import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;
import static tbc.tbcacademy.ge.data.specbuilder.ResponseSpecs.createResponseCheckerSpec;

public class CommonSteps<T extends CommonSteps> {
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static Faker faker = new Faker();
    protected static JSONObject petBody;
    protected static Pet pet;
    protected Response response;
    protected ValidatableResponse validatableResponse;
    protected RequestSpecification requestSpecification;


    public T getValidatableResponse() {
        validatableResponse = response
                .then()
                .assertThat();
        return (T) this;
    }
    public T checkStatusCode() {
        validatableResponse
                .spec(createResponseCheckerSpec(SUCCESS_CODE));
        return (T) this;
    }
}
