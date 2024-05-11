package tbc.tbcacademy.ge.data.steps.restfulbookersteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequestDates;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerResponse;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequest;
import tbc.tbcacademy.ge.data.specbuilder.RequestSpecs;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKING_ENDPOINT;

public class UpdateBookingSteps extends CommonSteps<UpdateBookingSteps> {
    private BookerResponse bookerResponseObjectFromUpdate;
    BookerRequest bookerRequest;

    public UpdateBookingSteps(){
        requestSpecification = RequestSpecs.getRequestSpecForBooker(bookerAuthResponse.getToken());
    }
    @Step("create booker body")
    public UpdateBookingSteps createBookerBody(String firstname, String lastname, int totalprice, boolean depositpaid, int salesprice, String checkin, String checkout, String additionalneeds, String passportNo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookerRequest = BookerRequest.builder().firstName(firstname)
                .lastName(lastname).totalprice(totalprice).depositpaid(depositpaid)
                .saleprice(salesprice)
                .bookingdates(BookerRequestDates.builder().checkin(LocalDate.parse(checkin, formatter)).checkout(LocalDate.parse(checkout,formatter)).build())
                .additionalneeds(additionalneeds).passportNo(passportNo).build();
        return this;
    }
    @Step("update booking")
    public UpdateBookingSteps updateBooking() {
        response = given(requestSpecification)
                        .body(bookerRequest)
                        .when()
                        .put(BOOKING_ENDPOINT + bookingResponse.bookingid());
        return this;
    }
    @Step("extract booker added by me")
    public UpdateBookingSteps extractAddedBooker() {
        bookerResponseObjectFromUpdate = validatableResponse
                .extract()
                .body()
                .as(BookerResponse.class);
        return this;
    }
    @Step("validate updated bookers")
    public void validateUpdatedBookers() {
        assertThat(bookerRequest.getFirstName(), equalTo(bookerResponseObjectFromUpdate.getFirstname()));
    }
}
