package steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class LoginPage {

    private static String bearerToken;
    private static Response response;

    @Step("Open login page")
    public LoginPage openLoginPage() {
        response = given()
                .get("/auth/realms/nix/protocol/openid-connect/auth?client_id=.net-test&response_type=code");

        response.then()
                .assertThat()
                .statusCode(200);

        return this;
    }

    @Step("Login as user")
    public LoginPage loginAsUser() {
        String loginUrl = response.htmlPath().getString("**.find {it.@id =='kc-form-login'}.@action");
        Map<String, String> cookie = response.getCookies();

        response = given().contentType(ContentType.URLENC)
                .body("username=keycloakuser&password=AqxFgK5aFPT3nT&credentialId=")
                .cookies(cookie)
                .post(loginUrl);

        response.then()
                .assertThat()
                .statusCode(302);

        return this;
    }

    @Step("Init Bearer Token")
    public LoginPage initBearerToken() {
        Matcher matcher = Pattern.compile(".{110}$").matcher(response.getHeader("Location"));

        String code = null;
        if (matcher.find( )) {
            code = matcher.group(0);
        }

//        given().get("https://booking.sharp.nixdev.co/main.dart.js?versionCode=1266");

//        Pattern pattern = Pattern.compile("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$");
//        Matcher matcher = pattern.matcher(loginResponse.asString());

        response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .body("grant_type=authorization_code&client_id=.net-test&client_secret=1498571f-70dc-4eb4-ad7e-490645862e94&code=" + code)
                .post("/auth/realms/nix/protocol/openid-connect/token");

        response.then()
                .assertThat()
                .statusCode(200);

        bearerToken = response.jsonPath()
                .getString("access_token");

        return this;
    }
}
