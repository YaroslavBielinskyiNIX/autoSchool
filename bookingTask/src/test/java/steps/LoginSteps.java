package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class LoginSteps {
    private Response response;
    private final String KEYCLOAK_NIXSOLUTIONS = "https://keycloak.nixsolutions.com";

    @Step("Open login page")
    public LoginSteps openLoginPage() {
        response = given()
                .filter(new AllureRestAssured())
                .baseUri(KEYCLOAK_NIXSOLUTIONS)
                .basePath("/auth/realms/nix/protocol/openid-connect/auth")
                .queryParam("client_id", ".net-test")
                .queryParam("response_type", "code")
                .get();

        response.then()
                .assertThat()
                .statusCode(200);

        return this;
    }

    @Step("Login as user")
    public LoginSteps loginAsUser() {
        String loginUrl = response.htmlPath().getString("**.find {it.@id =='kc-form-login'}.@action");
        Map<String, String> cookie = response.getCookies();

        response = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.URLENC)
                .baseUri(KEYCLOAK_NIXSOLUTIONS)
                .body("username=keycloakuser&password=AqxFgK5aFPT3nT&credentialId=")
                .cookies(cookie)
                .post(loginUrl);

        response.then()
                .assertThat()
                .statusCode(302);

        return this;
    }

    @Step("Init Bearer Token")
    public BookSteps initBearerToken() {
        Matcher matcher = Pattern.compile(".{110}$").matcher(response.getHeader("Location"));

        String code = null;
        if (matcher.find( )) {
            code = matcher.group(0);
        }

        response = RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.URLENC)
                .baseUri(KEYCLOAK_NIXSOLUTIONS)
                .body("grant_type=authorization_code&client_id=.net-test&client_secret=1498571f-70dc-4eb4-ad7e-490645862e94&code=" + code)
                .post("/auth/realms/nix/protocol/openid-connect/token");

        response.then()
                .assertThat()
                .statusCode(200);

        String bearerToken = "Bearer " +  response.jsonPath()
                .getString("access_token");

        return new BookSteps(bearerToken);
    }
}
