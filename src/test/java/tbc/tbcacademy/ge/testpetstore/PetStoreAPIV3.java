package tbc.tbcacademy.ge.testpetstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.petstoreV3steps.AddOrderSteps;
import tbc.tbcacademy.ge.data.steps.petstoreV3steps.AddV3PetSteps;
import tbc.tbcacademy.ge.data.steps.petstoresteps.AddPetSteps;

public class PetStoreAPIV3 {
    AddOrderSteps addOrderSteps;
    AddV3PetSteps addV3PetSteps;
    @BeforeClass
    public void initializeSteps() {
        addOrderSteps = new AddOrderSteps();
        addV3PetSteps = new AddV3PetSteps();
    }
    @Test
    public void testAddOrder() {
        addOrderSteps
                .createPetBody()
                .addOrder()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPetOrderResponseAsClass()
                .validatePetID()
                .validatePetStatus();
    }
    @Test
    public void testAddPet() throws JsonProcessingException {
        addV3PetSteps
                .createPetBody()
                .serializeToXml()
                .addPet()
                .getValidatableResponse()
                .checkStatusCode()
                .validateResponseContentType()
                .deserializeResponseAsClass()
                .validateResponse();
    }
}
