package tbc.tbcacademy.ge.restfulbookertest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequestObject;
import tbc.tbcacademy.ge.data.steps.restfulbookersteps.*;

public class RestfulBookerAPI {
    PartialUpdateBookingSteps partialUpdateBookingSteps;
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
        partialUpdateBookingSteps = new PartialUpdateBookingSteps();
        createBookingSteps = new CreateBookingSteps();
        getBookingSteps = new GetBookingSteps();
        deleteBookingSteps = new DeleteBookingSteps();
    }
    @Test(priority = 1)
    public void addBookingTest(){
        createBookingSteps
                .createBookerDates()
                .createBookerBody()
                .addBooker()
                .getValidatableResponse()
                .checkStatusCode()
                .extractAddedBooker()
                .validateAddBooker();
    }
    @Test(priority = 2, dependsOnMethods = "addBookingTest")
    public void updateBookingTest() {
        partialUpdateBookingSteps
                .createBookerFullNameBody()
                .partialUpdateBookingByID()
                .getValidatableResponse()
                .checkStatusCode()
                .extractAddedBooker();

        BookerRequestObject bookerResponseObject = getBookingSteps
                .getBookingByID(createBookingSteps.getBookerResponseID());
        partialUpdateBookingSteps.validateUpdatedBookers(bookerResponseObject);
    }
    @Test(priority = 3, dependsOnMethods = {"addBookingTest", "updateBookingTest"})
    public void deleteBookingTest() {
        deleteBookingSteps
                .deleteBookingByID(deleteBookingSteps.getBookerResponseID())
                .getValidatableResponse()
                .checkStatusCodeDelete();
    }
}
