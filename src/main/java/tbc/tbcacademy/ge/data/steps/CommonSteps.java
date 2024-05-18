package tbc.tbcacademy.ge.data.steps;
import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.GetEmployeeByIdResponse;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import tbc.tbcacademy.ge.data.helpers.SerializeDeserializeXml;

import static org.hamcrest.MatcherAssert.assertThat;
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
        return SerializeDeserializeXml.marshallSoapRequest(body);
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
    public T validateEmployeeIDTEST(GetEmployeeByIdResponse getEmployeeByIdResponse, EmployeeInfo employeeInfo) {
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getEmployeeId(), equalTo(employeeInfo.getEmployeeId()));
        return (T) this;
    }
    @Step("Validate Employee Name in Response")
    public T validateEmployeeNameTEST(GetEmployeeByIdResponse getEmployeeByIdResponse, EmployeeInfo employeeInfo) {
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getName(), equalTo(employeeInfo.getName()));
        return (T) this;
    }
    @Step("Validate Employee Department in Response")
    public T validateEmployeeDepartmentTEST(GetEmployeeByIdResponse getEmployeeByIdResponse, EmployeeInfo employeeInfo) {
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getDepartment(), equalTo(employeeInfo.getDepartment()));
        return (T) this;
    }
    @Step("Validate Employee Phone in Response")
    public T validateEmployeePhoneTEST(GetEmployeeByIdResponse getEmployeeByIdResponse, EmployeeInfo employeeInfo) {
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getPhone(), equalTo(employeeInfo.getPhone()));
        return (T) this;
    }
    @Step("Validate Employee Address in Response")
    public T validateEmployeeAddressTEST(GetEmployeeByIdResponse getEmployeeByIdResponse, EmployeeInfo employeeInfo) {
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getAddress(), equalTo(employeeInfo.getAddress()));
        return (T) this;
    }
    @Step("Validate Employee Salary in Response")
    public T validateEmployeeSalaryTEST(GetEmployeeByIdResponse getEmployeeByIdResponse, EmployeeInfo employeeInfo) {
        double number = Double.parseDouble(String.valueOf(getEmployeeByIdResponse.getEmployeeInfo().getSalary()));
        assertThat(number, equalTo(Double.parseDouble(String.valueOf(employeeInfo.getSalary()))));
        return (T) this;
    }
    @Step("Validate Employee Email in Response")
    public T validateEmployeeEmailTEST(GetEmployeeByIdResponse getEmployeeByIdResponse, EmployeeInfo employeeInfo) {
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getEmail(), equalTo(employeeInfo.getEmail()));
        return (T) this;
    }
    @Step("Validate Employee Birth Date in Response")
    public T validateEmployeeBirthDateTEST(GetEmployeeByIdResponse getEmployeeByIdResponse, EmployeeInfo employeeInfo) {
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getBirthDate(), equalTo(employeeInfo.getBirthDate()));
        return (T) this;
    }
}
