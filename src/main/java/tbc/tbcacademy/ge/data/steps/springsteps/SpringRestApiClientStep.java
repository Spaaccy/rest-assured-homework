package tbc.tbcacademy.ge.data.steps.springsteps;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import spring.security.invoker.ApiClient;
import spring.security.invoker.JacksonObjectMapper;
import static io.restassured.RestAssured.config;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static tbc.tbcacademy.ge.data.constants.SpringRestData.SPRING_REST_URI;

public class SpringRestApiClientStep {
    @Step("Create and configure ApiClient for Spring Rest API")
    public ApiClient getApiClientForSpringReset(){
        return ApiClient.api(ApiClient.Config.apiConfig()
                .reqSpecSupplier(() -> new RequestSpecBuilder()
//                        .log(LogDetail.ALL)
                        .setConfig(config().objectMapperConfig(objectMapperConfig()
                                .defaultObjectMapper(JacksonObjectMapper.jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .addFilter(new AllureRestAssured())
                        .setBaseUri(SPRING_REST_URI)));
    }
}
