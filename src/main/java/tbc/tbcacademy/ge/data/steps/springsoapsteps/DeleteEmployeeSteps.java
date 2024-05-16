package tbc.tbcacademy.ge.data.steps.springsoapsteps;

import com.example.springboot.soap.interfaces.DeleteEmployeeRequest;
import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.ObjectFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Setter;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import tbc.tbcacademy.ge.data.steps.helpers.EmployeesNamespaceContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.SUCCESS_DELETE_MESSAGE;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForSpringSoap;

public class DeleteEmployeeSteps extends CommonSteps<DeleteEmployeeSteps> {
    private RequestSpecification requestSpecification;
    @Setter
    private EmployeeInfo employeeInfo;
    public DeleteEmployeeSteps() {
        requestSpecification = getRequestSpecForSpringSoap();
    }
    @Step("Create Delete Employee Request (Using Employee ID from Previous Add)")
    public DeleteEmployeeRequest createDeleteEmployeeRequest() {
        ObjectFactory objectFactory = new ObjectFactory();
        DeleteEmployeeRequest deleteEmployeeRequest = objectFactory.createDeleteEmployeeRequest();
        deleteEmployeeRequest.setEmployeeId(employeeInfo.getEmployeeId());
        return deleteEmployeeRequest;
    }
    @Step("Send Delete Employee Request and Get Response")
    public Response deleteEmployeeAndGetResponse(String xml) {
        return given(requestSpecification)
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/deleteEmployeeRequest")
                .body(xml)
                .post("ws");
    }
    @Step("Validate Delete Employee Response Message for Success")
    public void validateDeleteResponseMessage(ValidatableResponse response) {
        response
                .body(hasXPath("//ns2:message",  new EmployeesNamespaceContext(), equalTo(SUCCESS_DELETE_MESSAGE)));
    }
}
