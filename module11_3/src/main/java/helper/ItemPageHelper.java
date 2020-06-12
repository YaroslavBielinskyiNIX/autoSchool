package helper;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.ItemPage;

import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static matchers.DisplayedMatcher.isDisplayed;
import static matchers.StringMatcher.hasText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class ItemPageHelper extends BaseHelper {

    public ItemPageHelper(WebDriver webDriver) {
        super(webDriver);
    }

    private ItemPage onItemPage() {
        return onPage(ItemPage.class);
    }

    @Step("QuantityField set value '{quantity}'")
    public ItemPageHelper setQuantity(int quantity) {
        onItemPage().itemInfo().quantityInputField().clear();
        onItemPage().itemInfo().quantityInputField().sendKeys(valueOf(quantity));

        return this;
    }

    @Step("SizeDropDown set value '{size}'")
    public ItemPageHelper setSize(String size) {
        waitUntilLoad();
        onItemPage().sizeDropDownMenu().selectByValue(size);

        return this;
    }



    @Step("Click 'Add to Cart' Link")
    public ItemPageHelper clickAddToCartButton(List<Product> products) {
        Product product = new Product(
                onItemPage().itemInfo().itemName().getAttribute("innerHTML"),
                parseInt(onItemPage().itemInfo().quantityInputField().getAttribute("value")),
                parseDouble(onItemPage().itemInfo().itemPrice().getText().replaceAll("[^0-9.]", "")));

        products.add(product);

        onItemPage().addToCartButton().click();

        return this;
    }

    @Step("Click 'Add to Cart' Link")
    public ItemPageHelper clickAddToCartButton() {
        onItemPage().addToCartButton().click();

        return this;
    }

    @Step("Click 'Procced to checkout' Link")
    public CartPageHelper clickProceedToCheckout() {
        waitUntilLoad();
        onItemPage().itemAddedPopUp().proceedToCheckout().should(isDisplayed()).click();

        return new CartPageHelper(webDriver);
    }

    @Step("Click 'Continue shopping' Link")
    public ItemPageHelper clickContinueShopping() {
        waitUntilLoad();
        onItemPage().itemAddedPopUp().continueShoppingButton().should(isDisplayed()).click();

        return new ItemPageHelper(webDriver);
    }

    @Step("Enter '{str}' in searchInput Filed")
    public ItemPageHelper enterQuery(String str) {
        onItemPage().header().searchInput().clear();
        onItemPage().header().searchInput().sendKeys(str);

        return this;
    }

    @Step("Click 'Cart' Link")
    public CartPageHelper clickCart() {
        onItemPage().header().cartButton().click();

        return new CartPageHelper(webDriver);
    }

    @Step("Click 'Submit' Link")
    public SearchResultPageHelper clickSubmitButton() {
        onItemPage().header().searchInput().submit();

        return new SearchResultPageHelper(webDriver);
    }

    @Step("ItemSize, ItemName Should be the same on ItemAddedPopUp")
    public ItemPageHelper verifyAllData() {
        onItemPage().itemAddedPopUp().itemName().should(hasText(onItemPage().itemInfo().itemName().getText()));
        onItemPage().itemAddedPopUp().itemSizeColor().should(hasText(onItemPage().itemInfo().quantityInputField().getText()));

        return this;
    }

    @Step("TotalPrice Should be correct")
    public ItemPageHelper verifyTotalPrice() {
        double itemPrice = parseDouble(onItemPage().itemInfo().itemPrice().getText().trim().replaceAll("[^0-9.]", ""));
        int itemCount = parseInt(onItemPage().itemInfo().quantityInputField().getAttribute("value") );
        double totalPrice = parseDouble(onItemPage().itemAddedPopUp().totalPrice().getText().replaceAll("[^0-9.]", ""));

        assertThat(totalPrice, closeTo(itemCount * itemPrice, 0.01));

        return this;
    }
}
