package tbc.tbcacademy.ge.data.steps.springsoapsteps;
import com.example.springboot.soap.interfaces.EmployeeInfo;
import com.example.springboot.soap.interfaces.GetEmployeeByIdRequest;
import com.example.springboot.soap.interfaces.GetEmployeeByIdResponse;
import com.example.springboot.soap.interfaces.ObjectFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import tbc.tbcacademy.ge.data.steps.CommonSteps;
import tbc.tbcacademy.ge.data.helpers.SerializeDeserializeXml;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.NULL_SOURCE_MESSAGE;
import static tbc.tbcacademy.ge.data.constants.SpringSoapData.URI_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getRequestSpecForSpringSoap;

public class GetEmployeeSteps extends CommonSteps<GetEmployeeSteps> {
    @Setter
    @Getter
    private EmployeeInfo employeeInfo;
    private final RequestSpecification requestSpecification;
    private String nullSourceErrorMessage;

    public GetEmployeeSteps() {
        requestSpecification = getRequestSpecForSpringSoap();
    }
    @Step("Create Get Employee Request (Using Previously Added Employee ID)")
    public GetEmployeeByIdRequest createGetEmployeeRequest() {
        ObjectFactory objectFactory = new ObjectFactory();
        return objectFactory.createGetEmployeeByIdRequest()
                .withEmployeeId(employeeInfo.getEmployeeId());
    }
    @Step("Send Get Employee Request (By ID) and Retrieve Response")
    public Response getAddedEmployeeResponse(String xml) {
        return given(requestSpecification)
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/getEmployeeByIdRequest")
                .body(xml)
                .post(URI_ENDPOINT);
    }
    @Step("Deserialize Response to GetEmployeeByIdResponse or Capture Error")
    public GetEmployeeByIdResponse deserializeToGetEmployeeByIDResponse(Response response) {
        GetEmployeeByIdResponse getEmployeeByIdResponse = null;
        try {
            return getEmployeeByIdResponse = SerializeDeserializeXml
                    .unmarshallResponse(response.body().asString(), GetEmployeeByIdResponse.class);
        } catch (IllegalArgumentException e) {
            nullSourceErrorMessage = e.getMessage();
        }
        return getEmployeeByIdResponse;
    }
    @Step("Validate null source message")
    public void validateNullSourceMessage() {
        assertThat(nullSourceErrorMessage, equalTo(NULL_SOURCE_MESSAGE));
    }
}
