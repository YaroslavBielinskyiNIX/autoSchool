import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.LoginPage;

@Listeners(LogListener.class)
public class LoginTests extends BaseTest {

    @Test
    public void authAsUserTest() {
        new LoginPage().openLoginPage()
                .loginAsUser()
                .initBearerToken();
    }
}
