package tbc.tbcacademy.ge.data.steps.petstoreV3steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import tbc.tbcacademy.ge.data.models.requests.petv3.Category;
import tbc.tbcacademy.ge.data.models.requests.petv3.Pet;
import tbc.tbcacademy.ge.data.models.requests.petv3.Tag;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_ENDPOINT_V3;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStoreV3;

public class AddV3PetSteps extends CommonSteps<AddV3PetSteps> {
    Pet pet;
    Pet petResponse;
    String xmlBody;
    public AddV3PetSteps() {
        requestSpecification = getBaseRequestSpecForPetStoreV3();
    }
    public AddV3PetSteps createPetBody() {
        pet = new Pet();
        pet.setId(faker.number().randomDigitNotZero());
        pet.setName(faker.name().firstName());
        pet.setPhotoUrls(new String[] { faker.internet().url() });
        pet.setStatus("available");

        Category category = new Category();
        category.setId(faker.number().randomDigitNotZero());
        category.setName(faker.name().firstName());
        pet.setCategory(category);

        Tag tag = new Tag();
        tag.setId(faker.number().randomDigitNotZero());
        tag.setName(faker.name().firstName());
        pet.setTags(new Tag[]{tag});
        return this;
    }
    public AddV3PetSteps serializeToXml() throws JsonProcessingException {
        xmlBody = xmlMapper.writeValueAsString(pet);
        return this;
    }
    public AddV3PetSteps addPet() {
        response = given(requestSpecification)
                .contentType("application/xml")
                .accept("application/xml")
                .body(xmlBody)
                .when()
                .post(PET_ENDPOINT_V3);
        return this;
    }
    public AddV3PetSteps validateResponseContentType() {
        validatableResponse
                .contentType("application/xml");
        return this;
    }
    public AddV3PetSteps deserializeResponseAsClass() throws JsonProcessingException {
        petResponse = xmlMapper.readValue(response.asString(), Pet.class);
        return this;
    }
    public void validateResponse() {
        assertThat(petResponse, hasToString(pet.toString()));
    }
}

