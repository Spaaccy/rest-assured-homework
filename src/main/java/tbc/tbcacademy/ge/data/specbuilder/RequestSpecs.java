package tbc.tbcacademy.ge.data.specbuilder;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.XmlConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {
    public static RequestSpecification getRequestSpecForOorSprong() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso")
                .setContentType(ContentType.JSON)
                .build();
    }
    public static RequestSpecification getRequestSpecForSpringSoap() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost:8087/")
                .setContentType(ContentType.XML)
                .addFilter(new AllureRestAssured())
                .build()
                .header("Content-Type", "text/xml; charset=utf-8");
    }
}
