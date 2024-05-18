package tbc.tbcacademy.ge.data.steps.springsoapsteps;
import com.example.springboot.soap.interfaces.AddEmployeeRequest;
import com.example.springboot.soap.interfaces.AddEmployeeResponse;
import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.ObjectFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import tbc.tbcacademy.ge.data.helpers.SerializeDeserializeXml;
import java.math.BigDecimal;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.SUCCESS_ADD_MESSAGE;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.URI_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForSpringSoap;
import static tbc.tbcacademy.ge.data.helpers.HelperFunctions.getRandomGregorianFormatDate;

public class AddEmployeeSteps extends CommonSteps<AddEmployeeSteps> {
    private final RequestSpecification requestSpecification;
    private AddEmployeeResponse addEmployeeResponse;

    public AddEmployeeSteps() {
        requestSpecification = getRequestSpecForSpringSoap();
    }

    @Step("Create Add Employee Request with Random Faker Data")
    public AddEmployeeRequest createAddEmployeeRequest() {
        ObjectFactory objectFactory = new ObjectFactory();
        AddEmployeeRequest addEmployeeRequest = objectFactory.createAddEmployeeRequest();
        EmployeeInfo employeeInfo = new EmployeeInfo()
                .withAddress(faker.address().fullAddress())
                .withEmployeeId(faker.number().randomDigitNotZero())
                .withBirthDate(getRandomGregorianFormatDate())
                .withName(faker.name().firstName())
                .withEmail(faker.internet().emailAddress())
                .withSalary(BigDecimal.valueOf(faker.number().numberBetween(500, 1000)))
                .withDepartment(faker.company().catchPhrase())
                .withPhone(String.valueOf(faker.random().nextLong()));
        addEmployeeRequest.withEmployeeInfo(employeeInfo);
        return addEmployeeRequest;
    }
    @Step("Send Add Employee Request and Get Response")
    public Response addEmployeeAndGetResponse(String xml) {
        return given(requestSpecification)
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/addEmployeeRequest")
                .body(xml)
                .post(URI_ENDPOINT);
    }
    @Step("Deserialize Response into AddEmployeeResponse Object")
    public AddEmployeeSteps deserializeToAddEmployeeResponse(Response response) {
        addEmployeeResponse = SerializeDeserializeXml.unmarshallResponse(response.body().asString(), AddEmployeeResponse.class);
        return this;
    }
    @Step("Validate Add Employee Response Message for Success")
    public void validateAddResponseMessage() {
        assertThat(addEmployeeResponse.getServiceStatus().getMessage(), equalTo(SUCCESS_ADD_MESSAGE));
    }
}
