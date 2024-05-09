package tbc.tbcacademy.ge.data.steps.petstoresteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import org.json.JSONArray;
import tbc.tbcacademy.ge.data.models.shared.petstore.PetShared;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;

public class FindPetSteps extends CommonSteps<FindPetSteps> {
    private PetShared[] allPets;
    private PetShared myPet;
    public FindPetSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }

    @Step("get my added pet response")
    public FindPetSteps getAddedPet() {
        response = given(requestSpecification)
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus");
        return this;
    }
    @Step("find and extract my added pet as object using pojo")
    public FindPetSteps extractPetResponseAsClass() {
        allPets = response
                .then()
                .extract()
                .body()
                .as(PetShared[].class);
        return this;
    }
    @Step("validate response contains my id")
    public FindPetSteps validateResponseContainsID() {
        assertThat(Arrays.asList(allPets), hasItem(hasProperty("id", equalTo(petSharedClass.getId()))));
        return this;
    }
    @Step("extract my pet")
    public FindPetSteps extractMyPet() {
        myPet = Arrays.stream(allPets).filter(pet -> pet.getId() == petSharedClass.getId()).findFirst().get();
        return this;
    }
    @Step("validate pet equals")
    public FindPetSteps validatePetEqualsPOJO() {
        assertThat(myPet, equalTo(petSharedClass));
        return this;
    }
}
