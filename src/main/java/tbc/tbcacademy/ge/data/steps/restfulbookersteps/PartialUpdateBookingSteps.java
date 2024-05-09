package tbc.tbcacademy.ge.data.steps.restfulbookersteps;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerPartialUpdateRequest;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequestObject;
import tbc.tbcacademy.ge.data.specbuilder.RequestSpecs;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKING_ENDPOINT;

public class PartialUpdateBookingSteps extends CommonSteps<PartialUpdateBookingSteps> {
    private BookerPartialUpdateRequest bookerPartialUpdateRequest;
    public PartialUpdateBookingSteps(){
        requestSpecification = RequestSpecs.getRequestSpecForBooker(bookerAuthResponse.getToken());
    }
//    public PartialUpdateBookingSteps partialUpdateBookerBody() {
//        bookerRequestObject.setFirstname(faker.funnyName().name());
//        bookerRequestObject.setLastname(faker.name().lastName());
//        bookingRequest.setBooking(bookerRequestObject);
//        return this;
//    }
    public PartialUpdateBookingSteps createBookerFullNameBody() {
        bookerPartialUpdateRequest = new BookerPartialUpdateRequest();
        bookerPartialUpdateRequest.setFirstName("luka");
        bookerPartialUpdateRequest.setLastName("magda");
        bookingRequest.getBooking().setFirstname("luka");
        bookingRequest.getBooking().setLastname("magda");
        return this;
    }

    public PartialUpdateBookingSteps partialUpdateBookingByID() {
        response = given(requestSpecification)
                        .body(bookerPartialUpdateRequest)
                        .when()
                        .patch(BOOKING_ENDPOINT + bookingResponse.getBookingid());
        return this;
    }
    public void extractAddedBooker() {
        bookerResponseObjectFromUpdate = validatableResponse
                .extract()
                .body()
                .as(BookerRequestObject.class);
    }
    public void validateUpdatedBookers(BookerRequestObject bookerResponseFromGet) {
        assertThat(bookerResponseObjectFromUpdate, equalTo(bookerResponseFromGet));
    }
}
