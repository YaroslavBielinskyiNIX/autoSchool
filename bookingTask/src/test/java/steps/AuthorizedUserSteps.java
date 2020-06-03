package steps;

import io.restassured.response.Response;

public class AuthorizedUserSteps {

    protected String bearerToken;
    protected Response response;

    protected final String BOOKING_SHARP_NIXDEV_CO = "https://booking.sharp.nixdev.co";

    public AuthorizedUserSteps(String bearerToken) {
        this.bearerToken = bearerToken;
    }
}
