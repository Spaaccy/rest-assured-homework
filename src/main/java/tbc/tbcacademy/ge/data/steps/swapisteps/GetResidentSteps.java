package tbc.tbcacademy.ge.data.steps.swapisteps;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.models.responses.swapi.SwapiResident;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.SwapiData.*;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForSwapi;

public class GetResidentSteps extends CommonSteps<GetResidentSteps> {
    private RequestSpecification requestSpecification;
    private SwapiResident swapiResidentResponse;
    private SwapiResident mySwapiResidentResponse;

    public GetResidentSteps() {
        requestSpecification = getBaseRequestSpecForSwapi();
    }
    public GetResidentSteps getResident(String urlEndpoint) {
        response = given(requestSpecification)
                .when()
                .get(urlEndpoint);
        return this;
    }
    @Step("extract resident response as class")
    public GetResidentSteps extractResidentResponseAsClass() {
        swapiResidentResponse = validatableResponse
                .extract()
                .body()
                .as(SwapiResident.class);

        return this;
    }
    @Step("build resident response class")
    public GetResidentSteps buildResidentResponse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        mySwapiResidentResponse = new SwapiResident()
                .name(BOBA_FETT_NAME)
                .height(BOBA_FETT_HEIGHT)
                .mass(BOBA_FETT_MASS)
                .hairColor(BOBA_FETT_HAIR_COLOR)
                .skinColor(BOBA_FETT_SKIN_COLOR)
                .eyeColor(BOBA_FETT_EYE_COLOR)
                .birthYear(BOBA_FETT_BIRTH_YEAR)
                .gender(BOBA_FETT_GENDER)
                .homeworld(BOBA_FETT_HOMEWORLD)
                .films(BOBA_FETT_FILMS)
                .species(List.of())
                .vehicles(List.of())
                .starships(BOBA_FETT_STARSHIPS)
                .created(LocalDateTime.parse(BOBA_FETT_CREATED, formatter))
                .edited(LocalDateTime.parse(BOBA_FETT_EDITED, formatter))
                .url(BOBA_FETT_URL);
        return this;
    }
    @Step("validate swapi resident response")
    public void validateSwapiResidentResponse() {
        assertThat(mySwapiResidentResponse, equalTo(swapiResidentResponse));
    }
}
