package tbc.tbcacademy.ge.data.steps.petstoresteps;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import static io.restassured.RestAssured.given;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;

public class UpdatePetSteps extends CommonSteps<UpdatePetSteps> {
    public UpdatePetSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }
    @Step("change pet name in pojo")
    public UpdatePetSteps updatePetPOJOName(String petPOJOName) {
        petSharedClass.setName(petPOJOName);
        return this;
    }
    @Step("change pet status in pojo")
    public UpdatePetSteps updatePetPOJOStatus(String petPOJOStatus) {
        petSharedClass.setStatus(petPOJOStatus);
        return this;
    }
    @Step("send updated pet")
    public UpdatePetSteps updatePet() {
        response = given(requestSpecification)
                .contentType(ContentType.URLENC)
                .formParam("name", petSharedClass.getName())
                .formParam("status", petSharedClass.getStatus())
                .when()
                .post("/pet/{petId}", petSharedClass.getId());
        return this;
    }
}
