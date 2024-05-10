package tbc.tbcacademy.ge.data.steps.petstoresteps;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import static io.restassured.RestAssured.given;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;

public class UpdatePetSteps extends CommonSteps<UpdatePetSteps> {
    public UpdatePetSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }
    @Step("change pet name in pojo")
    public UpdatePetSteps updatePetPOJOName(String petPOJOName) {
        pet.setName(petPOJOName);
        return this;
    }
    @Step("change pet status in pojo")
    public UpdatePetSteps updatePetPOJOStatus(String petPOJOStatus) {
        pet.setStatus(petPOJOStatus);
        return this;
    }
    @Step("send updated pet")
    public UpdatePetSteps updatePet() {
        response = given(requestSpecification)
                .contentType(ContentType.URLENC)
                .formParam("name", pet.getName())
                .formParam("status", pet.getStatus())
                .when()
                .post("/pet/{petId}", pet.getId());
        return this;
    }
}
