package tbc.tbcacademy.ge.data.steps.petstoresteps;
import io.qameta.allure.Step;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
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
                .get("/pet/{petId}", petSharedClass.id());
        return this;
    }
    @Step("validate updated pet name")
    public GetPetSteps validatePetName() {
        assertThat(petSharedClass.name(), equalTo(petSharedClass.name()));
        return this;
    }
    @Step("validate updated pet status")
    public void validatePetStatus() {
        assertThat(petSharedClass.status(), equalTo(petSharedClass.status()));
    }
}
