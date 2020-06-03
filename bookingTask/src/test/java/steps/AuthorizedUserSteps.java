package steps;

import com.google.gson.Gson;
import io.restassured.response.Response;

public class AuthorizedUserSteps {

    protected String bearerToken;
    protected Response response;
    protected Gson gson;

    protected final String BOOKING_SHARP_NIXDEV_CO = "https://booking.sharp.nixdev.co";

    public AuthorizedUserSteps(String bearerToken) {
        this.bearerToken = bearerToken;
    }
}
