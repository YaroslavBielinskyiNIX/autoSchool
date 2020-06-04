package api.steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
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
        response = given().filter(new AllureRestAssured())
                .baseUri(KEYCLOAK_NIXSOLUTIONS)
                .basePath("/auth/realms/nix/protocol/openid-connect/auth")
                .queryParam("client_id", ".net-test")
                .queryParam("response_type", "code")
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        return this;
    }

    @Step("Login as user")
    public BookSteps loginAsUser() {
        String loginUrl = response.htmlPath().getString("**.find {it.@id =='kc-form-login'}.@action");
        Map<String, String> cookie = response.getCookies();

        response = given().filter(new AllureRestAssured())
                .contentType(ContentType.URLENC)
                .baseUri(KEYCLOAK_NIXSOLUTIONS)
                .body("username=keycloakuser&password=AqxFgK5aFPT3nT&credentialId=")
                .cookies(cookie)
                .post(loginUrl)
                .then()
                .assertThat()
                .statusCode(302)
                .extract()
                .response();

        Matcher matcher = Pattern.compile(".{110}$").matcher(response.getHeader("Location"));
        String code = null;
        if (matcher.find( )) {
            code = matcher.group(0);
        }

        response = given().filter(new AllureRestAssured())
                .contentType(ContentType.URLENC)
                .baseUri(KEYCLOAK_NIXSOLUTIONS)
                .body("grant_type=authorization_code&client_id=.net-test&client_secret=1498571f-70dc-4eb4-ad7e-490645862e94&code=" + code)
                .post("/auth/realms/nix/protocol/openid-connect/token")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        return new BookSteps("Bearer " +  response.jsonPath().getString("access_token"));
    }
}
