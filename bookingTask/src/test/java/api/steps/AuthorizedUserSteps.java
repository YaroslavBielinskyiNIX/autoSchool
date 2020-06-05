package api.steps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
}
