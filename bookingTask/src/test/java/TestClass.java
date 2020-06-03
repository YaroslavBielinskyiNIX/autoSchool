import org.testng.annotations.Test;
import steps.LoginSteps;

public class TestClass {

    @Test
    public void authAsUserTest() {
        new LoginSteps().openLoginPage()
                .loginAsUser();
    }

    @Test
    public void bookRoomTest() {
        new LoginSteps().openLoginPage()
                .loginAsUser()
                .bookRoom()
                .deleteBook();
    }
}
