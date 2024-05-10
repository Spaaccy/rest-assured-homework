package tbc.tbcacademy.ge.data.specbuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static tbc.tbcacademy.ge.data.constants.BookStoreData.BOOK_STORE_URI;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKER_BASE_URI;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_STORE_URL;

public class RequestSpecs {
    static RequestSpecification requestSpecification;

    public static RequestSpecification getRequestSpecForBookerUpdate() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BOOKER_BASE_URI)
                .setContentType(ContentType.JSON)
                .build();

        return given(requestSpecification).header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    public static RequestSpecification getBaseRequestSpecForBooking() {
        return new RequestSpecBuilder()
                .setBaseUri(BOOK_STORE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }


    public static RequestSpecification getBaseRequestSpecForPetStore() {
        return new RequestSpecBuilder()
                .setBaseUri(PET_STORE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
