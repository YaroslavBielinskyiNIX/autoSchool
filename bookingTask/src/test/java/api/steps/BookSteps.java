package api.steps;

import api.serializationClasses.OneTimeBookIdentity;
import api.serializationClasses.RecurringBookIdentity;
import api.serializationClasses.oneTime.OneTimeBookRequestInfo;
import api.serializationClasses.oneTime.OneTimeBookResponse;
import api.serializationClasses.oneTime.OneTimeBookResponseInfo;
import api.serializationClasses.recurring.request.RecurringDailyBookRequestInfo;
import api.serializationClasses.recurring.request.RecurringMonthlyBookRequestInfo;
import api.serializationClasses.recurring.request.RecurringWeeklyBookRequestInfo;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import java.util.EventListener;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookSteps extends AuthorizedUserSteps {

    public BookSteps(String token) {
        super(token);
    }

    @Step("Verify one time book created")
    public BookSteps verifyOneTimeBookCreated(OneTimeBookRequestInfo oneTimeBookRequestInfo) {
        List<OneTimeBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookIdentity.class, ObjectMapperType.GSON).getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getStartTime().equals(oneTimeBookRequestInfo.getData().getAttributes().getStartTime())).count(), is(1L));

        return this;
    }

    @Step("Verify recurring daily book created")
    public BookSteps verifyRecurringDailyBookCreated(RecurringDailyBookRequestInfo recurringDailyBookRequestInfo) {
        List<RecurringBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + RECURRING_EVENTS + MY)
                .as(RecurringBookIdentity.class, ObjectMapperType.GSON)
                .getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getDateStart().equals(recurringDailyBookRequestInfo.getDateStart())).count(), is(1L));

        return this;
    }

    @Step("Verify recurring weekly book created")
    public void verifyRecurringWeeklyBookCreated(RecurringWeeklyBookRequestInfo recurringWeeklyBookRequestInfo) {
        List<OneTimeBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookIdentity.class, ObjectMapperType.GSON).getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getStartTime().equals(recurringWeeklyBookRequestInfo.getDateStart())).count(), is(1L));
    }

    @Step("Verify recurring monthly book created")
    public void verifyRecurringMonthlyBookCreated(RecurringMonthlyBookRequestInfo recurringMonthlyBookRequestInfo) {
        List<OneTimeBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookIdentity.class, ObjectMapperType.GSON).getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getStartTime().equals(recurringMonthlyBookRequestInfo.getDateStart())).count(), is(1L));
    }

    @Step("Book room one time")
    public BookSteps bookOneTimeRoom(OneTimeBookRequestInfo oneTimeBookRequestInfo) {
        given().spec(requestBookSpec)
                .body(gson.toJson(oneTimeBookRequestInfo))
                .post(API_API + EVENTS)
                .then()
                .assertThat()
                .statusCode(200);

        verifyOneTimeBookCreated(oneTimeBookRequestInfo);

        return this;
    }

    @Step("Delete one time book")
    public BookSteps deleteOneTimeBook(OneTimeBookRequestInfo oneTimeBookRequestInfo) {
        List<OneTimeBookResponseInfo.Data> bookData = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookResponseInfo.class, ObjectMapperType.GSON)
                .getData();

        String bookId = requireNonNull(bookData.stream()
                .filter(data -> data.getAttributes().getStartTime().equals(oneTimeBookRequestInfo.getData().getAttributes().getStartTime()))
                .findFirst()
                .orElse(null))
                .getAttributes()
                .getId();

        assertThat(bookId, is(notNullValue()));

        given().spec(requestBookSpec)
                .basePath(API_API + EVENTS)
                .delete(bookId)
                .then()
                .assertThat()
                .statusCode(200);

        List<OneTimeBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookIdentity.class, ObjectMapperType.GSON)
                .getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getStartTime().equals(oneTimeBookRequestInfo.getData().getAttributes().getStartTime())).count(), is(0L));

        return this;
    }

    @Step("Delete nearest recurring daily book")
    public BookSteps deleteNearestRecurringDailyBook(RecurringDailyBookRequestInfo recurringDailyBookRequestInfo) {
        List<RecurringBookIdentity.Data> bookData = given().spec(requestBookSpec)
                .get(API_API + EVENTS + "/my")
                .as(RecurringBookIdentity.class, ObjectMapperType.GSON)
                .getData();

        String bookId = requireNonNull(bookData.stream()
                .filter(data -> data.getAttributes().getStartTime().contains(recurringDailyBookRequestInfo.getTimeStart()))
                .findFirst()
                .orElse(null))
                .getId();

        assertThat(bookId, is(notNullValue()));

        given().spec(requestBookSpec)
                .basePath(API_API + EVENTS)
                .delete(bookId)
                .then()
                .assertThat()
                .statusCode(200);

        List<RecurringBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(RecurringBookIdentity.class, ObjectMapperType.GSON)
                .getData();

        assertThat(booksStartTime.stream().filter(book -> book.getId().equals(bookId)).count(), is(0L));

        return this;
    }

    @Step("Book room recurring")
    public BookSteps BookRoomRecurring(RecurringDailyBookRequestInfo recurringDailyBookRequestInfo) {
        given().spec(requestBookSpec)
                .contentType("application/json; charset=utf-8")
                .body(gson.toJson(recurringDailyBookRequestInfo))
                .post(API_API + REC_EVENTS)
                .then()
                .assertThat()
                .statusCode(200);

        verifyRecurringDailyBookCreated(recurringDailyBookRequestInfo);

        return this;
    }

    @Step("Change one time book title")
    public BookSteps changeTitle(OneTimeBookRequestInfo oneTimeBookRequestInfo, String newTitle) {
        List<OneTimeBookResponse.Data> books = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookResponse.class, ObjectMapperType.GSON).getData();

        for (OneTimeBookResponse.Data book:books) {
            if (book.getAttributes().getStartTime().equals(oneTimeBookRequestInfo.getData().getAttributes().getStartTime())) oneTimeBookRequestInfo.getData().getAttributes().setId(book.getAttributes().getId());
        }

        oneTimeBookRequestInfo.getData()
                .getAttributes()
                .setTitle(newTitle);

        System.out.println(gson.toJson(oneTimeBookRequestInfo));

        given().spec(requestBookSpec)
                .body(gson.toJson(oneTimeBookRequestInfo))
                .patch(API_API + EVENTS)
                .then()
                .assertThat()
                .statusCode(200);

        List<OneTimeBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookIdentity.class, ObjectMapperType.GSON).getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getTitle().equals(newTitle)).count(), is(1L));

        return this;
    }
}
