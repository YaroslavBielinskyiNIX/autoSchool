package helper;

import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.WebDriver;
import page.SearchResultPage;

public class SearchResultPageHelper extends BaseHelper {

    public SearchResultPageHelper(WebDriver webDriver, Atlas atlas) {
        super(webDriver, atlas);
    }

    @Step("Counting items on ResultPage")
    public int getItemsCount() {
        return onSearchPage().items().size();
    }

    private SearchResultPage onSearchPage() {
        return onPage(SearchResultPage.class);
    }

    @Step("Opening ItemPage")
    public ItemPageHelper openItemPage(int index) {
        onSearchPage().items().get(index).moreButton();

        return new ItemPageHelper(webDriver, atlas);
    }

    @Step("DescendingSort for Items on SearchResultPage")
    public SearchResultPageHelper descendingItemsSort() {
        waitUntilLoad();
        onSearchPage().dropDownMenu().selectByValue("Price: Highest first");

        return this;
    }

}
