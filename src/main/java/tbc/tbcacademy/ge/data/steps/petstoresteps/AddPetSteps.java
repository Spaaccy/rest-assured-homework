package tbc.tbcacademy.ge.data.steps.petstoresteps;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_DEFAULT_STATUS;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;

public class AddPetSteps extends CommonSteps<AddPetSteps> {
    private JSONObject petCategory;
    private JSONObject petTag1;
    private JSONObject petTag2;

    public AddPetSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }

    public JSONArray getPetTagArray() {
        return petBody.getJSONArray("tags");
    }

    @Step("create pet category json")
    public AddPetSteps createPetCategory() {
        petCategory = new JSONObject()
                .put("id", faker.number().randomDigitNotZero())
                .put("name", faker.animal().name());
        return this;
    }

    @Step("create pet tag json")
    public AddPetSteps createPetTag() {
        petTag1 = new JSONObject()
                .put("id", faker.number().randomDigitNotZero())
                .put("name", faker.funnyName().name());

        petTag2 = new JSONObject()
                .put("id", faker.number().randomDigitNotZero())
                .put("name", faker.funnyName().name());
        return this;
    }

    @Step("create pet body json")
    public AddPetSteps createPetBody() {
        JSONArray tagsArray = new JSONArray().put(petTag1).put(petTag2);
       petBody = new JSONObject()
                .put("id", faker.number().randomDigitNotZero())
                .put("category", petCategory)
                .put("name", faker.cat().name())
                .put("photoUrls", Collections.singletonList(faker.internet().url()))
                .put("tags", tagsArray)
                .put("status", PET_DEFAULT_STATUS);
        return this;

    }

    @Step("add pet and get the response")
    public AddPetSteps addPet(){
        response = given(requestSpecification)
                .body(petBody.toString())
                .when()
                .put(PET_ENDPOINT);
        return this;
    }

    @Step("validate pet response id")
    public AddPetSteps validatePetID() {
        validatableResponse
                .body("id", equalTo(petBody.getInt("id")));
        return this;
    }
    @Step("validate pet response category id")
    public AddPetSteps validatePetCategoryID() {
        validatableResponse
                .body("category.id", equalTo(petBody.getJSONObject("category").getInt("id")));
        return this;
    }
    @Step("validate pet response category name")
    public AddPetSteps validatePetCategoryName() {
        validatableResponse
                .body("category.name", equalTo(petBody.getJSONObject("category").getString("name")));
        return this;
    }
    @Step("validate pet response name")
    public AddPetSteps validatePetName() {
        validatableResponse
                .body("name", equalTo(petBody.getString("name")));
        return this;
    }
    @Step("validate pet response photo urls")
    public AddPetSteps validatePetPhotoURLs() {
        // tu ramdenimes davamatebt ase kvelas gatestavs
        JSONArray photoUrlsArray = petBody.getJSONArray("photoUrls");
        for (int i = 0; i < photoUrlsArray.length(); i++) {
            validatableResponse
                    .body("photoUrls", hasItems(petBody.getJSONArray("photoUrls").get(i)));
        }
        return this;
    }
    @Step("validate pet response tag id")
    public AddPetSteps validatePetTagID() {
        // tu ramdenimes davamatebt ase kvelas gatestavs
        JSONArray tagsArray = getPetTagArray();
        for (int i = 0; i < tagsArray.length(); i++) {
            validatableResponse
                    .body("tags["+i+"].id", equalTo(petBody.getJSONArray("tags").getJSONObject(i).getInt("id")));
        }
        return this;
    }
    @Step("validate pet response tag name")
    public AddPetSteps validatePetTagName() {
        // tu ramdenimes davamatebt ase kvelas gatestavs
        JSONArray tagsArray = getPetTagArray();
        for (int i = 0; i < tagsArray.length(); i++) {
            validatableResponse
                    .body("tags["+i+"].name", equalTo(petBody.getJSONArray("tags").getJSONObject(i).getString("name")));
        }
        return this;
    }
    @Step("validate pet response status")
    public void validatePetTagStatus() {
        validatableResponse
                .body("status", equalTo(petBody.getString("status")));
    }
}
