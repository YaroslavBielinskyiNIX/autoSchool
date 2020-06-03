package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BookSteps extends BaseSteps {

    private BaseSteps baseSteps;

    public BookSteps(BaseSteps baseSteps) {
        this.baseSteps = baseSteps;
    }

    @Step("Book room")
    public BookSteps bookRoom() {
        RestAssured.baseURI = "https://booking.sharp.nixdev.co";

        JSONObject jsonObj = new JSONObject().put("data", new JSONObject()
                            .put("attributes", new JSONObject()
                                .put("description", "")
                                .put("end-time", "2020-06-14T10:05:00")
                                .put("id", "")
                                .put("recurring-event-id", "")
                                .put("start-time", "2020-06-14T09:05:00")
                                .put("title", ""))
                            .put("id", "")
                            .put("relationships", new JSONObject()
                                    .put("room", new JSONObject()
                                        .put("data", new JSONObject()
                                            .put("id", "9")
                                            .put("type", "rooms"))))
                            .put("type", "events"));

        baseSteps.response = given()
                .filter(new AllureRestAssured())
                .contentType("application/vnd.api+json; charset=utf-8")
                .header("Authorization", baseSteps.bearerToken)
                .body(jsonObj.toString())
                .post("/api/api/events");

        baseSteps.response.then()
                .assertThat()
                .statusCode(200);

        return this;
    }

    @Step("Delete book")
    public BookSteps deleteBook() {
        JSONObject object = new JSONObject(baseSteps.response.asString());

        given()
                .filter(new AllureRestAssured())
                .header("Authorization", baseSteps.bearerToken)
                .basePath("/api/api/events/")
                .delete(object.getJSONObject("data").getJSONObject("attributes").get("id").toString())
                .then()
                .assertThat()
                .statusCode(200);

        return this;
    }
}
