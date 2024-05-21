package tbc.tbcacademy.ge.oorsprongtest;
import io.qameta.allure.*;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.oorsprongsteps.OorSprongGetContinentsSteps;

import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;


@Epic("OorSprongAPI - Core Functionality: Get Continents")
public class OorSprongAPI {
    OorSprongGetContinentsSteps oorSprongGetContinentsSteps;
    @BeforeClass
    public void setUp() {
        oorSprongGetContinentsSteps = new OorSprongGetContinentsSteps();
    }

    @Test(priority = 1, description = "retrieve and validate continent data")
    @Feature("Continent Data Validation")
    @Story("Comprehensive XML Validation for Continents API")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test retrieves continent data from the API, parses the XML response, " +
            "and performs a series of validations on the SName, SCode, " +
            "and overall data structure to ensure accuracy and consistency.")
    public void GetContinentsTest() {
        Response response = oorSprongGetContinentsSteps.getCountryResponse();
        XmlPath xmlPath = oorSprongGetContinentsSteps
                .checkStatusCode(response, SUCCESS_CODE)
                .getXmlPathFromResponse(response);

        oorSprongGetContinentsSteps
                .setRootXmlPath(xmlPath)
                .extractSNameForValidation(xmlPath)
                .extractSodeForValidation(xmlPath)
                .validatePresenceOfSNameValues()
                .validatePresenceOfSCodeValues()
                .validateSNameCount(xmlPath)
                .validateSnameValues()
                .validateScodeAN(xmlPath)
                .validateLastTContinentSNameValue(xmlPath)
                .validateEachSnameUnique()
                .validatingCorrectnessOfSNameAndSCode()
                .checkScodeFormat()
                .validateTContinentSequence()
                .validateAllContinents()
                .validateNoDigitsSname()
                .validateScodeStartWithO(xmlPath)
                .validateScodeStartWithAandEndsWithCa(xmlPath);

    }
}
