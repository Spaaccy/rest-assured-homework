package tbc.tbcacademy.ge.data.steps.restfulbookersteps;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import static io.restassured.RestAssured.given;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKING_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForBooker;

public class DeleteBookingSteps extends CommonSteps<DeleteBookingSteps> {
    RequestSpecification requestSpecification;
    public DeleteBookingSteps() {
        requestSpecification = getRequestSpecForBooker(bookerAuthResponse.getToken());
    }
    public DeleteBookingSteps deleteBookingByID(int id) {
        response =
                given(requestSpecification)
                        .when()
                        .delete(BOOKING_ENDPOINT + id);
        return this;
    }
}
