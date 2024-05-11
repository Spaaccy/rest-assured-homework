package tbc.tbcacademy.ge.testpetstore;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.petstoresteps.*;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.*;

public class PetStoreAPI {
    AddPetSteps addPetSteps;
    FindPetSteps findPetSteps;
    UpdatePetSteps updatePetSteps;
    GetPetSteps getPetSteps;
    UploadImageSteps uploadImageSteps;

    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        addPetSteps = new AddPetSteps();
        findPetSteps = new FindPetSteps();
        updatePetSteps = new UpdatePetSteps();
        getPetSteps = new GetPetSteps();
        uploadImageSteps = new UploadImageSteps();
    }
    @Test(priority = 1)
    public void addPetTest() {
        addPetSteps
                .createPetCategory()
                .createPetTag()
                .createPetBody()
                .addPet()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPetStoreResponseAsClass()
                .validateAddedPetResponse();
    }
    @Test(priority = 2, dependsOnMethods = "addPetTest")
    public void findPetTest() throws JsonProcessingException {
        findPetSteps
                .getAddedPet()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPetResponseAsClass()
                .validateResponseContainsID()
                .extractMyPet()
                .validatePetEqualsPOJO();
    }
    @Test(priority = 3, dependsOnMethods = {"addPetTest", "findPetTest"})
    public void updatePetTest() {
        updatePetSteps
                .updatePetPOJOName(UPDATED_PET_NAME)
                .updatePetPOJOStatus(UPDATED_PET_STATUS)
                .updatePet()
                .getValidatableResponse()
                .checkStatusCode();
    }
    @Test(priority = 4, dependsOnMethods = {"addPetTest", "findPetTest", "updatePetTest"})
    public void getPetTest() {
        getPetSteps
                .getPet()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPetStoreResponseAsClass()
                .validatePetName()
                .validatePetStatus();
    }
    @Test(priority = 5, dependsOnMethods = "addPetTest")
    public void uploadImageTest() {
        uploadImageSteps
                .getImage()
                .uploadPetImage()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPetStoreImageUploadResponseAsClass()
                .validateImageMetadata()
                .validateImageFilename()
                .validateImageFilesize();
    }
}
