package tbc.tbcacademy.ge.data.steps.springsoapsteps;
import com.example.springboot.soap.interfaces.DeleteEmployeeRequest;
import com.example.springboot.soap.interfaces.DeleteEmployeeResponse;
import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.ObjectFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Setter;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import tbc.tbcacademy.ge.data.helpers.SerializeDeserializeXml;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.SUCCESS_DELETE_MESSAGE;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.URI_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForSpringSoap;

public class DeleteEmployeeSteps extends CommonSteps<DeleteEmployeeSteps> {
    private final RequestSpecification requestSpecification;
    @Setter
    private EmployeeInfo employeeInfo;
    private DeleteEmployeeResponse deleteEmployeeResponse;
    public DeleteEmployeeSteps() {
        requestSpecification = getRequestSpecForSpringSoap();
    }
    @Step("Create Delete Employee Request (Using Employee ID from Previous Add)")
    public DeleteEmployeeRequest createDeleteEmployeeRequest() {
        ObjectFactory objectFactory = new ObjectFactory();
        return objectFactory.createDeleteEmployeeRequest()
                .withEmployeeId(employeeInfo.getEmployeeId());
    }
    @Step("Send Delete Employee Request and Get Response")
    public Response deleteEmployeeAndGetResponse(String xml) {
        return given(requestSpecification)
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/deleteEmployeeRequest")
                .body(xml)
                .post(URI_ENDPOINT);
    }
    @Step("Deserialize Response into DeleteEmployeeResponse Object")
    public DeleteEmployeeSteps deserializeToDeleteEmployeeResponse(Response response) {
        deleteEmployeeResponse = SerializeDeserializeXml.unmarshallResponse(response.body().asString(), DeleteEmployeeResponse.class);
        return this;
    }
    @Step("Validate Delete Employee Response Message for Success")
    public void validateDeleteResponseMessage() {
        assertThat(deleteEmployeeResponse.getServiceStatus().getMessage(), equalTo(SUCCESS_DELETE_MESSAGE));
    }
}
