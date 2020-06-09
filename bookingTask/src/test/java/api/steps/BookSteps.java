package api.steps;

import api.serializationClasses.EmailRemainder;
import api.serializationClasses.oneTime.OneTimeBookIdentity;
import api.serializationClasses.recurring.RecurringBookIdentity;
import api.serializationClasses.RoomsResponseData;
import api.serializationClasses.oneTime.OneTimeBookRequestInfo;
import api.serializationClasses.oneTime.OneTimeBookResponse;
import api.serializationClasses.oneTime.OneTimeBookResponseInfo;
import api.serializationClasses.recurring.request.RecurringDailyBookRequestInfo;
import api.serializationClasses.recurring.request.RecurringMonthlyBookRequestInfo;
import api.serializationClasses.recurring.request.RecurringWeeklyBookRequestInfo;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class BookSteps extends AuthorizedUserSteps {

    public BookSteps(String bearerToken) {
        super(bearerToken);
    }

    @Step("Book room one time")
    public BookSteps bookRoom(OneTimeBookRequestInfo oneTimeBookRequestInfo) {
        given().spec(requestBookSpec)
                .body(gson.toJson(oneTimeBookRequestInfo))
                .post(API_API + EVENTS)
                .then()
                .assertThat()
                .statusCode(200);

        verifyBookCreated(oneTimeBookRequestInfo);

        return this;
    }

    @Step("Book room recurring")
    public BookSteps bookRoom(RecurringDailyBookRequestInfo recurringDailyBookRequestInfo) {
        given().spec(requestBookSpec)
                .contentType("application/json; charset=utf-8")
                .body(gson.toJson(recurringDailyBookRequestInfo))
                .post(API_API + REC_EVENTS)
                .then()
                .assertThat()
                .statusCode(200);

        verifyBookCreated(recurringDailyBookRequestInfo);

        return this;
    }

    @Step("Verify one time book created")
    public BookSteps verifyBookCreated(OneTimeBookRequestInfo oneTimeBookRequestInfo) {
        List<OneTimeBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookIdentity.class, ObjectMapperType.GSON).getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getStartTime().equals(oneTimeBookRequestInfo.getData().getAttributes().getStartTime())).count(), is(1L));

        return this;
    }

    @Step("Verify recurring daily book created")
    public BookSteps verifyBookCreated(RecurringDailyBookRequestInfo recurringDailyBookRequestInfo) {
        List<RecurringBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + RECURRING_EVENTS + MY)
                .as(RecurringBookIdentity.class, ObjectMapperType.GSON)
                .getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getDateStart().equals(recurringDailyBookRequestInfo.getDateStart())).count(), is(1L));

        return this;
    }

    @Step("Verify recurring weekly book created")
    public void verifyBookCreated(RecurringWeeklyBookRequestInfo recurringWeeklyBookRequestInfo) {
        List<OneTimeBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookIdentity.class, ObjectMapperType.GSON).getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getStartTime().equals(recurringWeeklyBookRequestInfo.getDateStart())).count(), is(1L));
    }

    @Step("Verify recurring monthly book created")
    public void verifyBookCreated(RecurringMonthlyBookRequestInfo recurringMonthlyBookRequestInfo) {
        List<OneTimeBookIdentity.Data> books = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookIdentity.class, ObjectMapperType.GSON).getData();

        assertThat(books.stream().filter(book -> book.getAttributes().getStartTime().equals(recurringMonthlyBookRequestInfo.getDateStart())).count(), is(1L));
    }

    @Step("Delete one time book")
    public BookSteps deleteBook(OneTimeBookRequestInfo oneTimeBookRequestInfo) {
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

    @Step("Delete recurring daily book")
    public BookSteps deleteBook(RecurringDailyBookRequestInfo recurringDailyBookRequestInfo) {
        List<RecurringBookIdentity.Data> bookData = given().spec(requestBookSpec)
                .get(API_API + RECURRING_EVENTS + MY)
                .as(RecurringBookIdentity.class, ObjectMapperType.GSON)
                .getData();

        String bookId = requireNonNull(bookData.stream()
                .filter(data -> data.getAttributes().getStartTime().contains(recurringDailyBookRequestInfo.getTimeStart()))
                .findAny()
                .orElse(null))
                .getId();

        given().spec(requestBookSpec)
                .contentType("application/vnd.api+json")
                .basePath(API_API + REC_EVENTS)
                .delete('/' + bookId)
                .then()
                .assertThat()
                .statusCode(200);

        List<OneTimeBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookIdentity.class, ObjectMapperType.GSON)
                .getData();

        assertThat(booksStartTime.stream().filter(book -> book.getAttributes().getStartTime().equals(recurringDailyBookRequestInfo.getTimeStart())).count(), is(0L));

        return this;
    }

    @Step("Delete nearest recurring daily book")
    public BookSteps deleteNearestRecurringDailyBook(RecurringDailyBookRequestInfo recurringDailyBookRequestInfo) {
        List<RecurringBookIdentity.Data> bookData = given().spec(requestBookSpec)
                .get(API_API + EVENTS)
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
                .delete('/' + bookId)
                .then()
                .assertThat()
                .statusCode(200);

        List<RecurringBookIdentity.Data> booksStartTime = given().spec(requestBookSpec)
                .get(API_API + RECURRING_EVENTS + MY)
                .as(RecurringBookIdentity.class, ObjectMapperType.GSON)
                .getData();

        assertThat(booksStartTime.stream().filter(book -> book.getId().equals(bookId)).count(), is(0L));

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

        given().spec(requestBookSpec)
                .body(gson.toJson(oneTimeBookRequestInfo))
                .patch(API_API + EVENTS)
                .then()
                .assertThat()
                .statusCode(200);

        List<OneTimeBookResponse.Data> booksList = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookResponse.class, ObjectMapperType.GSON).getData();

        Long count = 0L;

        for (OneTimeBookResponse.Data book:booksList) {
            try {
                if (book.getAttributes().getTitle().equals(newTitle)) count++;
            } catch (NullPointerException ignored) {

            }
        }

        assertThat(count, is(1L));

        return this;
    }

    @Step("Add book email remainder")
    public BookSteps addEmailRemainder(OneTimeBookRequestInfo oneTimeBookRequestInfo) {
        EmailRemainder emailRemainder = new EmailRemainder();

        List<OneTimeBookResponse.Data> books = given().spec(requestBookSpec)
                .get(API_API + EVENTS + MY)
                .as(OneTimeBookResponse.class, ObjectMapperType.GSON).getData();

        for (OneTimeBookResponse.Data book:books) {
            if (book.getAttributes().getStartTime().equals(oneTimeBookRequestInfo.getData().getAttributes().getStartTime())) emailRemainder.setEventId(book.getAttributes().getId());
        }

        given().spec(requestBookSpec)
                .contentType("application/json; charset=utf-8")
                .body("{\"eventId\":" + emailRemainder.getEventId() + ",\"recEventId\":null,\"groupIds\":[],\"addresses\":[\"testee@gmail.com\"]}")
                .post(API_API + EMAIL_CREATE)
                .then()
                .assertThat()
                .statusCode(200);

        return this;
    }

    @Step("Get first match, size - {size} room")
    public OneTimeBookRequestInfo getRoomsWithSize(String size) {
        RoomsResponseData roomsResponseData = given().spec(requestBookSpec)
                .queryParam("include", "room-attributes,floor")
                .get(API_API + ROOMS)
                .as(RoomsResponseData.class, ObjectMapperType.GSON);

        String idRoom = requireNonNull(roomsResponseData.getData()
                .stream()
                .filter(data -> data.getAttributes().getSize().equals(size) && (data.getAttributes().getFloorId().equals("2") || data.getAttributes().getFloorId().equals("3")))
                .findFirst()
                .orElse(null))
                .getAttributes()
                .getId();

        OneTimeBookRequestInfo oneTimeBookRequestInfo = new OneTimeBookRequestInfo();
        oneTimeBookRequestInfo.getData().getRelationships().getRoom().getDData().setId(idRoom);

        return oneTimeBookRequestInfo;
    }
}
