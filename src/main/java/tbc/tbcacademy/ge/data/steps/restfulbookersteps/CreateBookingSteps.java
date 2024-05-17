package tbc.tbcacademy.ge.data.steps.restfulbookersteps;

import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequest;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequestDates;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerMainResponse;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerResponse;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerResponseDates;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKING_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForBooker;

public class CreateBookingSteps extends CommonSteps<CreateBookingSteps> {
    private RequestSpecification requestSpecification;
    public CreateBookingSteps() {
        requestSpecification = getRequestSpecForBooker(bookerAuthResponse.getToken());
    }

    public CreateBookingSteps createBookerBody() {
        bookingRequest = BookerRequest.builder()
                                .firstName(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .saleprice(10)
                                .totalprice(faker.number().numberBetween(50, 100))
                                .depositpaid(faker.bool().bool())
                                .bookingdates(BookerRequestDates.builder()
                                        .checkin(LocalDate.parse("2018-01-01"))
                                        .checkout(LocalDate.parse("2019-01-01"))
                                        .build())
                                .additionalneeds(faker.cat().breed())
                                .passportNo(null)
                                .build();
        return this;
    }

    public CreateBookingSteps addBooker() {
        response =
                given(requestSpecification)
                        .body(bookingRequest)
                        .post(BOOKING_ENDPOINT);
        return this;
    }
    public void extractAddedBooker() {
        bookingResponse = validatableResponse
                .extract()
                .body()
                .as(BookerMainResponse.class);
    }

    public void validateUpdatedBookers(BookerResponse bookerResponseFromGet) {
        assertThat(bookingResponse.booking(), equalTo(bookerResponseFromGet));
    }
}
