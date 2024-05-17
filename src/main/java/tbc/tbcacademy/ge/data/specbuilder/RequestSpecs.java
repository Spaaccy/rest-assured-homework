package tbc.tbcacademy.ge.data.specbuilder;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.constants.ErgastData;

import static io.restassured.RestAssured.given;
import static tbc.tbcacademy.ge.data.constants.BookStoreData.BOOK_STORE_URI;
import static tbc.tbcacademy.ge.data.constants.BookerData.BOOKER_BASE_URI;
import static tbc.tbcacademy.ge.data.constants.ErgastData.ERGAST_URI;
import static tbc.tbcacademy.ge.data.constants.FakerData.FAKER_URI;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_STORE_URL;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_STORE_URL_V3;
import static tbc.tbcacademy.ge.data.constants.SwapiData.SWAPI_URL;

public class RequestSpecs {
    public static RequestSpecification getRequestSpecForBookerToken() {
        return new RequestSpecBuilder()
                .setBaseUri(BOOKER_BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }
    public static RequestSpecification getRequestSpecForBooker(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(BOOKER_BASE_URI)
                .setContentType(ContentType.JSON)
                .addHeader("Cookie", "token=" + token)
                .build();
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
                .build()
                .filter(new AllureRestAssured());
    }
    public static RequestSpecification getBaseRequestSpecForPetStoreV3() {
        return new RequestSpecBuilder()
                .setBaseUri(PET_STORE_URL_V3)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }

    public static RequestSpecification getBaseRequestSpecForErgast() {
        return new RequestSpecBuilder()
                .setBaseUri(ERGAST_URI)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }


    public static RequestSpecification getBaseRequestSpecForSwapi() {
        return new RequestSpecBuilder()
                .setBaseUri(SWAPI_URL)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());

    }
    public static RequestSpecification getBaseRequestSpecForFaker() {
        return new RequestSpecBuilder()
                .setBaseUri(FAKER_URI)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }
}
