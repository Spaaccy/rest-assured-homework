package tbc.tbcacademy.ge.springsoaptest;
import com.example.springboot.soap.interfaces.*;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.springsoapsteps.*;
import static tbc.tbcacademy.ge.data.constants.CommonData.FAIL_CODE;
import static tbc.tbcacademy.ge.data.constants.CommonData.SUCCESS_CODE;


@Epic("SpringSoap API - Core Functionality: Adding, Getting, Updating and Deleting Employee Data")
public class SpringSoapAPI {
    AddEmployeeSteps addEmployeeSteps;
    GetEmployeeSteps getEmployeeSteps;
    UpdateEmployeeSteps updateEmployeeSteps;
    DeleteEmployeeSteps deleteEmployeeSteps;

    @BeforeClass
    public void init() {
        addEmployeeSteps = new AddEmployeeSteps();
        getEmployeeSteps = new GetEmployeeSteps();
        updateEmployeeSteps = new UpdateEmployeeSteps();
        deleteEmployeeSteps = new DeleteEmployeeSteps();
    }

    @Test(priority = 1, description = "Adding new employee to SOAP API")
    @Feature("Employee Management")
    @Story("Add New Employee")
    @Severity(SeverityLevel.NORMAL)
    @Description("""
            This test case verifies the process of adding a new employee via the SOAP API. It covers:
            Creation of an 'AddEmployeeRequest' with random employee data.
            Serialization of the request to XML format.
            Sending the request to the SOAP endpoint.
            Validation of the response status code (200 OK).
            Validation of the success message in the response body.
            Storing the employee information for subsequent test steps.
            """)
    public void addEmployeeTest() {
        AddEmployeeRequest addEmployeeRequest = addEmployeeSteps
                .createAddEmployeeRequest();
        String addEmployeeXML = addEmployeeSteps
                .serializeToXML(addEmployeeRequest);
        Response response = addEmployeeSteps
                .addEmployeeAndGetResponse(addEmployeeXML);

        addEmployeeSteps
                .checkStatusCode(response, SUCCESS_CODE)
                .deserializeToAddEmployeeResponse(response)
                .validateAddResponseMessage();

        getEmployeeSteps.setEmployeeInfo(addEmployeeRequest.getEmployeeInfo());
    }

    @Test(priority = 2, dependsOnMethods = "addEmployeeTest", description = "Get employee from SOAP API")
    @Feature("Employee Management")
    @Story("Get Employee by ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("""
            This test case verifies the retrieval of employee details by ID after the employee has been successfully added in the previous test ('addEmployeeTest'). 
            The following steps are performed:
            Creation of a 'GetEmployeeByIdRequest' using the employee ID from the 'addEmployeeTest'.
            Serialization of the request to XML format.
            Sending the request to the SOAP endpoint.
            Validation of the response status code (200 OK).
            Thorough validation of all employee details (ID, name, department, phone, address, salary, email, birthdate) in the response against the expected values.
            """)
    public void GetEmployeeTest() {
        GetEmployeeByIdRequest getEmployeeByIdRequest = getEmployeeSteps
                .createGetEmployeeRequest();
        String getEmployeeXML = getEmployeeSteps
                .serializeToXML(getEmployeeByIdRequest);
        Response response = getEmployeeSteps
                .getAddedEmployeeResponse(getEmployeeXML);

        GetEmployeeByIdResponse getEmployeeByIdResponse = getEmployeeSteps
                .checkStatusCode(response, SUCCESS_CODE)
                .deserializeToGetEmployeeByIDResponse(response);

        EmployeeInfo employeeInfo = getEmployeeSteps.getEmployeeInfo();
        getEmployeeSteps
                .validateEmployeeIDTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeNameTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeDepartmentTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeePhoneTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeAddressTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeSalaryTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeEmailTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeBirthDateTEST(getEmployeeByIdResponse, employeeInfo);
        updateEmployeeSteps.setEmployeeID(employeeInfo.getEmployeeId());
    }

    @Test(priority = 3, dependsOnMethods = {"addEmployeeTest", "GetEmployeeTest"}, description = "Updating the employee, and getting it")
    @Feature("Employee Management")
    @Story("Update Employee Information")
    @Severity(SeverityLevel.NORMAL)
    @Description("""
            This test case verifies the complete flow of updating an employee's information through the SOAP API. It involves:

            1. Creating an Update Request: Generating a request with new random employee data.
            2. Sending the Update Request: Submitting the request to the API endpoint.
            3. Validating the Update Response:
               Checking for a successful status code (200 OK).
               Verifying the success message in the response.
            4. Fetching Updated Employee:** Retrieving the updated employee information using the `GetEmployeeByIdRequest`.
            5. Validating the Fetched Data:**
               Checking the status code of the get response.
               Thoroughly validating all employee details (ID, name, department, address, salary, email, birthdate) in the get response against the expected updated values.
            This test ensures that updates to employee information are correctly processed and reflected in the system.
            """)
    public void UpdateEmployeeTest() {
        UpdateEmployeeRequest updateEmployeeRequest = updateEmployeeSteps
                .createUpdateEmployeeRequest();
        String updateEmployeeXML = updateEmployeeSteps
                .serializeToXML(updateEmployeeRequest);
        Response updateResponse = updateEmployeeSteps
                .updateEmployeeAndGetResponse(updateEmployeeXML);


        updateEmployeeSteps
                .checkStatusCode(updateResponse, SUCCESS_CODE)
                .deserializeToUpdateEmployeeResponse(updateResponse)
                .validateUpdateResponseMessage();

        EmployeeInfo employeeInfo = updateEmployeeRequest.getEmployeeInfo();
        getEmployeeSteps.setEmployeeInfo(employeeInfo);

        GetEmployeeByIdRequest getEmployeeByIdRequest = getEmployeeSteps
                .createGetEmployeeRequest();
        String getEmployeeXML = getEmployeeSteps
                .serializeToXML(getEmployeeByIdRequest);
        Response getResponse = getEmployeeSteps
                .getAddedEmployeeResponse(getEmployeeXML);
        GetEmployeeByIdResponse getEmployeeByIdResponse = getEmployeeSteps
                .checkStatusCode(getResponse, SUCCESS_CODE)
                .deserializeToGetEmployeeByIDResponse(getResponse);

        updateEmployeeSteps
                .validateEmployeeIDTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeNameTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeDepartmentTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeAddressTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeSalaryTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeEmailTEST(getEmployeeByIdResponse, employeeInfo)
                .validateEmployeeBirthDateTEST(getEmployeeByIdResponse, employeeInfo);

        deleteEmployeeSteps.setEmployeeInfo(updateEmployeeRequest.getEmployeeInfo());
    }

    @Test(priority = 4, dependsOnMethods = {"addEmployeeTest", "GetEmployeeTest", "UpdateEmployeeTest"}, description = "Deleting employee nad veryfing it using get")
    @Feature("Employee Management")
    @Story("Delete Employee by ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("""
            This test case verifies the deletion of an employee through the SOAP API. It relies on the successful completion of previous tests to ensure an employee exists for deletion. Here's the workflow:
            1. Create Delete Request:** A request to delete the employee is generated using the ID obtained from previous tests.
            2. Send Delete Request:** The request is sent to the API endpoint.
            3. Validate Delete Response:** The response is checked for:
               Success status code (200 OK)
               Confirmation message of successful deletion
            4. Attempt Get After Delete:** An attempt is made to retrieve the deleted employee's information.
            5. Validate Get Failure:**  The response from the get request is checked for a failure status code (e.g., 404 Not Found), confirming the employee was deleted.
            """)
    public void DeleteEmployeeTest() {
        DeleteEmployeeRequest deleteEmployeeRequest = deleteEmployeeSteps
                .createDeleteEmployeeRequest();
        String deleteEmployeeXML = deleteEmployeeSteps
                .serializeToXML(deleteEmployeeRequest);

        Response response = deleteEmployeeSteps
                .deleteEmployeeAndGetResponse(deleteEmployeeXML);

        deleteEmployeeSteps
                .checkStatusCode(response, SUCCESS_CODE)
                .deserializeToDeleteEmployeeResponse(response)
                .validateDeleteResponseMessage();

        GetEmployeeByIdRequest getEmployeeByIdRequest = getEmployeeSteps
                .createGetEmployeeRequest();
        String getEmployeeXML = getEmployeeSteps
                .serializeToXML(getEmployeeByIdRequest);
        Response getResponse = getEmployeeSteps
                .getAddedEmployeeResponse(getEmployeeXML);

        getEmployeeSteps
                .checkStatusCode(getResponse, FAIL_CODE)
                .deserializeToGetEmployeeByIDResponse(getResponse);
        getEmployeeSteps.validateNullSourceMessage();
    }
}
