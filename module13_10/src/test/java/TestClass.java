import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class TestClass {

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{{ "https://auto.ria.com/auto_mitsubishi_lancer_21990745.html" },
                { "https://auto.ria.com/auto_toyota_camry_22101863.html" },
                { "https://auto.ria.com/auto_bmw_520_21996828.html" },
                { "https://auto.ria.com/auto_nissan_leaf_22104675.html" },
                { "https://auto.ria.com/auto_nissan_leaf_22104647.html" },
                { "https://auto.ria.com/auto_mercedes_benz_e_220_21994701.html" },
                { "https://auto.ria.com/auto_lexus_nx_200_22083607.html" } };
    }

    @Test(dataProvider = "dataProvider")
    public void test(String url) {
        given().header("User-Agent", "Jmeter")
                .when()
                .get(url)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .header("Content-Type", StringMatcher.hasText("text/html"))
                .and()
                .header("Content-Encoding", "gzip");
    }
}
