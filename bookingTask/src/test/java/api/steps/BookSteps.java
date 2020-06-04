package api.steps;

import api.serializationClasses.BookIdentity;
import api.serializationClasses.BookRequestInfo;
import api.serializationClasses.BookResponseInfo;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class BookSteps extends AuthorizedUserSteps {

    public BookSteps(String token) {
        super(token);
    }

    @Step("Book room")
    public BookSteps bookRoom(BookRequestInfo bookRequestInfo) {
        given().spec(requestBookSpec)
                .body(gson.toJson(bookRequestInfo))
                .post(API_API_EVENTS)
                .then()
                .assertThat()
                .statusCode(200);

        response = given().spec(requestBookSpec)
                .get(API_API_EVENTS + "/my")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        List<BookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API_EVENTS + "/my")
                .as(BookIdentity.class, ObjectMapperType.GSON).getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getStartTime().equals(bookRequestInfo.getData().getAttributes().getStartTime())).count(), is(1L));

        return this;
    }

    @Step("Delete book")
    public BookSteps deleteBook(BookRequestInfo bookRequestInfo) {
        List<BookResponseInfo.Data> bookData = given().spec(requestBookSpec)
                .get(API_API_EVENTS + "/my")
                .as(BookResponseInfo.class, ObjectMapperType.GSON)
                .getData();

       String bookId = requireNonNull(bookData.stream()
               .filter(data -> data.getAttributes().getStartTime().equals(bookRequestInfo.getData().getAttributes().getStartTime()))
               .findFirst()
               .orElse(null))
               .getAttributes()
               .getId();

        assertThat(bookId, is(notNullValue()));

        given().spec(requestBookSpec)
                .basePath(API_API_EVENTS)
                .delete(bookId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        given().spec(requestBookSpec)
                .get(API_API_EVENTS + "/my")
                .then()
                .assertThat()
                .statusCode(200);

        List<BookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API_EVENTS + "/my")
                .as(BookIdentity.class, ObjectMapperType.GSON)
                .getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getStartTime().equals(bookRequestInfo.getData().getAttributes().getStartTime())).count(), is(0L));

        return this;
    }
}
