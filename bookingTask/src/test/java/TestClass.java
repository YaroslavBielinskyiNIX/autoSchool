import org.testng.annotations.Test;
import api.steps.LoginSteps;
import api.serializationClasses.BookRequestInfo;

public class TestClass {

    @Test
    public void authAsUserTest() {
        new LoginSteps().openLoginPage()
                .loginAsUser();
    }

    @Test
    public void bookRoomTest() {
        BookRequestInfo bookRequestInfo = new BookRequestInfo();

        new LoginSteps().openLoginPage()
                .loginAsUser()
                .bookRoom(bookRequestInfo)
                .deleteBook(bookRequestInfo);
    }
}