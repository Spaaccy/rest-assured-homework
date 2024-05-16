package tbc.tbcacademy.ge.data.steps.springsoapsteps;

import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.GetEmployeeByIdRequest;
import com.example.springboot.soap.interfaces.ObjectFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import tbc.tbcacademy.ge.data.steps.helpers.EmployeesNamespaceContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.NULL_SOURCE_MESSAGE;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForSpringSoap;

public class GetEmployeeSteps extends CommonSteps<GetEmployeeSteps> {
    @Setter
    @Getter
    private EmployeeInfo employeeInfo;
    private final RequestSpecification requestSpecification;

    public GetEmployeeSteps() {
        requestSpecification = getRequestSpecForSpringSoap();
    }
    @Step("Create Get Employee Request (Using Previously Added Employee ID)")
    public GetEmployeeByIdRequest createGetEmployeeRequest() {
        ObjectFactory objectFactory = new ObjectFactory();
        GetEmployeeByIdRequest getEmployeeByIdRequest = objectFactory.createGetEmployeeByIdRequest();
        getEmployeeByIdRequest.setEmployeeId(employeeInfo.getEmployeeId());
        return getEmployeeByIdRequest;
    }
    @Step("Send Get Employee Request (By ID) and Retrieve Response")
    public Response getAddedEmployeeResponse(String xml) {
        return given(requestSpecification)
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/getEmployeeByIdRequest")
                .body(xml)
                .post("ws");
    }
    @Step("Validate null source message")
    public void validateNullSourceMessage(Response response) {
        response
                .then()
                .body(hasXPath("//faultstring",  new EmployeesNamespaceContext()), containsString(NULL_SOURCE_MESSAGE));
    }
}
