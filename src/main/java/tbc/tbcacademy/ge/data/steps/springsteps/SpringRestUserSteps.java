package tbc.tbcacademy.ge.data.steps.springsteps;
import io.qameta.allure.Step;
import lombok.Setter;
import spring.security.api.AuthenticationApi;
import spring.security.api.AuthorizationApi;
import spring.security.invoker.ApiClient;
import spring.security.model.AuthenticationRequest;
import spring.security.model.RefreshTokenRequest;
import spring.security.model.RegisterRequest;
import tbc.tbcacademy.ge.data.steps.CommonSteps;


public class SpringRestUserSteps extends CommonSteps {
    private RegisterRequest registerRequest;
    @Setter
    private String token;
    @Setter
    private String refreshToken;
    @Setter
    private ApiClient apiClient;

    @Step("Create user body using RegisterRequest model")
    public void createUser(String password) {
        registerRequest = new RegisterRequest()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(password)
                .role(RegisterRequest.RoleEnum.ADMIN);
    }
    @Step("Send user registration request")
    public AuthenticationApi.RegisterOper authenticateUser() {
        return apiClient
                .authentication()
                .register()
                .body(registerRequest);
    }
    @Step("Add Bearer token authorization to API requests")
    public AuthorizationApi addAuthorizationHeader() {
        return apiClient
                .authorization()
                .reqSpec(requestSpecBuilder -> requestSpecBuilder.addHeader("Authorization", "Bearer " + token));
    }
    @Step("Send authentication request with user credentials")
    public AuthenticationApi.AuthenticateOper authorize() {
        return apiClient
                .authentication()
                .authenticate()
                .body(new AuthenticationRequest()
                        .email(registerRequest.email())
                        .password(registerRequest.password()));
    }
    @Step("Request a new access token using refresh token")
    public AuthenticationApi.RefreshTokenOper refreshAccessToken() {
        return apiClient
                .authentication()
                .refreshToken()
                .body(new RefreshTokenRequest()
                        .refreshToken(refreshToken));
    }
}
