package steps;

import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import steps.serializationClasses.BookRequestInfo;
import steps.serializationClasses.BookResponseInfo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertFalse;

public class BookSteps extends AuthorizedUserSteps {

    public BookSteps(String token) {
        super(token);
    }

    @Step("Book room")
    public BookSteps bookRoom(BookRequestInfo bookRequestInfo) {
        given().spec(requestBookSpec)
                .body(gson.toJson(bookRequestInfo))
                .post("/api/api/events")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        given().spec(requestBookSpec)
                .get("/api/api/events/my")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .toString()
                .contains(bookRequestInfo.getData().getAttributes().getStartTime());

        return this;
    }

    @Step("Delete book")
    public BookSteps deleteBook(BookRequestInfo bookRequestInfo) {
        String bookId = null;

        List<BookResponseInfo.Data> bookData = given().spec(requestBookSpec)
                .get("/api/api/events/my")
                .as(BookResponseInfo.class, ObjectMapperType.GSON)
                .getData();

        for (BookResponseInfo.Data data:bookData) {
            if (data.getAttributes().getStartTime().equals(bookRequestInfo.getData().getAttributes().getStartTime())) bookId = data.getAttributes().getId();
        }

        response = given().spec(requestBookSpec)
                .basePath("/api/api/events/")
                .delete(bookId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        assertFalse(response.then().assertThat().toString().contains(bookRequestInfo.getData().getAttributes().getStartTime()));

        return this;
    }
}
