package helper;

import io.qameta.atlas.core.Atlas;
import org.openqa.selenium.WebDriver;
import page.CartPage;

public class CartPageHelper extends BaseHelper {

    public CartPageHelper(WebDriver webDriver, Atlas atlas) {
        super(webDriver, atlas);
    }

    private CartPage onCartPage() {
        return onPage(CartPage.class);
    }

}
