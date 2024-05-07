package tbc.tbcacademy.ge.data.steps.petstoresteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import tbc.tbcacademy.ge.data.models.responses.Pet;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;

public class FindPetSteps extends CommonSteps<FindPetSteps> {
    public FindPetSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }

    @Step("get my added pet response")
    public FindPetSteps getAddedPet() {
        response = given(requestSpecification)
                .queryParam("status", petBody.getString("status"))
                .when()
                .get("/pet/findByStatus");
        return this;
    }
    @Step("validate response contains my id")
    public FindPetSteps validateResponseContainsID() {
        validatableResponse
                .body("id", hasItem(petBody.getInt("id")));
        return this;
    }
    @Step("find and extract my added pet as object using pojo")
    public FindPetSteps extractMyAddedPet() {
        // gavarchie pojo klasebic, shemedzlo aq pirdapir jsonOBJECT an Map<String, Object> damebrunebina
        pet = response
                .jsonPath()
                .getObject("find { it.id == " + petBody.getInt("id") + "}", Pet.class);
        return this;
    }
    @Step("validate pet response equals my pet json")
    public void validateMyPetObjectResponse() throws JsonProcessingException {
        assertThat(petBody.toString(), equalTo(mapper.writeValueAsString(pet)));
    }
}
