package steps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.mapper.ObjectMapperType;

import static io.restassured.RestAssured.given;

public class BookSteps extends AuthorizedUserSteps {

    private final GsonBuilder builder;
    private Gson gson;
    private final String bookInfoString;
    BookRequestInfo bookRequestInfo;
    BookResponseInfo bookResponseInfo;

    public BookSteps(String token) {
        super(token);

        builder = new GsonBuilder();
        gson = builder.create();
        bookInfoString = "{\"data\":{\"relationships\":{\"room\":{\"data\":{\"id\":\"9\",\"type\":\"rooms\"}}},\"attributes\":{\"start-time\":\"2020-06-16T09:05:00\",\"end-time\":\"2020-06-16T10:05:00\",\"description\":\"\",\"id\":\"\",\"title\":\"\",\"recurring-event-id\":\"\"},\"id\":\"\",\"type\":\"events\"}}\n";
        bookRequestInfo = gson.fromJson(bookInfoString, BookRequestInfo.class);
    }

    @Step("Book room")
    public BookSteps bookRoom() {
        response = given()
                .filter(new AllureRestAssured())
                .baseUri(BOOKING_SHARP_NIXDEV_CO)
                .contentType("application/vnd.api+json; charset=utf-8")
                .header("Authorization", bearerToken)
                .body(gson.toJson(bookRequestInfo))
                .post("/api/api/events");

        response.then()
                .assertThat()
                .statusCode(200);

        return this;
    }

    @Step("Delete book")
    public BookSteps deleteBook() {
        bookResponseInfo = response.as(BookResponseInfo.class, ObjectMapperType.GSON);

        given()
                .filter(new AllureRestAssured())
                .baseUri(BOOKING_SHARP_NIXDEV_CO)
                .header("Authorization", bearerToken)
                .basePath("/api/api/events/")
                .delete(bookResponseInfo.getData().getAttributes().getId())
                .then()
                .assertThat()
                .statusCode(200);

        return this;
    }
}
