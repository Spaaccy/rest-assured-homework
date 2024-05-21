package tbc.tbcacademy.ge.data.steps.oorsprongsteps;
import io.qameta.allure.Step;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.OorSprongData.*;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForOorSprong;

public class OorSprongGetContinentsSteps extends CommonSteps<OorSprongGetContinentsSteps> {
    private List<String> continentNames;
    private List<String> continentCodes;
    private final RequestSpecification requestSpecification;

    public OorSprongGetContinentsSteps() {
        requestSpecification = getRequestSpecForOorSprong();
    }
    @Step("Get Country Response")
    public Response getCountryResponse() {
        return given(requestSpecification)
                .when()
                .get("/ListOfContinentsByName");
    }
    @Step("Set Root XML Path")
    public OorSprongGetContinentsSteps setRootXmlPath(XmlPath xmlPath) {
        xmlPath.setRootPath("ArrayOftContinent.tContinent");
        return this;
    }
    @Step("Extract Scode For Validation")
    public OorSprongGetContinentsSteps extractSodeForValidation(XmlPath xmlPath) {
        continentNames = xmlPath.getList("sName");
        return this;
    }
    @Step("Extract SName For Validation")
    public OorSprongGetContinentsSteps extractSNameForValidation(XmlPath xmlPath) {
        continentCodes = xmlPath.getList("sCode");
        return this;
    }
    @Step("Validate Presence Of SName Values")
    public OorSprongGetContinentsSteps validatePresenceOfSNameValues(){
        assertThat(continentNames, everyItem(not(emptyOrNullString())));
        return this;
    }
    @Step("Validate Presence Of SCode Values")
    public OorSprongGetContinentsSteps validatePresenceOfSCodeValues(){
        assertThat(continentCodes, everyItem(not(emptyOrNullString())));
        return this;
    }
    @Step("Validate SName Count")
    public OorSprongGetContinentsSteps validateSNameCount(XmlPath xmlPath) {
        assertThat(xmlPath.get("size()"), equalTo(CONTINENTS_AMOUNT));
        return this;
    }
    @Step("Validate Sname Values")
    public OorSprongGetContinentsSteps validateSnameValues() {
        assertThat(continentNames, containsInAnyOrder(CONTINENTS_NAMES.toArray()));
        return this;
    }
    @Step("Validate Scode AN")
    public OorSprongGetContinentsSteps validateScodeAN(XmlPath xmlPath) {
        assertThat(xmlPath.get("find { it.sCode == 'AN' }.sName"), equalTo(ANTARCTICA_NAME));
        return this;
    }
    @Step("Validate Last TContinent SName Value")
    public OorSprongGetContinentsSteps validateLastTContinentSNameValue(XmlPath xmlPath) {
        assertThat(xmlPath.get("last().sName"), equalTo(AMERICAS_NAME));
        return this;
    }
    @Step("Validate Each Sname Unique")
    public OorSprongGetContinentsSteps validateEachSnameUnique() {
        Set<String> uniqueContinentNames = new HashSet<>(continentNames);
        assertThat(continentNames.size(), equalTo(uniqueContinentNames.size()));
        return this;
    }
    @Step("Validate Correctness Of SCode and SName")
    public OorSprongGetContinentsSteps validatingCorrectnessOfSNameAndSCode(){
        for(int i = 0; i < continentNames.size(); i++){
            int length = continentNames.get(i).toLowerCase().split(" ").length;
            assertThat(continentNames.get(i).toLowerCase().split(" ")[length-1], startsWith(continentCodes.get(i).toLowerCase()));
        }
        return this;
    }
    @Step("Check Scode Format")
    public OorSprongGetContinentsSteps checkScodeFormat() {
        assertThat(continentCodes, everyItem(matchesRegex(TWO_CAPITAL_LETTERS_REGEX)));
        return this;
    }
    @Step("Validate TContinent Sequence")
    public OorSprongGetContinentsSteps validateTContinentSequence() {
        List<String> sortedContinentNames = continentNames.stream().sorted().toList();
        assertThat(continentNames, equalTo(sortedContinentNames));
        return this;
    }
    @Step("Validate All Continents")
    public OorSprongGetContinentsSteps validateAllContinents() {
        assertThat(continentNames, everyItem(not(emptyOrNullString())));
        return this;
    }
    @Step("Validate No Digits Sname")
    public OorSprongGetContinentsSteps validateNoDigitsSname() {
        assertThat(continentNames, everyItem(not(matchesPattern(NUMBERS_REGEX))));
        return this;
    }
    @Step("Validate Scode Start With O")
    public OorSprongGetContinentsSteps validateScodeStartWithO(XmlPath xmlPath) {
        assertThat(xmlPath.get("find { it.sCode.text().startsWith('O') }.sName"), equalTo(OCEANIA_NAME));
        return this;
    }
    @Step("Validate Scode Start With A and Ends With Ca and contains no digits")
    public OorSprongGetContinentsSteps validateScodeStartWithAandEndsWithCa(XmlPath xmlPath) {
        List<String> filteredNames = xmlPath
                .getList("findAll { it.sName.text().startsWith('A') && " +
                                         "it.sName.text().endsWith('ca') && " +
                                         "!it.sName.text().matches(/.*\\d+.*/) }.sName");
        assertThat(filteredNames, containsInAnyOrder(FILTERED_CONTINENTS.toArray()));
        return this;
    }
}
