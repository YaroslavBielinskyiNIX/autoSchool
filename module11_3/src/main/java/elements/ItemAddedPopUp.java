package elements;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface ItemAddedPopUp extends AtlasWebElement {

    @FindBy(".//span[@title='Continue shopping']")
    AtlasWebElement continueShoppingButton();

    @FindBy(".//span[contains(text(),'Proceed to checkout')]")
    AtlasWebElement proceedToCheckout();

    @FindBy("//span[@id='layer_cart_product_title']")
    AtlasWebElement itemName();

    @FindBy("//span[@id='layer_cart_product_attributes']")
    AtlasWebElement itemSizeColor();

    @FindBy("//span[@id='layer_cart_product_price']")
    AtlasWebElement totalPrice();
}
