package helper;

import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.AtlasWebElement;
import org.openqa.selenium.WebDriver;
import page.CartPage;

import static matchers.InnerHtmlMatcher.hasInnerHtml;
import static matchers.StringMatcher.hasText;

public class CartPageHelper extends BaseHelper {

    public CartPageHelper(WebDriver webDriver, Atlas atlas) {
        super(webDriver, atlas);
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

}
