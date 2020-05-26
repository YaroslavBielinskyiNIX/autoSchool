package helper;

import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseHelper {

    protected WebDriver webDriver;
    protected Atlas atlas;
    private WebDriverWait wait;

    public BaseHelper(WebDriver webDriver, Atlas atlas) {
        this.webDriver = webDriver;
        this.atlas = atlas;
        this.wait = new WebDriverWait(webDriver, 10);

    }

    protected <T extends WebPage> T onPage(Class<T> page) {
        return atlas.create(webDriver, page);
    }

    @Step("Waiting until page would be load")
    public void waitUntilLoad() {
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

}
