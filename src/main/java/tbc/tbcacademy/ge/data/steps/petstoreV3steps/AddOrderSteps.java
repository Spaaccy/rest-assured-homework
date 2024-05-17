package tbc.tbcacademy.ge.data.steps.petstoreV3steps;

import io.qameta.allure.Step;
import tbc.tbcacademy.ge.data.models.requests.petv3.OrderRequest;
import tbc.tbcacademy.ge.data.models.responses.petstorev3.OrderResponse;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.ORDER_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForPetStoreV3;

public class AddOrderSteps extends CommonSteps<AddOrderSteps> {
    private OrderRequest myPetOrderRequest;
    private OrderResponse petOrderResponse;

    public AddOrderSteps() {
        requestSpecification = getBaseRequestSpecForPetStoreV3();
    }
    @Step("create pet body")
    public AddOrderSteps createPetBody() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        myPetOrderRequest = new OrderRequest()
                .id(faker.number().numberBetween(20, 100))
                .petId(faker.number().numberBetween(20, 100))
                .petQuantity(faker.number().numberBetween(20, 100))
                .shippingDate(LocalDateTime.parse("2024-05-10T23:16:03.229Z", formatter))
                .petStatus("approved")
                .complete(false);
        return this;
    }
    @Step("ad pet order")
    public AddOrderSteps addOrder() {
        response = given(requestSpecification)
                .body(myPetOrderRequest)
                .when()
                .post(ORDER_ENDPOINT);
        return this;
    }
    @Step("extract pet order response as class")
    public AddOrderSteps extractPetOrderResponseAsClass() {
        petOrderResponse = validatableResponse
                .extract()
                .body()
                .as(OrderResponse.class);
        return this;
    }
    @Step("validate pet order id")
    public AddOrderSteps validatePetID() {
        assertThat(myPetOrderRequest.petId(), equalTo(petOrderResponse.petId()));
        return this;
    }
    @Step("validate pet status")
    public void validatePetStatus() {
        assertThat(myPetOrderRequest.petStatus(), equalTo(petOrderResponse.petStatus()));
    }
}
