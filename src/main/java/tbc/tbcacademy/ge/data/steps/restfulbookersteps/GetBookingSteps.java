package tbc.tbcacademy.ge.data.steps.restfulbookersteps;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.models.requests.restfulbooker.BookerRequestObject;
import tbc.tbcacademy.ge.data.specbuilder.RequestSpecs;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import static io.restassured.RestAssured.given;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKING_ENDPOINT;

public class GetBookingSteps extends CommonSteps<GetBookingSteps> {
    RequestSpecification baseRequestSpecification;
    public GetBookingSteps(){
        baseRequestSpecification = RequestSpecs.getRequestSpecForBookerToken();
    }

    @Step("Get Booking By ID")
    public BookerRequestObject getBookingByID(int id){
       return given(baseRequestSpecification)
               .when()
               .get(BOOKING_ENDPOINT + id)
               .then()
               .extract()
               .body().as(BookerRequestObject.class);
    }
}
