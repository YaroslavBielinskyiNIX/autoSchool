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

    @Test
    public void test() {
        JSONObject jsonObj = new JSONObject().put("data", new JSONObject()
                .put("attributes", new JSONObject()
                        .put("description", "")
                        .put("end-time", "2020-06-16T10:05:00")
                        .put("id", "")
                        .put("recurring-event-id", "")
                        .put("start-time", "2020-06-16T09:05:00")
                        .put("title", ""))
                .put("id", "")
                .put("relationships", new JSONObject()
                        .put("room", new JSONObject()
                                .put("data", new JSONObject()
                                        .put("id", "9")
                                        .put("type", "rooms"))))
                .put("type", "events"));

        String string = "{\"data\":{\"relationships\":{\"room\":{\"data\":{\"id\":\"9\",\"type\":\"rooms\"}}},\"attributes\":{\"start-time\":\"2020-06-16T09:05:00\",\"end-time\":\"2020-06-16T10:05:00\",\"description\":\"\",\"id\":\"\",\"title\":\"\",\"recurring-event-id\":\"\"},\"id\":\"\",\"type\":\"events\"}}\n";

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        BookRequestInfo bookRequestInfo = gson.fromJson(string, BookRequestInfo.class);
        System.out.println(gson.toJson(bookRequestInfo));
    }
}
