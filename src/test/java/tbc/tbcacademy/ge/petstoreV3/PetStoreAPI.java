//package tbc.tbcacademy.ge.petstoreV3;
//import io.qameta.allure.*;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Test;
//import pet.store.v3.invoker.ApiClient;
//import pet.store.v3.model.Order;
//import tbc.tbcacademy.ge.data.steps.petstoresteps.PetStoreAddOrderSteps;
//import tbc.tbcacademy.ge.data.steps.petstoresteps.PetStoreAddPetSteps;
//import tbc.tbcacademy.ge.data.steps.petstoresteps.PetStoreApiClientStep;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//
//@Epic("Pet Store API - Core Functionality: Add Pet and Place Order")
//public class PetStoreAPI {
//    PetStoreApiClientStep petStoreApiClientStep;
//    PetStoreAddOrderSteps petStoreAddOrderSteps;
//    PetStoreAddPetSteps petStoreAddPetSteps;
//    ApiClient apiClient;
//
//    @BeforeSuite
//    public void beforeSuite() {
//        petStoreApiClientStep = new PetStoreApiClientStep();
//        apiClient = petStoreApiClientStep.getApiClientForPetStore();
//    }
//
//    @BeforeClass
//    void beforeClass(){
//        petStoreAddOrderSteps = new PetStoreAddOrderSteps();
//        petStoreAddPetSteps = new PetStoreAddPetSteps();
//        petStoreAddOrderSteps.setApiClient(apiClient);
//        petStoreAddPetSteps.setApiClient(apiClient);
//    }
//    @Test(priority = 1, description = "verify order")
//    @Feature("Pet Store Order Management")
//    @Severity(SeverityLevel.NORMAL)
//    @Story("User can successfully place a pet order")
//    @Description("Verify that a valid order for a pet can be placed and " +
//            "that the order details returned match the expected values.")
//     void addPetOrderTest() {
//        petStoreAddOrderSteps
//                .createOrder()
//                .placeOrder();
//
//        Order responseOrder = petStoreAddOrderSteps.getResponseAsOrder();
//        Order expectedOrder = petStoreAddOrderSteps.getOrder();
//        assertThat(responseOrder.petId(), equalTo(expectedOrder.petId()));
//        assertThat(responseOrder.status(), equalTo(expectedOrder.status()));
//    }
//    @Test(priority = 2, description = "verify pet")
//    @Feature("Pet Store Pet Management")
//    @Severity(SeverityLevel.NORMAL)
//    @Story("User can successfully add a new pet")
//    @Description("Verify that a new pet can be added to the store with the " +
//            "correct details and that the API returns a successful response.")
//    void addPetTest() {
//        petStoreAddPetSteps
//                .createPet()
//                .addPet()
//                .validateAddPetResponse();
//    }
//}
