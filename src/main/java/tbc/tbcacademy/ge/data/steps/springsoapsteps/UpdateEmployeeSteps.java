package tbc.tbcacademy.ge.data.steps.springsoapsteps;

import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.ObjectFactory;
import com.example.springboot.soap.interfaces.UpdateEmployeeRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Setter;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import tbc.tbcacademy.ge.data.steps.helpers.EmployeesNamespaceContext;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.SUCCESS_UPDATE_MESSAGE;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForSpringSoap;
import static tbc.tbcacademy.ge.data.steps.helpers.HelperFunctions.getRandomGregorianFormatDate;

public class UpdateEmployeeSteps extends CommonSteps<UpdateEmployeeSteps> {
    private final RequestSpecification requestSpecification;
    private EmployeeInfo employeeInfo;
    @Setter
    private long EmployeeID;
    public UpdateEmployeeSteps() {
        requestSpecification = getRequestSpecForSpringSoap();
    }
    @Step("Create Update Employee Request (with New Random Faker Data)")
    public UpdateEmployeeRequest createUpdateEmployeeRequest() {
        ObjectFactory objectFactory = new ObjectFactory();
        UpdateEmployeeRequest updateEmployeeRequest = objectFactory.createUpdateEmployeeRequest();
        employeeInfo = objectFactory.createEmployeeInfo();
        employeeInfo.setEmployeeId(EmployeeID);
        employeeInfo.setName(faker.name().firstName());
        employeeInfo.setDepartment(faker.internet().domainName());
        employeeInfo.setAddress(faker.address().streetAddress());
        employeeInfo.setSalary(BigDecimal.valueOf(faker.number().numberBetween(100, 1000)));
        employeeInfo.setEmail(faker.internet().emailAddress());
        employeeInfo.setBirthDate(getRandomGregorianFormatDate());
        updateEmployeeRequest.setEmployeeInfo(employeeInfo);
        return updateEmployeeRequest;
    }
    @Step("Send Update Employee Request and Get Response")
    public Response updateEmployeeAndGetResponse(String xml) {
        return given(requestSpecification)
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/updateEmployeeRequest")
                .body(xml)
                .post("ws");
    }
    @Step("Validate Update Employee Response Message for Success\n")
    public void validateUpdateResponseMessage(ValidatableResponse response) {
        response
                .body(hasXPath("//ns2:message",  new EmployeesNamespaceContext(), equalTo(SUCCESS_UPDATE_MESSAGE)));
    }
}
