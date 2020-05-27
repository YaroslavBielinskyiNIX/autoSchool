import helper.MainPageHelper;
import helper.SearchResultPageHelper;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {

    @Test
    public void simple() {
        SearchResultPageHelper searchResultPageHelper = new MainPageHelper(webDriver).open()
                .enterQuery("Dress")
                .clickSubmitButton()
                .verifySearchResultTitleContainSearchQuery("Dress")
                .descendingItemsSort()
                .verifyDescendingSort();

        String currentItemPricePrice = searchResultPageHelper.getCurrentItemPrice(0);
        String itemName = searchResultPageHelper.getItemName(0);

        searchResultPageHelper.
                openItemPage(0)
                .clickAddToCartButton()
                .clickProceedToCheckout()
                .verifyItemPriceSameAsOnSearchResultPage(currentItemPricePrice)
                .verifyItemNameSameAsOnSearchResultPage(itemName);

    }

}
