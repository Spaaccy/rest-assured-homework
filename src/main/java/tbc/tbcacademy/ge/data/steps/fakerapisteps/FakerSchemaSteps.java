package tbc.tbcacademy.ge.data.steps.fakerapisteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import tbc.tbcacademy.ge.data.models.responses.faker.CreditCardResponse;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import java.io.File;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static tbc.tbcacademy.ge.data.constants.FakerData.FAKER_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForFaker;

public class FakerSchemaSteps extends CommonSteps<FakerSchemaSteps> {
    CreditCardResponse creditCardResponse;
    File jsonSchema;
    String responseBodyJson;
    public FakerSchemaSteps() {
        requestSpecification = getBaseRequestSpecForFaker();
    }
    @Step("get schema file")
    public FakerSchemaSteps getSchemaFile() {
        jsonSchema = new File("src/main/java/tbc/tbcacademy/ge/data/steps/fakerapisteps/credit_cards_schema.json");
        return this;
    }
    @Step("get faker")
    public FakerSchemaSteps getFaker() {
        response = given(requestSpecification)
                .when()
                .get(FAKER_ENDPOINT);
        return this;
    }
    @Step("extract faker credit card response as class")
    public FakerSchemaSteps extractFakerCreditCardResponseAsClass() {
        creditCardResponse = validatableResponse
                .extract()
                .as(CreditCardResponse.class);
        return this;
    }
    @Step("serialize response as class")
    public FakerSchemaSteps serializeResponseAsClass() throws JsonProcessingException {
        responseBodyJson = mapper.writeValueAsString(creditCardResponse);
        return this;
    }
    @Step("validate using shcema")
    public void validateFakerSchema() {
        assertThat(responseBodyJson, matchesJsonSchema(jsonSchema));
    }

}
