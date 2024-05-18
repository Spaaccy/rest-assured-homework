package tbc.tbcacademy.ge.data.steps.springsoapsteps;
import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.ObjectFactory;
import com.example.springboot.soap.interfaces.UpdateEmployeeRequest;
import com.example.springboot.soap.interfaces.UpdateEmployeeResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Setter;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import tbc.tbcacademy.ge.data.helpers.SerializeDeserializeXml;
import java.math.BigDecimal;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.SUCCESS_UPDATE_MESSAGE;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.URI_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForSpringSoap;
import static tbc.tbcacademy.ge.data.helpers.HelperFunctions.getRandomGregorianFormatDate;

public class UpdateEmployeeSteps extends CommonSteps<UpdateEmployeeSteps> {
    private final RequestSpecification requestSpecification;
    @Setter
    private long EmployeeID;
    private UpdateEmployeeResponse updateEmployeeResponse;
    public UpdateEmployeeSteps() {
        requestSpecification = getRequestSpecForSpringSoap();
    }
    @Step("Create Update Employee Request (with New Random Faker Data)")
    public UpdateEmployeeRequest createUpdateEmployeeRequest() {
        ObjectFactory objectFactory = new ObjectFactory();
        UpdateEmployeeRequest updateEmployeeRequest = objectFactory.createUpdateEmployeeRequest();
        EmployeeInfo employeeInfo = objectFactory.createEmployeeInfo()
                .withEmployeeId(EmployeeID)
                .withName(faker.name().firstName())
                .withDepartment(faker.internet().domainName())
                .withAddress(faker.address().streetAddress())
                .withSalary(BigDecimal.valueOf(faker.number().numberBetween(100, 1000)))
                .withEmail(faker.internet().emailAddress())
                .withBirthDate(getRandomGregorianFormatDate());
        updateEmployeeRequest.withEmployeeInfo(employeeInfo);
        return updateEmployeeRequest;
    }
    @Step("Send Update Employee Request and Get Response")
    public Response updateEmployeeAndGetResponse(String xml) {
        return given(requestSpecification)
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/updateEmployeeRequest")
                .body(xml)
                .post(URI_ENDPOINT);
    }
    @Step("Deserialize Response into UpdateEmployeeResponse Object")
    public UpdateEmployeeSteps deserializeToUpdateEmployeeResponse(Response response) {
        updateEmployeeResponse = SerializeDeserializeXml.unmarshallResponse(response.body().asString(), UpdateEmployeeResponse.class);
        return this;
    }
    @Step("Validate Update Employee Response Message for Success")
    public void validateUpdateResponseMessage() {
        assertThat(updateEmployeeResponse.getServiceStatus().getMessage(), equalTo(SUCCESS_UPDATE_MESSAGE));
    }
}
