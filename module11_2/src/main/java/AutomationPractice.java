import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.parseDouble;
import static matchers.InDescendingOrdering.isSortedDescending;
import static matchers.InnerHtmlMatcher.hasInnerHtml;
import static matchers.StringMatcher.hasText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.xpath;

public class AutomationPractice {

    private WebDriver webDriver;

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{{ "Dress" }, { "Summer" }, { "t-shirt" }};
    }

    @BeforeMethod
    public void init() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "dataProvider")
    public void seleniumTask(String searchQuery) {
        webDriver.get("http://automationpractice.com/");
        webDriver.findElement(xpath("//input[@id='search_query_top']")).sendKeys(searchQuery);
        webDriver.findElement(xpath("//button[@name='submit_search']")).click();

        assertThat(webDriver.findElement(xpath("//span[contains(@class,'lighter')]")), hasText(searchQuery));

        Select dropDownMenu = new Select(webDriver.findElement(xpath("//select[@id='selectProductSort']")));
        dropDownMenu.selectByVisibleText("Price: Highest first");

        List<WebElement> items = webDriver.findElements(xpath("//ul[contains(@class,'row')]/li/div"));
        String nameItem = items.get(0).findElement(xpath("//div[contains(@class,'right-block')]//a[contains(@class,'product-name')]")).getText();

        List<Double> itemPrice = new ArrayList<Double>();
        for (int i = 0; i < items.size(); i++) {
            try {
                itemPrice.add(i, parseDouble(webDriver.findElement(xpath("//ul[contains(@class,'row')]/li[" + (i + 1) + "]/div//span[contains(@class,'old-price product-price')]")).getAttribute("innerHTML").trim().replaceAll("[^0-9.]", "")));
            } catch (Exception e) {
                itemPrice.add(i, parseDouble(webDriver.findElement(xpath("//ul[contains(@class,'row')]/li[" + (i + 1) + "]/div//span[contains(@class,'price product-price')]")).getAttribute("innerHTML").trim().replaceAll("[^0-9.]", "")));
            }
        }

        assertThat(itemPrice, isSortedDescending());

        items.get(0).findElement(xpath("//a[@title='Add to cart']")).click();
        webDriver.findElements(xpath("//span[contains(text(),'Proceed to checkout')]")).get(0).click();

        WebElement priceItemCart;
        try {
            priceItemCart = webDriver.findElement(xpath("//span[contains(@class,'old-price')]"));
        } catch (Exception e) {
            priceItemCart = webDriver.findElement(xpath("//span/span[contains(@class,'price')]"));
        }

        assertThat(priceItemCart, hasText(itemPrice.get(0).toString()));
        assertThat(webDriver.findElement(xpath("//td/p[contains(@class,'product-name')]/a")), hasInnerHtml(nameItem));
    }

    @AfterMethod
    public void tearDownAfterMethod() {
        webDriver.close();
    }

    @AfterTest
    public void tearDown() {
        webDriver.quit();
    }
}
