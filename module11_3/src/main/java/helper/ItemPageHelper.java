package helper;

import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.WebDriver;
import page.ItemPage;

public class ItemPageHelper extends BaseHelper {

    public ItemPageHelper(WebDriver webDriver, Atlas atlas) {
        super(webDriver, atlas);
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
        onItemPage().itemAddedPopUp().proceedToCheckout().click();

        return new CartPageHelper(webDriver, atlas);
    }
}
