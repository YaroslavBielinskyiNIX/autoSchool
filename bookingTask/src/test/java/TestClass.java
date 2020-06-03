import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.testng.annotations.Test;
import steps.BookRequestInfo;
import steps.BookResponseInfo;
import steps.LoginSteps;

public class TestClass {

    @Test
    public void authAsUserTest() {
        new LoginSteps().openLoginPage()
                .loginAsUser()
                .initBearerToken();
    }

    @Test
    public void bookRoomTest() {
        new LoginSteps().openLoginPage()
                .loginAsUser()
                .initBearerToken()
                .bookRoom()
                .deleteBook();
    }
}
