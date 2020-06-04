import org.testng.annotations.Test;
import steps.LoginSteps;
import steps.serializationClasses.BookRequestInfo;

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
