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
    @Step("get image file")
    public UploadImageSteps getImage() {
        imageFile = new File("src/main/resources/randomImage.png");
        return this;
    }
    @Step("upload image for pet")
    public UploadImageSteps uploadPetImage() {
        response = given(requestSpecification)
                .contentType(ContentType.MULTIPART)
                .multiPart("additionalMetadata", PET_IMAGE_METADATA)
                .multiPart("file", imageFile, PET_IMAGE_FORMAT)
                .when()
                .post("/pet/{petId}/uploadImage", petSharedClass.getId());
        return this;
    }
    @Step("validate image metadata")
    public UploadImageSteps validateImageMetadata() {
        assertThat(imageUploadResponseClass.getMessage(), containsString(PET_IMAGE_METADATA));
        return this;
    }
    @Step("validate image filename")
    public UploadImageSteps validateImageFilename() {
        assertThat(imageUploadResponseClass.getMessage(), containsString(imageFile.getName()));
        return this;
    }
    @Step("validate image filesize")
    public void validateImageFilesize() {
        assertThat(imageUploadResponseClass.getMessage().split(" "), hasItemInArray(imageFile.length()+""));
    }
}
