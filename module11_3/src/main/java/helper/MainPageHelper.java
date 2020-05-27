package helper;

import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.WebDriver;
import page.MainPage;

public class MainPageHelper extends BaseHelper {

    public MainPageHelper(WebDriver webDriver, Atlas atlas) {
        super(webDriver, atlas);
    }

    private MainPage onMainPage() {
        return onPage(MainPage.class);
    }

    @Step("Enter '{str}' in searchInput Filed")
    public MainPageHelper enterQuery(String str) {
        onMainPage().header().searchInput().sendKeys(str);

        return this;
    }

    @Step("Click 'Submit' Link")
    public SearchResultPageHelper clickSubmitButton() {
        onMainPage().header().searchInput().submit();

        return new SearchResultPageHelper(webDriver, atlas);
    }

    @Step("Open MainPage")
    public MainPageHelper open() {
        onMainPage().open("http://automationpractice.com");

        return this;
    }

}
