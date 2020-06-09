package api.steps;

import api.serializationClasses.userInfo.UserInfoRequest;
import api.serializationClasses.userInfo.UserInfoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthorizedUserSteps {

    protected String bearerToken;
    protected Response response;
    protected Gson gson;
    protected RequestSpecification requestBookSpec;
    GsonBuilder builder;

    protected final String BOOKING_SHARP_NIXDEV_CO = "https://booking.sharp.nixdev.co";

    protected final String API_API = "/api/api";
    protected final String EVENTS = "/events";
    protected final String REC_EVENTS = "/rec-events";
    protected final String RECURRING_EVENTS = "/recurring-events";
    protected final String MY = "/my";
    protected final String ME = "/me";
    protected final String EMAIL_CREATE = "/email/create";
    protected final String USERS = "/users";
    protected final String ROOMS = "/rooms";

    public AuthorizedUserSteps(String bearerToken) {
        this.bearerToken = bearerToken;

        builder = new GsonBuilder().serializeNulls()
                .setPrettyPrinting();
        gson = builder.create();
        requestBookSpec = new RequestSpecBuilder().addFilter(new AllureRestAssured())
                .setContentType("application/vnd.api+json; charset=utf-8")
                .addHeader("Authorization", bearerToken)
                .setBaseUri(BOOKING_SHARP_NIXDEV_CO)
                .build();
    }

    @Step("Set favourite building")
    public AuthorizedUserSteps setFavouriteBuilding(int buildingId) {
        UserInfoResponse userInfoResponse = given().spec(requestBookSpec)
                .get(API_API + USERS + ME)
                .as(UserInfoResponse.class, ObjectMapperType.GSON);

        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.getData().getAttributes().setId(userInfoResponse.getData().get(0).getAttributes().getId());
        userInfoRequest.getData().getAttributes().setFavoriteBuildingId(buildingId);
        userInfoRequest.getData().setId(userInfoResponse.getData().get(0).getId());
        userInfoRequest.getData().setType(userInfoResponse.getData().get(0).getType());

        given().spec(requestBookSpec)
                .body(gson.toJson(userInfoRequest))
                .patch(API_API + USERS +  '/' + userInfoRequest.getData().getId())
                .then()
                .assertThat()
                .statusCode(200);

        userInfoResponse = given().spec(requestBookSpec)
                .get(API_API + USERS + ME)
                .as(UserInfoResponse.class, ObjectMapperType.GSON);

        assertThat(userInfoResponse.getData().get(0).getAttributes().getFavoriteBuildingId(), is(buildingId));

        return this;
    }
}
