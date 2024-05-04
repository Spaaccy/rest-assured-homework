package tbc.tbcacademy.ge.steps.petstoresteps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import tbc.tbcacademy.ge.model.FormData;
import tbc.tbcacademy.ge.steps.CommonSteps;

import static tbc.tbcacademy.ge.data.constants.CommonData.NOT_FOUND_CODE;
import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;
import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_ID_ENDPOINT;
import static org.hamcrest.Matchers.*;

public class PetSteps extends CommonSteps {
    Response response;
    FormData formData;


    @Step("create form body using: {0}, {1}, {2} values")
    public PetSteps createFormBody(int id, String petName, String status) {
        formData = new FormData();
        formData.petId = id;
        formData.formParams.put("petName", petName);
        formData.formParams.put("petStatus", status);
        return this;
    }
    @Step("send form using post")
    public PetSteps createForm() {
        response =
                requestSpecification
                        .contentType("application/x-www-form-urlencoded")
                        .pathParam("petId", formData.petId)
                        .formParams(formData.formParams)
                        .when()
                        .post(PET_ID_ENDPOINT);
        return this;
    }
    @Step("get validatable form response")
    public void getFormValidatableResponse() {
        validatableResponse =
                response.then();
    }
    @Step("validate form success response code")
    public PetSteps validateFormCodeSuccess() {
        validatableResponse
                .body("code", equalTo(SUCCESS_CODE));
        return this;
    }
    @Step("validate form fail response code")
    public void validateFormCodeFail() {
        validatableResponse
                .body("code", equalTo(NOT_FOUND_CODE));
    }
    @Step("validate form response has message")
    public PetSteps validateFormHasMessage() {
        validatableResponse
                .body("message", not(emptyOrNullString()));
        return this;
    }
    @Step("validate form response has type")
    public void validateFormHasType() {
        validatableResponse
                .body(  "type", not(emptyOrNullString()));;
    }
}
