package tbc.tbcacademy.ge.data.steps.crcindsteps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.tempuri.FindPerson;
import org.tempuri.FindPersonResponse;
import org.tempuri.ObjectFactory;
import org.tempuri.Person;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import tbc.tbcacademy.ge.data.helpers.SerializeDeserializeXml;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.CrcindData.*;
import static tbc.tbcacademy.ge.data.helpers.HelperFunctions.formatIntoGregorianDate;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForCrcind;

public class GetCrcindPersonSteps extends CommonSteps<GetCrcindPersonSteps> {
    private final RequestSpecification requestSpecification;

    public GetCrcindPersonSteps() {
        requestSpecification = getRequestSpecForCrcind();
    }

    @Step("Create FindPerson Request")
    public FindPerson createFindPersonRequest() {
        ObjectFactory objectFactory = new ObjectFactory();
        FindPerson findPerson = objectFactory.createFindPerson();
        findPerson
                .withId(USER_ID);
        return findPerson;
    }
    @Step("Send FindPerson Request and Get Response")
    public Response getPersonResponse(String xml) {
        return
                given(requestSpecification)
                        .header("SOAPAction", "http://tempuri.org/SOAP.Demo.FindPerson")
                        .body(xml)
                        .post(URI_ENDPOINT);
    }
    @Step("Deserialize Response to FindPersonResponse Object")
    public FindPersonResponse deserializeToFindPersonResponse(Response response) {
        return SerializeDeserializeXml.unmarshallResponse(response.body().asString(), FindPersonResponse.class);
    }
    @Step("Validate FindPersonResult Is Not Null")
    public GetCrcindPersonSteps validateFindPersonResultNotNull(Person person) {
        assertThat(person, not(nullValue()));
        return this;
    }
    @Step("Validate First Name Matches Expected Value")
    public GetCrcindPersonSteps validateFindPersonFirstName(Person person) {
        assertThat(person.getName().split(",")[1], equalTo(FIRST_NAME));
        return this;
    }
    @Step("Validate SSN value")
    public GetCrcindPersonSteps validateFindPersonSSN(Person person) {
        assertThat(person.getSSN(), equalTo(SSN));
        return this;
    }
    @Step("Validate SSN Length Matches Expected Length")
    public GetCrcindPersonSteps validateFindPersonSSNCharacters(Person person) {
        assertThat(person.getSSN().trim().length(), equalTo(SSN.trim().length()));
        return this;
    }
    @Step("Validate SSN Pattern for Person")
    public GetCrcindPersonSteps validateFindPersonSSNPattern(Person person) {
        assertThat(person.getSSN(), matchesPattern(SSN_REGEX));
        return this;
    }
    @Step("Validate Date of Birth")
    public GetCrcindPersonSteps validateFindPersonDateOfBirth(Person person) {
        assertThat(person.getDOB(), equalTo(formatIntoGregorianDate(BOD)));
        return this;
    }
    @Step("Validate Favorite Colors Contain Orange and Red")
    public GetCrcindPersonSteps validateFindPersonFavColors(Person person) {
        assertThat(person.getFavoriteColors().getFavoriteColorsItem(), containsInAnyOrder(FAV_COLORS));
        return this;
    }
    @Step("Validate Last Name Matches Expected Value")
    public GetCrcindPersonSteps validateFindPersonLastName(Person person) {
        assertThat(person.getName().split(",")[0], equalTo(LAST_NAME));
        return this;
    }
    @Step("Validate Favorite Colors Contain Red at Index 2 Using Stream API")
    public GetCrcindPersonSteps validateIndexOfFavColorRedIsTwo(Person person) {
        List<String> favoriteColors = person.getFavoriteColors().getFavoriteColorsItem();
        boolean hasRedAtIndex2 = IntStream.range(0, favoriteColors.size())
                .anyMatch(index -> index == SECOND_COLOR_INDEX && favoriteColors.get(index).equals(SECOND_COLOR));

        // ასე დაფეილდება რადგან ინდექსი არის 1;
        assertThat(hasRedAtIndex2, equalTo(true));
//        assertThat(hasRedAtIndex2, equalTo(false));
        return this;
    }
    @Step("Validate Office and Home Zip Codes Are Different Using Stream API")
    public void validateFindPersonZipCodesAreDistinct(Person person) {
        Stream<String> zipCodes = Stream.of(
                person.getOffice().getZip(),
                person.getHome().getZip()
        );
//        boolean zipCodesAreUnique = zipCodes.distinct().count() == 2;
        boolean zipCodesAreUnique = zipCodes.distinct().count() > 1;
        assertThat(zipCodesAreUnique, equalTo(true));
    }
}
