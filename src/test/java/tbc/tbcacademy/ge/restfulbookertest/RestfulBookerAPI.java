package tbc.tbcacademy.ge.restfulbookertest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.dataproviders.DataProviders;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerResponse;
import tbc.tbcacademy.ge.data.steps.restfulbookersteps.*;

public class RestfulBookerAPI {
    UpdateBookingSteps updateBookingSteps;
    GetBookerTokenSteps getBookerTokenSteps;
    CreateBookingSteps createBookingSteps;
    GetBookingSteps getBookingSteps;
    DeleteBookingSteps deleteBookingSteps;
    @BeforeTest(alwaysRun = true)
    public void initializeToken() {
        getBookerTokenSteps = new GetBookerTokenSteps();
        getBookerTokenSteps
                .setUserInfo()
                .getRequestToken()
                .getValidatableResponse()
                .checkStatusCode()
                .extractTokenResponseAsClass();
    }

    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        updateBookingSteps = new UpdateBookingSteps();
        createBookingSteps = new CreateBookingSteps();
        getBookingSteps = new GetBookingSteps();
        deleteBookingSteps = new DeleteBookingSteps();
    }
    @Test(priority = 1)
    public void addBookingTest() {
        createBookingSteps
                .createBookerBody()
                .addBooker()
                .getValidatableResponse()
                .checkStatusCode()
                .extractAddedBooker();

        BookerResponse bookerResponseObject = getBookingSteps
                .getBookingByID(createBookingSteps.getBookerResponseID());
        createBookingSteps.validateUpdatedBookers(bookerResponseObject);
    }
    @Test(priority = 2, dependsOnMethods = "addBookingTest", dataProvider = "bookingData", dataProviderClass = DataProviders.class)
    public void updateBookingTest(String firstname, String lastname, int totalprice, boolean depositpaid, int salesprice, String checkin, String checkout, String additionalneeds, String passportNo) throws JsonProcessingException {
        updateBookingSteps
                .createBookerBody(firstname, lastname, totalprice, depositpaid, salesprice, checkin, checkout, additionalneeds, passportNo)
                .updateBooking()
                .getValidatableResponse()
                .checkStatusCode()
                .extractAddedBooker()
                .validateUpdatedBookers();
    }
    @Test(priority = 3, dependsOnMethods = {"addBookingTest", "updateBookingTest"})
    public void deleteBookingTest() {
        deleteBookingSteps
                .deleteBookingByID(deleteBookingSteps.getBookerResponseID())
                .getValidatableResponse()
                .checkStatusCodeDelete();

        getBookingSteps
                .getBookingByNotFound(updateBookingSteps.getBookerResponseID())
                .getValidatableResponse()
                .checkStatusCodeNotFound();
    }
}
