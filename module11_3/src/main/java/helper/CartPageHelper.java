package helper;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.CartPage;

import java.util.List;

import static java.lang.String.valueOf;
import static matchers.GetAttributeByValue.hasByValue;
import static matchers.InnerHtmlMatcher.hasInnerHtml;
import static matchers.StringMatcher.hasText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class CartPageHelper extends BaseHelper {

    public CartPageHelper(WebDriver webDriver) {
        super(webDriver);
    }

    private CartPage onCartPage() {
        return onPage(CartPage.class);
    }

    @Step("Element ItemPrice Should be the same as on SearchResultPage")
    public CartPageHelper verifyItemPriceSameAsOnSearchResultPage(String str) {
        onCartPage().itemsInCart().get(0).itemPrice().should(hasText(str));

        return this;
    }

    @Step("Element ItemName Should be the same as on SearchResultPage")
    public CartPageHelper verifyItemNameSameAsOnSearchResultPage(String str) {
        onCartPage().itemsInCart().get(0).itemDescriptionName().should(hasInnerHtml(str));

        return this;
    }

    @Step("Cart Should contain all items")
    public CartPageHelper verifyContainAllItems(List<Product> products) {
        waitUntilLoad();

        assertThat(onCartPage().itemsInCart(), hasSize(products.size()));

        for (int i = 0; i < products.size() - 1; i++) {
            assertThat(onCartPage().itemsInCart().get(i).itemDescriptionName(), hasText(products.get(i).getItemName()));
            assertThat(onCartPage().itemsInCart().get(i).itemPrice(), hasText(valueOf(products.get(i).getItemPrice())));
            assertThat(onCartPage().itemsInCart().get(i).itemCount(), hasByValue(valueOf(products.get(i).getItemCount())));
        }

        return this;
    }

    @Step("Remove '{index}' item from CartPage")
    public CartPageHelper removeItemFromCart(List<Product> products, int index) {
        onCartPage().itemsInCart().get(index).itemTrashIcon().click();
        onCartPage().itemsInCart().remove(index);
        System.out.println(onCartPage().itemsInCart().size());
        products.remove(index);

        return this;
    }

}