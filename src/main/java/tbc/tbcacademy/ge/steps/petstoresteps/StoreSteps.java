package tbc.tbcacademy.ge.steps.petstoresteps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import tbc.tbcacademy.ge.model.PetData;
import tbc.tbcacademy.ge.steps.CommonSteps;

import static tbc.tbcacademy.ge.data.constants.PetStoreData.ORDER_ENDPOINT;
import static org.hamcrest.Matchers.equalTo;

public class StoreSteps extends CommonSteps {
    PetData petData;
    Response response;

    @Step("create order body using: {0}, {1}, {2}, {3}, {4}, {5} values")
    public StoreSteps createOrderBody(int id, int petId, int quantity, String shipDate, String status, Boolean complete) {
        petData = new PetData();
        petData.id = id;
        petData.petId = petId;
        petData.quantity = quantity;
        petData.shipDate = shipDate;
        petData.status = status;
        petData.complete = complete;
        return this;
    }
    @Step("send order using post")
    public StoreSteps createOrder() {
        response = requestSpecification
                .contentType("application/json")
                .body(petData)
                .when()
                .post(ORDER_ENDPOINT);
        return this;
    }
    @Step("get validatable order response")
    public void getOrderValidatableResponse() {
        validatableResponse =
                response.then();
    }
    @Step("validate order id")
    public StoreSteps validateOrderId() {
        validatableResponse.body("id", equalTo(petData.id));
        return this;
    }
    @Step("validate pet id")
    public StoreSteps validateOrderPetId() {
        validatableResponse.body("petId", equalTo(petData.petId));
        return this;
    }
    @Step("validate order quantity")
    public StoreSteps validateOrderQuantity() {
        validatableResponse.body( "quantity", equalTo(petData.quantity));
        return this;
    }
    @Step("validate order ship date")
    public StoreSteps validateOrderShipDate() {
        validatableResponse.body( "shipDate", equalTo(petData.shipDate));
        return this;
    }
    @Step("validate order status")
    public StoreSteps validateOrderStatus() {
        validatableResponse.body( "status", equalTo(petData.status));
        return this;
    }
    @Step("validate order complete")
    public void validateOrderComplete() {
        validatableResponse.body( "complete", equalTo(petData.complete));
    }
    // ase shemedzlo damewera soft asertit magram erti didi stepi unda kofiliko, ver gadavwkvite romeli jobia calcalke stepebad chashla tu erti didi ikos da soft :d
//    public void validateOrderResponse() {
//        validatableResponse
//                .body("id", equalTo(petData.id),
//                 "petId", equalTo(petData.petId),
//                 "quantity", equalTo(petData.quantity),
//                 "shipDate", equalTo(petData.shipDate),
//                 "status", equalTo(petData.status),
//                 "complete", equalTo(petData.complete));
//    }

}
