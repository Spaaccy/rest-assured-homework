package tbc.tbcacademy.ge.data.steps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerMainRequest;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequestObject;
import tbc.tbcacademy.ge.data.models.shared.petstore.PetShared;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerUserRequest;
import tbc.tbcacademy.ge.data.models.shared.ergast.DriverShared;
import tbc.tbcacademy.ge.data.models.responses.petstore.ImageUploadResponse;
import tbc.tbcacademy.ge.data.models.responses.bookstore.Book;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerAuthResponse;

import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;
import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE_CREATED;
import static tbc.tbcacademy.ge.data.specbuilder.ResponseSpecs.createResponseCheckerSpec;

public class CommonSteps<T extends CommonSteps> {
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static Faker faker = new Faker();
    protected static PetShared petSharedClass;
    protected static PetShared petResponseClass;
    protected static Book bookStoreResponseClass;
    protected static ImageUploadResponse imageUploadResponseClass;
    protected static DriverShared driverShared;
    protected static BookerAuthResponse bookerAuthResponse;
    protected static BookerUserRequest bookerUserRequest;
    protected static BookerMainRequest bookingRequest;
    protected static BookerMainRequest bookingResponse;
    protected static BookerRequestObject bookerRequestObject;
    protected static BookerRequestObject bookerResponseObjectFromUpdate;
    protected Response response;
    protected ValidatableResponse validatableResponse;
    protected RequestSpecification requestSpecification;


    public T getValidatableResponse() {
        validatableResponse = response
                .then()
                .assertThat();
        return (T) this;
    }
    public T checkStatusCode() {
        validatableResponse
                .spec(createResponseCheckerSpec(SUCCESS_CODE));
        return (T) this;
    }
    public void checkStatusCodeDelete() {
        validatableResponse
                .spec(createResponseCheckerSpec(SUCCESS_CODE_CREATED));
    }
    public T extractBookStoreResponseAsClass() {
        bookStoreResponseClass = validatableResponse
                .extract()
                .as(Book.class);
        return (T) this;
    }
    public T extractPetStoreResponseAsClass() {
        petResponseClass = validatableResponse
                .extract()
                .as(PetShared.class);
        return (T) this;
    }
    public T extractPetStoreImageUploadResponseAsClass() {
        imageUploadResponseClass = validatableResponse
                .extract()
                .as(ImageUploadResponse.class);
        return (T) this;
    }
    public T extractDriverResponseAsClassUsingIndex(int index) {
        driverShared = validatableResponse
                .extract()
                .jsonPath()
                .getObject("MRData.DriverTable.Drivers["+index+"]", DriverShared.class);
        return (T) this;
    }
    public int getBookerResponseID() {
        return bookingResponse.getBookingid();
    }
}
