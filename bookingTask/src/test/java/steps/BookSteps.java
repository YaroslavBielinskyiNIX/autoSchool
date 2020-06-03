package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.mapper.ObjectMapperType;
import steps.serializationClasses.BookRequestInfo;
import steps.serializationClasses.BookResponseInfo;

import static io.restassured.RestAssured.given;

public class BookSteps extends AuthorizedUserSteps {

    private final BookRequestInfo bookRequestInfo;
    private BookResponseInfo bookResponseInfo;

    public BookSteps(String token) {
        super(token);

        bookRequestInfo = new BookRequestInfo();
    }

    @Step("Book room")
    public BookSteps bookRoom() {
        response = given().spec(requestBookSpec)
                .body(gson.toJson(bookRequestInfo))
                .post("/api/api/events")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        return this;
    }

    @Step("Delete book")
    public BookSteps deleteBook() {
        bookResponseInfo = response.as(BookResponseInfo.class, ObjectMapperType.GSON);

        given().filter(new AllureRestAssured()).spec(requestBookSpec)
                .basePath("/api/api/events/")
                .delete(bookResponseInfo.getData().getAttributes().getId())
                .then()
                .assertThat()
                .statusCode(200);

        return this;
    }
}
