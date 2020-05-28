package page;

import elements.ItemInCart;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import layout.WithHeader;

public interface CartPage extends WebPage, WithHeader {

    @FindBy("//tbody/tr[contains(@class,'cart_item')]")
    ElementsCollection<ItemInCart> itemsInCart();
}
