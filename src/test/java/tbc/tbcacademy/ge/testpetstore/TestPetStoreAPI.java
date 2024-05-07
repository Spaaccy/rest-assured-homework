package tbc.tbcacademy.ge.testpetstore;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.steps.CommonSteps;
import tbc.tbcacademy.ge.steps.petstoresteps.PetSteps;
import tbc.tbcacademy.ge.steps.petstoresteps.StoreSteps;
import tbc.tbcacademy.ge.steps.petstoresteps.UserSteps;
import static tbc.tbcacademy.ge.data.constants.CommonData.NOT_FOUND_CODE;
import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.*;

public class TestPetStoreAPI extends CommonSteps{
    StoreSteps storeSteps;
    PetSteps petSteps;
    UserSteps userSteps;

    @BeforeClass(alwaysRun = true)
    public void setUpBeforeClass() {
        storeSteps = new StoreSteps();
        petSteps = new PetSteps();
        userSteps = new UserSteps();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpBeforeMethod() {
        CommonSteps.setRequestSpecification(PET_STORE_URL);
    }

    @Test(priority = 1)
    void addOrderTest() {
        storeSteps
                .createOrderBody(PET_ID, PET_ORDER_ID, PET_QUANTITY, PET_SHIP_DATE, PET_ORDER_STATUS, PET_ORDER_COMPLETE)
                .createOrder()
                .getOrderValidatableResponse();
        storeSteps
                .checkStatusCode(SUCCESS_CODE);
        storeSteps
                .validateOrderId()
                .validateOrderPetId()
                .validateOrderQuantity()
                .validateOrderShipDate()
                .validateOrderStatus()
                .validateOrderComplete();
    }

    @Test(priority = 2)
    void addFormTest() {
        petSteps
                .createFormBody(FORM_ID, FORM_PET_NAME, FORM_PET_STATUS)
                .createForm()
                .getFormValidatableResponse();
        petSteps
                .checkStatusCode(SUCCESS_CODE);
        petSteps
                .validateFormCodeSuccess()
                .validateFormHasMessage()
                .validateFormHasType();
    }

    @Test(priority = 3)
    void addFormTestFail() {
        petSteps
                .createFormBody(INCORRECT_FORM_ID, FORM_PET_NAME, FORM_PET_STATUS)
                .createForm()
                .getFormValidatableResponse();
        petSteps
                .checkStatusCode(NOT_FOUND_CODE);
        petSteps
                .validateFormCodeFail();
    }

    @Test(priority = 4)
    void addUserTest() {
        userSteps
                .createUserBody(USER_NAME, PASSWORD)
                .createUser()
                .getUserValidatableResponse();
        userSteps
                .checkStatusCode(SUCCESS_CODE);
        userSteps
                .validateUserMessage()
                .getUserJsonPath();
        String userMessageNumber = userSteps.extractNumbersFromUserMessage();
        System.out.println(userMessageNumber);
    }

}
