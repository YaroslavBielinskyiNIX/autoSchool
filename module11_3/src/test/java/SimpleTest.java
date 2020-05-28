import helper.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleTest extends BaseTest {

    @Test
    public void test2() throws InterruptedException {
        List<Product> products = new ArrayList<>();

        new MainPageHelper(webDriver).open()
                .enterQuery("Printed Dress")
                .clickSubmitButton()
                .openItemPage(0)
                .setQuantity(10)
                .setSize("M")
                .clickAddToCartButton(products)
                .verifyAllData()
                .verifyTotalPrice()
                .clickContinueShopping()
                .enterQuery("blouse")
                .clickSubmitButton()
                .openItemPage(0)
                .setSize("S")
                .clickAddToCartButton(products)
                .verifyAllData()
                .verifyTotalPrice()
                .clickContinueShopping()
                .clickCart()
                .verifyContainAllItems(products)
                .removeItemFromCart(products, 1)
                .verifyContainAllItems(products);
    }

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
