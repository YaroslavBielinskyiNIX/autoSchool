import org.testng.annotations.Test;
import steps.LoginSteps;

public class TestClass extends BaseTest {

    @Test
    public void authAsUserTest() {
        new LoginSteps(baseSteps).openLoginPage()
                .loginAsUser()
                .initBearerToken();
    }

    @Test
    public void bookRoomTest() {
        new LoginSteps(baseSteps).openLoginPage()
                .loginAsUser()
                .initBearerToken()
                .bookRoom()
                .deleteBook();
    }
}
