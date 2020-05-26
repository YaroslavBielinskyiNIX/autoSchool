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

    @Step("addToCartButton clicking")
    public ItemPageHelper clickAddToCartButton() {
        onItemPage().addToCartButton().click();

        return this;
    }

    @Step("proceedToCheckout clicking")
    public CartPageHelper clickProceedToCheckout() {
        waitUntilLoad();
        onItemPage().itemAddedPopUp().proceedToCheckout().click();

        return new CartPageHelper(webDriver, atlas);
    }
}
