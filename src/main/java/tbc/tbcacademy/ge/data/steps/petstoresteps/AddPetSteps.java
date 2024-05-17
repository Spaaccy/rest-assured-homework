package tbc.tbcacademy.ge.data.steps.petstoresteps;
import io.qameta.allure.Step;
import tbc.tbcacademy.ge.data.models.shared.petstore.CategoryShared;
import tbc.tbcacademy.ge.data.models.shared.petstore.PetShared;
import tbc.tbcacademy.ge.data.models.shared.petstore.TagShared;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_DEFAULT_STATUS;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;

public class AddPetSteps extends CommonSteps<AddPetSteps> {
    public AddPetSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }
    @Step("create pet body json")
    public AddPetSteps createPetBody() {
        petSharedClass = new PetShared()
                .id(faker.number().randomDigitNotZero())
                .category(
                        new CategoryShared()
                        .id(faker.number().randomDigitNotZero())
                        .name(faker.name().firstName()))
                .name(faker.name().firstName())
                .photoUrls(new String[] { faker.internet().url(), faker.internet().url() })
                .tags(new TagShared[] {new TagShared()
                        .id(faker.number().randomDigitNotZero())
                        .name(faker.name().firstName())}
                )
                .status(PET_DEFAULT_STATUS);
        return this;
    }

    @Step("add pet and get the response")
    public AddPetSteps addPet(){
        response = given(requestSpecification)
                .body(petSharedClass)
                .when()
                .put(PET_ENDPOINT);
        return this;
    }
    public void validateAddedPetResponse() {
        assertThat(petResponseClass.id(), equalTo(petSharedClass.id()));
        assertThat(petResponseClass.name(), equalTo(petSharedClass.name()));
        assertThat(petResponseClass.photoUrls(), arrayContainingInAnyOrder(petSharedClass.photoUrls()));
        assertThat(petResponseClass.tags()[0].id(), equalTo(petSharedClass.tags()[0].id()));
        assertThat(petResponseClass.tags()[0].name(), equalTo(petSharedClass.tags()[0].name()));
        assertThat(petResponseClass.status(), equalTo(petSharedClass.status()));
    }
}
