package tbc.tbcacademy.ge.data.steps;
import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import tbc.tbcacademy.ge.data.steps.helpers.EmployeesNamespaceContext;
import tbc.tbcacademy.ge.data.steps.helpers.SerializeXml;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;
import static tbc.tbcacademy.ge.data.specbuilder.ResponseSpecs.createResponseCheckerSpec;

public class CommonSteps<T extends CommonSteps> {
    public static Faker faker = new Faker();

    @Step("Check Status Code (Response)")
    public T checkStatusCode(Response response, int statusCode) {
        response.then()
                .spec(createResponseCheckerSpec(statusCode));
        return (T) this;
    }
    @Step("Check Status Code (ValidatableResponse)")
    public T checkStatusCodeValResponse(ValidatableResponse response, int statusCode) {
        response
                .spec(createResponseCheckerSpec(statusCode));
        return (T) this;
    }
    @Step("Serialize Object to XML String")
    public String serializeToXML(Object body) {
        return SerializeXml.marshallSoapRequest(body);
    }
    @Step("Extract XML Path from Response")
    public XmlPath getXmlPathFromResponse(Response response) {
        return response.xmlPath();
    }
    @Step("Get Validatable Response")
    public ValidatableResponse getValidatableResponse(Response response) {
        return response.then().assertThat();
    }
    @Step("Validate Employee ID in Response")
    public T validateEmployeeIDTEST(ValidatableResponse response, EmployeeInfo employeeInfo) {
        response
                .body(hasXPath("//ns2:employeeId", new EmployeesNamespaceContext(), equalTo(String.valueOf(employeeInfo.getEmployeeId()))));
        return (T) this;
    }
    @Step("Validate Employee Name in Response")
    public T validateEmployeeNameTEST(ValidatableResponse response, EmployeeInfo employeeInfo) {
        response
                .body(hasXPath("//ns2:name", new EmployeesNamespaceContext(), equalTo(employeeInfo.getName())));
        return (T) this;
    }
    @Step("Validate Employee Department in Response")
    public T validateEmployeeDepartmentTEST(ValidatableResponse response, EmployeeInfo employeeInfo) {
        response
                .body(hasXPath("//ns2:department", new EmployeesNamespaceContext(), equalTo(employeeInfo.getDepartment())));
        return (T) this;

    }
    @Step("Validate Employee Phone in Response")
    public T validateEmployeePhoneTEST(ValidatableResponse response, EmployeeInfo employeeInfo) {
        response
                .body(hasXPath("//ns2:phone", new EmployeesNamespaceContext(), equalTo(employeeInfo.getPhone())));
        return (T) this;
    }
    @Step("Validate Employee Address in Response")
    public T validateEmployeeAddressTEST(ValidatableResponse response, EmployeeInfo employeeInfo) {
        response
                .body(hasXPath("//ns2:address", new EmployeesNamespaceContext(), equalTo(employeeInfo.getAddress())));
        return (T) this;
    }
    @Step("Validate Employee Salary in Response")
    public T validateEmployeeSalaryTEST(ValidatableResponse response, EmployeeInfo employeeInfo) {
        double number = Double.parseDouble(String.valueOf(employeeInfo.getSalary()));
        String formattedNumber = String.format("%.2f", number);
        response
                .body(hasXPath("//ns2:salary", new EmployeesNamespaceContext(), equalTo(formattedNumber)));
        return (T) this;
    }
    @Step("Validate Employee Email in Response")
    public T validateEmployeeEmailTEST(ValidatableResponse response, EmployeeInfo employeeInfo) {
        response
                .body(hasXPath("//ns2:email", new EmployeesNamespaceContext(), equalTo(employeeInfo.getEmail())));
        return (T) this;
    }
    @Step("Validate Employee Birth Date in Response")
    public T validateEmployeeBirthDateTEST(ValidatableResponse response, EmployeeInfo employeeInfo) {
        response
                .body(hasXPath("//ns2:birthDate", new EmployeesNamespaceContext(), equalTo(String.valueOf(employeeInfo.getBirthDate()))));
        return (T) this;
    }
}
