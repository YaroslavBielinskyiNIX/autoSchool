package helper;

import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.WebDriver;
import page.ItemPage;

import static matchers.DisplayedMatcher.isDisplayed;

public class ItemPageHelper extends BaseHelper {

    public ItemPageHelper(WebDriver webDriver) {
        super(webDriver);
    }

    private ItemPage onItemPage() {
        return onPage(ItemPage.class);
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
}
