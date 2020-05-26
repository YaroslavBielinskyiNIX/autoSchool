import helper.MainPageHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SimpleTest {

    private WebDriver webDriver;
    private Atlas atlas;

    @BeforeTest
    public void initBeforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        atlas = new Atlas(new WebDriverConfiguration(webDriver));
        webDriver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    @Test
    public void simple() {
        new MainPageHelper(webDriver, atlas).open()
                .enterQuery("Dress")
                .clickSubmitButton()
                .descendingItemsSort()
                .openItemPage(0)
                .clickAddToCartButton()
                .clickProceedToCheckout();
    }

    @AfterMethod
    public void tearDownAfterMethod() {
        webDriver.close();
    }

    @AfterTest
    public void tearDownAfterTest() {
        webDriver.quit();
    }

}
