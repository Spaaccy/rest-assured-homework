package tbc.tbcacademy.ge.fakerapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.fakerapisteps.FakerSchemaSteps;

public class FakerAPI {
    FakerSchemaSteps fakerSchemaSteps;
    @BeforeClass(alwaysRun = true)
    public void initiateSteps() {
        fakerSchemaSteps = new FakerSchemaSteps();
    }
    @Test(priority = 1)
    public void fakerSchemaTest() throws JsonProcessingException {
        fakerSchemaSteps
                .getSchemaFile()
                .getFaker()
                .getValidatableResponse()
                .checkStatusCode()
                .extractFakerCreditCardResponseAsClass()
                .serializeResponseAsClass()
                .validateFakerSchema();
    }
}
