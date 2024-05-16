//package tbc.tbcacademy.ge.data.steps.petstoresteps;
//import io.qameta.allure.Step;
//import io.qameta.allure.restassured.AllureRestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.filter.log.ErrorLoggingFilter;
//import pet.store.v3.invoker.ApiClient;
//import pet.store.v3.invoker.JacksonObjectMapper;
//import static io.restassured.RestAssured.config;
//import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
//import static tbc.tbcacademy.ge.data.constants.PetStoreData.PET_STORE_V3_URI;
//
//public class PetStoreApiClientStep {
//    @Step("Create and configure ApiClient for Pet Store API v3")
//    public ApiClient getApiClientForPetStore(){
//        return ApiClient.api(ApiClient.Config.apiConfig()
//                .reqSpecSupplier(() -> new RequestSpecBuilder()
////                        .log(LogDetail.ALL)
//                        .setConfig(config().objectMapperConfig(objectMapperConfig()
//                                .defaultObjectMapper(JacksonObjectMapper.jackson())))
//                        .addFilter(new ErrorLoggingFilter())
//                        .addFilter(new AllureRestAssured())
//                        .setBaseUri(PET_STORE_V3_URI)));
//    }
//}
