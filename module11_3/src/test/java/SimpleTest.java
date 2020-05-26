import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import io.qameta.atlas.webdriver.WebPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.ItemPage;
import page.MainPage;
import page.SearchResultPage;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SimpleTest {

    private WebDriver webDriver;
    private WebDriverWait wait;
    private Atlas atlas;

    @BeforeTest
    public void initBeforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        atlas = new Atlas(new WebDriverConfiguration(webDriver));
        webDriver.manage().timeouts().implicitlyWait(10, SECONDS);
        wait = new WebDriverWait(webDriver, 10);
    }

    @Test
    public void simple() {
        onMainPage().open("http://automationpractice.com/");
        onMainPage().header().searchInput().sendKeys("dress");
        onMainPage().header().searchInput().submit();

        waitUntilLoad();
        System.out.println(onSearchPage().items().size());

        onSearchPage().items().get(0).moreButton().click();
        onItemPage().addToCartButton().click();
        onSearchPage().itemAddedPopUp().proceedToCheckout().click();

    }

    @AfterTest
    public void tearDownAfterTest() {
        webDriver.quit();
    }

    private MainPage onMainPage() {
        return onPage(MainPage.class);
    }

    private SearchResultPage onSearchPage() {
        return onPage(SearchResultPage.class);
    }

    private ItemPage onItemPage() {
        return onPage(ItemPage.class);
    }

    private <T extends WebPage> T onPage(Class<T> page) {
        return atlas.create(webDriver, page);
    }

    public void waitUntilLoad() {
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
