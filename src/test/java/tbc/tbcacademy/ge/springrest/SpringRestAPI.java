package tbc.tbcacademy.ge.springrest;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import spring.security.invoker.ApiClient;
import spring.security.model.*;
import tbc.tbcacademy.ge.data.dataproviders.DataProviders;
import tbc.tbcacademy.ge.data.steps.springsteps.SpringRestApiClientStep;
import tbc.tbcacademy.ge.data.steps.springsteps.SpringRestUserSteps;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static spring.security.invoker.ResponseSpecBuilders.shouldBeCode;
import static spring.security.invoker.ResponseSpecBuilders.validatedWith;
import static tbc.tbcacademy.ge.data.constants.SpringRestData.*;


@Epic("Spring REST API - User Authentication and Authorization")
public class SpringRestAPI {
    SpringRestApiClientStep springRestApiClientStep;
    private ApiClient apiClient;
    private SpringRestUserSteps springRestUserSteps;

    @BeforeSuite
    public void createApiClient(){
        springRestApiClientStep = new SpringRestApiClientStep();
        apiClient = springRestApiClientStep.getApiClientForSpringReset();
    }
    @BeforeClass
    public void beforeClass(){
        springRestUserSteps = new SpringRestUserSteps();
        springRestUserSteps.setApiClient(apiClient);
    }

    @Test(priority = 1, dataProviderClass = DataProviders.class, dataProvider = "passwordProvider", description = "User authentication using incorrect passwords")
    @Feature("User Authentication")
    @Severity(SeverityLevel.NORMAL)
    @Story("User cannot authenticate with an incorrect password")
    @Description("This test verifies that the system correctly rejects authentication " +
            "attempts with invalid passwords, ensuring security and preventing unauthorized access.")
    public void wrongPasswordTest(String password) {
        springRestUserSteps.createUser(password);
        springRestUserSteps
                .authenticateUser()
                .executeAs(validatedWith(shouldBeCode(SC_BAD_REQUEST)));
    }
    @Test(priority = 2, dependsOnMethods = "wrongPasswordTest", description = "User authentication using correct password")
    @Feature("User Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User can authenticate with a correct password")
    @Description("This test verifies that a user can successfully authenticate with a valid " +
            "email and password combination, receiving the expected access and refresh tokens.")
    void correctPasswordTest(){
        springRestUserSteps.createUser(CORRECT_PASSWORD);
        AuthenticationResponse registerResponse =
                springRestUserSteps.authenticateUser()
                        .executeAs(validatedWith(shouldBeCode(SC_OK)));

        springRestUserSteps.setToken(registerResponse.getAccessToken());
        springRestUserSteps.setRefreshToken(registerResponse.getRefreshToken());
    }
    @Test(priority = 3, dependsOnMethods = {"wrongPasswordTest", "correctPasswordTest"}, description = "Verify a user with a valid access token can access admin features")
    @Feature("Authorization & Access Control")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User with valid access token and admin role can access protected resource")
    @Description("This test verifies that a user with a valid access token and the 'admin' role, along with the 'read' " +
            "authority, can successfully access a protected resource. It ensures proper authorization checks and access control mechanisms are in place.")
    void accessTokenTest() {
        String authorizationResponse = springRestUserSteps
                .addAuthorizationHeader()
                .sayHelloWithRoleAdminAndReadAuthority()
                .execute(validatedWith(shouldBeCode(SC_OK)))
                .asString();

        assertThat(authorizationResponse, equalTo(AUTHORIZATION_ADMIN_RESPONSE_TEXT));
    }
    @Test(priority = 4, dependsOnMethods = {"wrongPasswordTest", "correctPasswordTest", "accessTokenTest"}, description = "verify authenticated user can successfully re-authenticate")
    @Feature("User Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User can re-authenticate with existing credentials")
    @Description("This test verifies that a previously authenticated user can successfully re-authenticate" +
            " using their stored credentials (email and password) and that the correct set of user roles/privileges is returned in the response.")
    void authenticateTest() {
        AuthenticationResponse authResponse = springRestUserSteps
                .authorize()
                .executeAs(validatedWith(shouldBeCode(SC_OK)));

        assertThat(authResponse.getRoles(), containsInAnyOrder(ACCOUNT_PRIVILEGES));
    }
    @Test(priority = 5, dependsOnMethods = {"wrongPasswordTest", "correctPasswordTest", "accessTokenTest", "authenticateTest"}, description = "verify a user can successfully obtain a new access token")
    @Feature("User Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User can refresh their access token")
    @Description("This test verifies that a user with a valid refresh token can successfully obtain a new access token, " +
            "ensuring continuous access to the application without requiring re-authentication.")
    void refreshToken() {
        springRestUserSteps.
                refreshAccessToken()
                .executeAs(validatedWith(shouldBeCode(SC_OK)));
    }
    @Test(priority = 6, dependsOnMethods = {"wrongPasswordTest", "correctPasswordTest", "accessTokenTest", "authenticateTest", "refreshToken"}, description = "verify that after successfully refreshing an access token," +
            "a user can still access a protected resource")
    @Feature("Authorization & Access Control")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User can access protected resource with refreshed access token")
    @Description("This test verifies that after successfully refreshing an access token, " +
            "a user can still access a protected resource using the new token. It ensures that the refresh token mechanism is working correctly and that authorization remains intact after token renewal.")
    void accessTokenTestAfterRefreshToken() {
        springRestUserSteps
                .addAuthorizationHeader()
                .sayHelloWithRoleAdminAndReadAuthority()
                .execute(validatedWith(shouldBeCode(SC_OK)));
    }
}
