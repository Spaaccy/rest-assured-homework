package tbc.tbcacademy.ge.data.steps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequest;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerMainResponse;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerResponse;
import tbc.tbcacademy.ge.data.models.responses.swapi.SwapiPlanet;
import tbc.tbcacademy.ge.data.models.shared.petstore.PetShared;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerUserRequest;
import tbc.tbcacademy.ge.data.models.shared.ergast.DriverShared;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerAuthResponse;

import static tbc.tbcacademy.ge.data.constants.CommonData.*;
import static tbc.tbcacademy.ge.data.specbuilder.ResponseSpecs.createResponseCheckerSpec;

public class CommonSteps<T extends CommonSteps> {
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static XmlMapper xmlMapper = new XmlMapper();
    protected static Faker faker = new Faker();
    protected static PetShared petSharedClass;
    protected static PetShared petResponseClass;
    protected static DriverShared driverShared;
    protected static BookerAuthResponse bookerAuthResponse;
    protected static BookerUserRequest bookerUserRequest;
    protected static BookerRequest bookingRequest;
    protected static BookerMainResponse bookingResponse;
    protected static BookerResponse bookerResponse;
    protected static SwapiPlanet filteredTopRotationPlanet;
    protected Response response;
    protected ValidatableResponse validatableResponse;
    protected RequestSpecification requestSpecification;
    protected String topRotationPlanetFirstResidentURL;


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
    public void checkStatusCodeNotFound() {
        validatableResponse
                .spec(createResponseCheckerSpec(ERROR_CODE));
    }
    public T extractPetStoreResponseAsClass() {
        petResponseClass = validatableResponse
                .extract()
                .as(PetShared.class);
        return (T) this;
    }
    public T extractDriverResponseAsClassUsingIndex(int index) {
        driverShared = validatableResponse
                .extract()
                .jsonPath()
                .getObject("MRData.DriverTable.Drivers["+index+"]", DriverShared.class);
        return (T) this;
    }
    public String extractFirstResidentURLFromTopRotationPlanet() {
        return topRotationPlanetFirstResidentURL = filteredTopRotationPlanet.residents().get(0);
    }
    public int getBookerResponseID() {
        return bookingResponse.bookingid();
    }
}
