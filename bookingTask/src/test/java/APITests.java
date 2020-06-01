import io.restassured.RestAssured;
import io.restassured.config.XmlConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.xpath.XPath;

public class APITests {

    @Test
    public void authTest() {
        Response response = RestAssured.given()
                .get("https://keycloak.nixsolutions.com/auth/realms/nix/protocol/openid-connect/auth?client_id=.net-test&response_type=code");

        System.out.println(response.getBody().htmlPath().prettyPrint());

    }
}
