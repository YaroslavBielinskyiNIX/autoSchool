package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class LoginSteps extends BaseSteps {

    private BaseSteps baseSteps;

    public LoginSteps(BaseSteps baseSteps) {
        this.baseSteps = baseSteps;
    }

    @Step("Open login page")
    public LoginSteps openLoginPage() {
        baseSteps.response = given()
                .filter(new AllureRestAssured())
                .basePath("/auth/realms/nix/protocol/openid-connect/auth")
                .queryParam("client_id", ".net-test")
                .queryParam("response_type", "code")
                .get();

        baseSteps.response.then()
                .assertThat()
                .statusCode(200);

        return this;
    }

    @Step("Login as user")
    public LoginSteps loginAsUser() {
        String loginUrl = baseSteps.response.htmlPath().getString("**.find {it.@id =='kc-form-login'}.@action");
        Map<String, String> cookie = baseSteps.response.getCookies();

        baseSteps.response = given()
                .filter(new AllureRestAssured()).contentType(ContentType.URLENC)
                .body("username=keycloakuser&password=AqxFgK5aFPT3nT&credentialId=")
                .cookies(cookie)
                .post(loginUrl);

        baseSteps.response.then()
                .assertThat()
                .statusCode(302);

        return this;
    }

    @Step("Init Bearer Token")
    public BookSteps initBearerToken() {
        Matcher matcher = Pattern.compile(".{110}$").matcher(baseSteps.response.getHeader("Location"));

        String code = null;
        if (matcher.find( )) {
            code = matcher.group(0);
        }

        baseSteps.response = RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.URLENC)
                .body("grant_type=authorization_code&client_id=.net-test&client_secret=1498571f-70dc-4eb4-ad7e-490645862e94&code=" + code)
                .basePath("/auth/realms/nix/protocol/openid-connect/token")
                .post();

        baseSteps.response.then()
                .assertThat()
                .statusCode(200);

        baseSteps.bearerToken = "Bearer " +  baseSteps.response.jsonPath()
                .getString("access_token");

        return new BookSteps(baseSteps);
    }
}
