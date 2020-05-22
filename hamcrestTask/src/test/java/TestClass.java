import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestClass {
    
    private WebDriver webDriver;

    @BeforeClass
    public void initBeforeClass() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    public void testMethod() {
        webDriver.get("http://automationpractice.com/");

        WebElement footerText = webDriver.findElement(By.xpath("//p[@id='editorial_image_legend']"));

        assertThat(footerText, DisplayedMatcher.isDisplayed());
        assertThat(footerText, StrMatcher.hasText("Subsidiary of seleniumframework.com"));
    }

    @AfterMethod
    public void tearDownAfterMethod(){
        webDriver.close();
    }

    @AfterClass
    public void tearDownAfterClass() {
        webDriver.quit();
    }
}
