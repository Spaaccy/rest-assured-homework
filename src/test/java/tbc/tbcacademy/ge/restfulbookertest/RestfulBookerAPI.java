package tbc.tbcacademy.ge.restfulbookertest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.restfulbookersteps.UpdateBookingSteps;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKER_ID;
import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE_CREATED;

public class RestfulBookerAPI {
    UpdateBookingSteps updateBookingSteps;

    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        updateBookingSteps = new UpdateBookingSteps();
    }

    @Test(priority = 1)
    public void updateBookingTest(){
        updateBookingSteps
                .createBookingDates()
                .createBookingBody()
                .updateBookingByID(BOOKER_ID)
                .getValidatableResponse()
                .extractAndValidateStatusCode()
                .logStatusIfStatusCodeIs(SUCCESS_CODE_CREATED);
    }
}
