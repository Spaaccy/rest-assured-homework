package tbc.tbcacademy.ge.data.steps.restfulbookersteps;

import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerMainRequest;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequestObject;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerDates;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKING_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForBooker;

public class CreateBookingSteps extends CommonSteps<CreateBookingSteps> {
    private BookerDates bookerDates;
    private RequestSpecification requestSpecification;
    public CreateBookingSteps() {
        requestSpecification = getRequestSpecForBooker(bookerAuthResponse.getToken());
    }
    public CreateBookingSteps createBookerDates() {
        bookerDates = new BookerDates();
        bookerDates.setCheckin("2018-01-01");
        bookerDates.setCheckout("2019-01-01");
        return this;
    }

    public CreateBookingSteps createBookerBody() {
        bookingRequest = new BookerMainRequest();
        bookerRequestObject = new BookerRequestObject();
        bookerRequestObject.setFirstname(faker.name().firstName());
        bookerRequestObject.setLastname(faker.name().lastName());
        bookerRequestObject.setTotalprice(faker.number().numberBetween(50, 100));
        bookerRequestObject.setDepositpaid(faker.bool().bool());
        bookerRequestObject.setBookingdates(bookerDates);
        bookerRequestObject.setAdditionalneeds(faker.cat().breed());
        bookingRequest.setBooking(bookerRequestObject);
        return this;
    }

    public CreateBookingSteps addBooker() {
        response =
                given(requestSpecification)
                        .body(bookingRequest.getBooking())
                        .post(BOOKING_ENDPOINT);
        return this;
    }
    public CreateBookingSteps extractAddedBooker() {
        bookingResponse = validatableResponse
                .extract()
                .body()
                .as(BookerMainRequest.class);
        return this;
    }

    public void validateAddBooker() {
        assertThat(bookingRequest.getBooking(), equalTo(bookingResponse.getBooking()));
    }
}
