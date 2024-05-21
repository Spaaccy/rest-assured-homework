//package tbc.tbcacademy.ge.data.steps.petstoresteps;
//import io.qameta.allure.Step;
//import lombok.Getter;
//import lombok.Setter;
//import pet.store.v3.api.StoreApi.PlaceOrderOper;
//import pet.store.v3.invoker.ApiClient;
//import pet.store.v3.model.Order;
//import tbc.tbcacademy.ge.data.steps.CommonSteps;
//import java.time.ZoneOffset;
//import static org.apache.http.HttpStatus.SC_OK;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static pet.store.v3.invoker.ResponseSpecBuilders.shouldBeCode;
//import static pet.store.v3.invoker.ResponseSpecBuilders.validatedWith;
//
//public class PetStoreAddOrderSteps extends CommonSteps {
//    @Getter
//    private Order order;
//    private PlaceOrderOper placeOrderOper;
//    @Setter
//    private ApiClient apiClient;
//
//    @Step("Create order body using order model")
//    public PetStoreAddOrderSteps createOrder(){
//        order = new Order()
//                .petId(faker.random().nextLong())
//                .id(faker.random().nextLong())
//                .quantity(faker.number().randomDigitNotZero())
//                .shipDate(faker.date().birthday().toInstant().atOffset(ZoneOffset.UTC))
//                .status(Order.StatusEnum.APPROVED)
//                .complete(true);
//        return this;
//    }
//
//    @Step("Go to store and send order using the following order POJO model")
//    public void placeOrder() {
//        placeOrderOper = apiClient
//                .store()
//                .placeOrder()
//                .body(order);
//    }
//    @Step("Get response and convert it back into Order POJO model")
//    public Order getResponseAsOrder(){
//        return placeOrderOper
//                .executeAs(validatedWith(shouldBeCode(SC_OK))
//                        .andThen(response -> {
//                            assertThat(response.toString(), not(emptyString()));
//                            return response;
//                        }));
//    }
//}
