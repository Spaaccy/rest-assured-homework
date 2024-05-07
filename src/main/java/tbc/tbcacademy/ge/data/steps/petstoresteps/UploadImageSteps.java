package tbc.tbcacademy.ge.data.steps.petstoresteps;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_IMAGE_FORMAT;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_IMAGE_METADATA;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStore;

public class UploadImageSteps extends CommonSteps<UploadImageSteps> {
    private File imageFile;
    public UploadImageSteps() {
        requestSpecification = getBaseRequestSpecForPetStore();
    }
    @Step("get image name in pojo")
    public UploadImageSteps getImage() {
        imageFile = new File("src/main/resources/randomImage.png");
        return this;
    }
    @Step("upload image for pet")
    public UploadImageSteps uploadPetImage(int id) {
        response = given(requestSpecification)
                .contentType(ContentType.MULTIPART)
                .multiPart("additionalMetadata", PET_IMAGE_METADATA)
                .multiPart("file", imageFile, PET_IMAGE_FORMAT)
                .when()
                .post("/pet/{petId}/uploadImage", id);
        return this;
    }
    @Step("validate image metadata")
    public UploadImageSteps validateImageMetadata() {
        validatableResponse
                .body("message", containsString(PET_IMAGE_METADATA));
        return this;
    }
    @Step("validate image filename")
    public UploadImageSteps validateImageFilename() {
        validatableResponse
                .body("message", containsString(imageFile.getName()));
        return this;
    }
    @Step("validate image filesize")
    public void validateImageFilesize() {
        String ImageMessage = response
                .jsonPath()
                .getString("message");
        assertThat(ImageMessage.split(" "), hasItemInArray(imageFile.length()+""));
    }
}
