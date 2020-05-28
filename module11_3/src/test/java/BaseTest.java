import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseTest {

    protected WebDriver webDriver;

    @BeforeTest
    public void initBeforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, SECONDS);
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
