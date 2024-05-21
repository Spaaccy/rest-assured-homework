package tbc.tbcacademy.ge.data.steps.springsoapsteps;

import com.example.springboot.soap.interfaces.AddEmployeeRequest;
import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.ObjectFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import tbc.tbcacademy.ge.data.steps.helpers.EmployeesNamespaceContext;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.SUCCESS_ADD_MESSAGE;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForSpringSoap;
import static tbc.tbcacademy.ge.data.steps.helpers.HelperFunctions.getRandomGregorianFormatDate;

public class AddEmployeeSteps extends CommonSteps<AddEmployeeSteps> {
    private final RequestSpecification requestSpecification;
    private EmployeeInfo employeeInfo;

    public AddEmployeeSteps() {
        requestSpecification = getRequestSpecForSpringSoap();
    }

    @Step("Create Add Employee Request with Random Faker Data")
    public AddEmployeeRequest createAddEmployeeRequest() {
        ObjectFactory objectFactory = new ObjectFactory();
        AddEmployeeRequest addEmployeeRequest = objectFactory.createAddEmployeeRequest();
        employeeInfo = objectFactory.createEmployeeInfo();
        employeeInfo.setAddress(faker.address().fullAddress());
        employeeInfo.setEmployeeId(faker.number().randomDigitNotZero());
        employeeInfo.setBirthDate(getRandomGregorianFormatDate());
        employeeInfo.setName(faker.name().firstName());
        employeeInfo.setEmail(faker.internet().emailAddress());
        employeeInfo.setSalary(BigDecimal.valueOf(faker.number().numberBetween(500, 1000)));
        employeeInfo.setDepartment(faker.company().catchPhrase());
        employeeInfo.setPhone(String.valueOf(faker.random().nextLong()));
        addEmployeeRequest.setEmployeeInfo(employeeInfo);
        return addEmployeeRequest;
    }
    @Step("Send Add Employee Request and Get Response")
    public Response addEmployeeAndGetResponse(String xml) {
        return given(requestSpecification)
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/addEmployeeRequest")
                .body(xml)
                .post("/ws");

    }
    @Step("Validate Add Employee Response Message for Success")
    public void validateAddResponseMessage(ValidatableResponse response) {
        response
                .body(hasXPath("//ns2:message",  new EmployeesNamespaceContext(), equalTo(SUCCESS_ADD_MESSAGE)));
    }
}
