import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class APITests {

    @Test
    public void authTest() {

        Response response = RestAssured.given()
                .get("https://keycloak.nixsolutions.com/auth/realms/nix/protocol/openid-connect/auth?client_id=.net-test&response_type=code");

        String url = response.htmlPath()
                .getString("**.find {it.@id =='kc-form-login'}.@action");

        RestAssured.given().body("username=keycloakuser&password=AqxFgK5aFPT3nT&credentialId=").cookies(response.getCookies()).post(url);
        RestAssured.given().body("username=keycloakuser&password=AqxFgK5aFPT3nT&credentialId=").cookies(response.getCookies()).post(url).then().assertThat().statusCode(302);

    }
}
