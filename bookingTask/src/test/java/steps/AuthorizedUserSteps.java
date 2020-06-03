package steps;

import com.google.gson.Gson;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizedUserSteps {

    protected String bearerToken;
    protected Response response;
    protected Gson gson;
    protected RequestSpecification requestBookSpec;
    protected final String BOOKING_SHARP_NIXDEV_CO = "https://booking.sharp.nixdev.co";

    public AuthorizedUserSteps(String bearerToken) {
        this.bearerToken = bearerToken;

        gson = new Gson();
        requestBookSpec = new RequestSpecBuilder().addFilter(new AllureRestAssured())
                .setContentType("application/vnd.api+json; charset=utf-8")
                .addHeader("Authorization", bearerToken)
                .setBaseUri(BOOKING_SHARP_NIXDEV_CO)
                .build();
    }
}
