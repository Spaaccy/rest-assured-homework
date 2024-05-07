package tbc.tbcacademy.ge.data.steps.restfulbookersteps;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import tbc.tbcacademy.ge.data.specbuilder.RequestSpecs;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKING_ENDPOINT;
import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;

public class UpdateBookingSteps extends CommonSteps<UpdateBookingSteps> {
    private JSONObject bookingDates;
    private JSONObject bookingBody;

    public UpdateBookingSteps(){
        requestSpecification = RequestSpecs.getRequestSpecForBookerUpdate();
    }
    public UpdateBookingSteps createBookingDates() {
        bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        return this;
    }
    public UpdateBookingSteps createBookingBody() {
        bookingBody = new JSONObject();
        bookingBody.put("firstname", faker.funnyName().name());
        bookingBody.put("lastname", faker.name().lastName());
        bookingBody.put("totalprice", faker.number().numberBetween(20, 50));
        bookingBody.put("depositpaid", faker.bool().bool());
        bookingBody.put("bookingdates", bookingDates);
        bookingBody.put("additionalneeds", faker.animal().name());
        return this;
    }

    public UpdateBookingSteps updateBookingByID(int id){
        response = requestSpecification
                        .body(bookingBody.toString())
                        .when()
                        .put(BOOKING_ENDPOINT + id);
        return this;
    }
    public UpdateBookingSteps extractAndValidateStatusCode() {
        assertThat(
                validatableResponse
                .extract().statusCode()
                ,equalTo(SUCCESS_CODE));
        return this;
    }
    public void logStatusIfStatusCodeIs(int statusCode) {
        validatableResponse
                .log()
                .ifStatusCodeIsEqualTo(statusCode);
    }
}
