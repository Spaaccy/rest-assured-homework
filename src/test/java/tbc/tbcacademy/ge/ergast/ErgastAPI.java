package tbc.tbcacademy.ge.ergast;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.ergaststeps.ErgastDriverSteps;

public class ErgastAPI {
    ErgastDriverSteps ergastDriverSteps;
    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        ergastDriverSteps = new ErgastDriverSteps();
    }
    @Test(priority = 1)
    public void ergastSteps() {
        ergastDriverSteps
                .createDriver()
                .getDrivers()
                .getValidatableResponse()
                .checkStatusCode()
                .extractDriverResponseAsClassUsingIndex(0)
                .validateDriver();
    }
}
