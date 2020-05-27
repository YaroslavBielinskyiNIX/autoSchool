package helper;

import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.WebDriver;
import page.SearchResultPage;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static matchers.InDescendingOrdering.isSortedDescending;
import static matchers.StringMatcher.hasText;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchResultPageHelper extends BaseHelper {

    public SearchResultPageHelper(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Items count on ResultPage")
    public int getItemsCount() {
        return onSearchResultPage().items().size();
    }

    private SearchResultPage onSearchResultPage() {
        return onPage(SearchResultPage.class);
    }

    @Step("Open ItemPage")
    public ItemPageHelper openItemPage(int index) {
        onSearchResultPage().items().get(index).moreButton();

        return new ItemPageHelper(webDriver);
    }

    @Step("Applied DescendingSort for Items on SearchResultPage")
    public SearchResultPageHelper descendingItemsSort() {
        waitUntilLoad();
        onSearchResultPage().dropDownMenu().selectByValue("Price: Highest first");

        return this;
    }

    @Step("Should be DescendingSort for Items on SearchResultPage")
    public SearchResultPageHelper verifyDescendingSort() {
        onSearchResultPage().items().extract(item -> parseDouble(item.itemPriceWithoutDiscount().getAttribute("innerHTML").trim().replaceAll("[^0-9.]", ""))).should(isSortedDescending());
        return this;
    }

    @Step("SearchResultTitle should contain '{str}'")
    public SearchResultPageHelper verifySearchResultTitleContainSearchQuery(String str) {
        onSearchResultPage().searchResultTitle().should(hasText(str));

        return this;
    }

    @Step("Get current Item price")
    public String getCurrentItemPrice(int index) {
        return onSearchResultPage().items().get(index).currentPrice().getText();
    }

    @Step("Get Item name")
    public String getItemName(int index) {
        return  onSearchResultPage().items().get(index).itemName().getText();
    }
}
