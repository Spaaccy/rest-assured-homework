package tbc.tbcacademy.ge.data.specbuilder;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecs {
    public static ResponseSpecification createResponseCheckerSpec(int expectedStatusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .build();
    }
}
