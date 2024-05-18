package tbc.tbcacademy.ge.crcindtest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.tempuri.FindPerson;
import org.tempuri.FindPersonResponse;
import org.tempuri.Person;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.crcindsteps.GetCrcindPersonSteps;

import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;


@Epic("CRCIND SOAP API Integration Tests")
public class CrcindAPI {
    GetCrcindPersonSteps getCrcindPersonSteps;

    @BeforeClass
    public void init() {
        getCrcindPersonSteps = new GetCrcindPersonSteps();
    }
    @Test(priority = 1, description = "Verify successful retrieval of person details via SOAP API")
    @Feature("Person Information Retrieval")
    @Story("Find Person Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test case verifies the 'FindPerson' operation of the SOAP API, checking that it returns the correct person details based on the provided ID.")
    public void findPersonTEST() {
        FindPerson findPerson = getCrcindPersonSteps
                .createFindPersonRequest();
        String getPersonXML = getCrcindPersonSteps
                .serializeToXML(findPerson);
        Response response = getCrcindPersonSteps
                .getPersonResponse(getPersonXML);
        FindPersonResponse findPersonResponse = getCrcindPersonSteps
                .checkStatusCode(response, SUCCESS_CODE)
                .deserializeToFindPersonResponse(response);
        Person person = findPersonResponse
                .getFindPersonResult();

        getCrcindPersonSteps
                .validateFindPersonResultNotNull(person)
                .validateFindPersonFirstName(person)
                .validateFindPersonSSN(person)
                .validateFindPersonSSNCharacters(person)
                .validateFindPersonSSNPattern(person)
                .validateFindPersonDateOfBirth(person)
                .validateFindPersonFavColors(person)
                .validateFindPersonLastName(person)
                .validateIndexOfFavColorRedIsTwo(person)
                .validateFindPersonZipCodesAreDistinct(person);
    }
}
