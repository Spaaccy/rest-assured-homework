package tbc.tbcacademy.ge.data.steps.ergaststeps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.models.shared.ergast.DriverShared;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.ErgastData.*;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForErgast;

public class ErgastDriverSteps extends CommonSteps<ErgastDriverSteps> {
    RequestSpecification requestSpecification;
    DriverShared javaDriver;
    public ErgastDriverSteps() {
        requestSpecification = getBaseRequestSpecForErgast();
    }
    @Step("get updated pet using id")
    public ErgastDriverSteps getDrivers() {
        response = given(requestSpecification)
                .when()
                .get(ERGAST_ENDPOINT);
        return this;
    }
    @Step
    public ErgastDriverSteps createDriver() {
        javaDriver = new DriverShared();
        javaDriver.setDriverId(DRIVER_ID);
        javaDriver.setPermanentNumber(PERM_NUMBER);
        javaDriver.setCode(CODE);
        javaDriver.setUrl(URL);
        javaDriver.setGivenName(NAME);
        javaDriver.setFamilyName(FAMILY_NAME);
        javaDriver.setDateOfBirth(DATE);
        javaDriver.setNationality(NATIONALITY);
        return this;
    }

    @Step("validate driver")
    public void validateDriver() {
        assertThat(javaDriver, equalTo(driverShared));
    }
}
