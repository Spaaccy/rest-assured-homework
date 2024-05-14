package tbc.tbcacademy.ge.data.steps.petstore;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Setter;
import pet.store.v3.api.PetApi.AddPetOper;
import pet.store.v3.invoker.ApiClient;
import pet.store.v3.model.Category;
import pet.store.v3.model.Pet;
import pet.store.v3.model.Tag;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import java.util.List;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static pet.store.v3.invoker.ResponseSpecBuilders.shouldBeCode;
import static pet.store.v3.invoker.ResponseSpecBuilders.validatedWith;

public class PetStoreAddPetSteps extends CommonSteps {
    private Pet pet;
    private AddPetOper addPetOper;
    @Setter
    private ApiClient apiClient;

    @Step("Create pet body using Pet model")
    public PetStoreAddPetSteps createPet() {
        pet = new Pet()
                .id(faker.random().nextLong())
                .name(faker.cat().name())
                .category(
                        new Category()
                        .id(faker.random().nextLong())
                        .name(faker.dog().name())
               ).photoUrls(List.of(faker.name().name()))
                .tags(
                        List.of(
                                new Tag()
                                        .id(faker.random().nextLong())
                                        .name(faker.name().firstName())
                        )
                )
                .status(Pet.StatusEnum.AVAILABLE);
        return this;
    }

    @Step("Go to pets and add pet using the following Pet POJO model")
    public PetStoreAddPetSteps addPet() {
        addPetOper = apiClient
                .pet()
                .addPet()
                .body(pet);
        return this;
    }
    @Step("Get response and validate pet name")
    public void validateAddPetResponse() {
        Response response = addPetOper.execute(validatedWith(shouldBeCode(SC_OK)).andThen(response1 -> response1));
        response
                .then()
                .assertThat()
                .body("name", equalTo(pet.name()));
    }
}
