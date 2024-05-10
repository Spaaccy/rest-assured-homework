package tbc.tbcacademy.ge.data.steps.petstoresteps;
import io.qameta.allure.Step;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;

public class GetPetSteps extends CommonSteps<GetPetSteps> {
    public GetPetSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }
    @Step("get updated pet using id")
    public GetPetSteps getPet() {
        response = given(requestSpecification)
                .when()
                .get("/pet/{petId}", petBody.getInt("id"));
        return this;
    }
    @Step("validate updated pet name")
    public GetPetSteps validatePetName() {
        validatableResponse
                .body("name", equalTo(pet.getName()));
        return this;
    }
    @Step("validate updated pet status")
    public void validatePetStatus() {
        validatableResponse
                .body("status", equalTo(pet.getStatus()));
    }
}
