import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import steps.BaseSteps;

public class BaseTest {

    protected BaseSteps baseSteps;

    @BeforeTest
    public void init() {
        RestAssured.baseURI = "https://keycloak.nixsolutions.com";
        baseSteps = new BaseSteps();
    }

}
