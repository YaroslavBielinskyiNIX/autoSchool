package elements;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface ItemInCart extends AtlasWebElement<ItemInCart> {

    @FindBy(".//td/p[contains(@class,'product-name')]/a")
    AtlasWebElement itemDescriptionName();

    @FindBy(".//span[contains(@class,'old-price')] | .//span[contains(@class,'price special-price')] | .//span[@class='price']/span[@class='price']")
    AtlasWebElement itemPrice();

    @FindBy(".//input[contains(@class,'cart_quantity_input')]")
    AtlasWebElement itemCount();

    @FindBy(".//span/span[contains(@class,'price')]")
    AtlasWebElement totalPrice();

    @FindBy(".//td[@class='cart_description']//small/a")
    AtlasWebElement itemSizeColor();

    @FindBy("//i[@class='icon-trash']")
    AtlasWebElement itemTrashIcon();

}
