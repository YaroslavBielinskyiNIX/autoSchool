import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.openqa.selenium.By.xpath;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AutomationPractice {

    private WebDriver webDriver;

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{{ "Summer" }, { "Dress" }, { "t-shirt" }};
    }

    @BeforeMethod
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yaroslav\\IdeaProjects\\autoSchool\\module11_2\\src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "dataProvider")
    public void seleniumTask(String searchQuery) {
        webDriver.get("http://automationpractice.com/");
        webDriver.findElement(xpath("//input[@id='search_query_top']")).sendKeys(searchQuery);
        webDriver.findElement(xpath("//button[@name='submit_search']")).click();

        assertTrue(webDriver.findElement(xpath("//span[@class='lighter']")).getText().toLowerCase().contains(searchQuery.toLowerCase()));

        Select dropDownMenu = new Select(webDriver.findElement(xpath("//select[@id='selectProductSort']")));
        dropDownMenu.selectByVisibleText("Price: Highest first");

        List<WebElement> items = webDriver.findElements(xpath("//ul[@class='product_list grid row']/li/div"));
        String nameItem = items.get(0).findElement(xpath("//div[@class='right-block']//a[@class='product-name']")).getText();
        String priceItem;

        try {
            priceItem = items.get(0).findElement(xpath("//span[@class='old-price product-price']")).getAttribute("innerHTML").trim();
        } catch (Exception e) {
            priceItem = items.get(0).findElement(xpath("//span[@class='price product-price']")).getAttribute("innerHTML").trim();
        }

        if (items.size() > 1) assertTrue(parseInt(priceItem.replaceAll("[^0-9]","")) >= parseInt(items.get(1).findElement(xpath("//span[@class='old-price product-price']")).getAttribute("innerHTML").trim().replaceAll("[^0-9]","")));

        items.get(0).findElement(xpath("//a[@title='Add to cart']")).click();
        webDriver.findElements(xpath("//span[contains(text(),'Proceed to checkout')]")).get(0).click();

        WebElement priceItemCart;
        try {
            priceItemCart = webDriver.findElement(xpath("//span[@class='old-price']"));
        } catch (Exception e) {
            priceItemCart = webDriver.findElement(xpath("//td[@class='cart_unit']/span[@class='price']"));
        }

        assertEquals(priceItemCart.getText(), priceItem);
        assertEquals(webDriver.findElement(xpath("//div/p[@class='product-name']/a")).getAttribute("innerHTML").trim(), nameItem);
    }

    @AfterTest
    public void tearDown() {
        webDriver.quit();
    }
}
