package elements;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface ItemInCart extends AtlasWebElement<ItemInCart> {

    @FindBy(".//td/p[contains(@class,'product-name')]/a")
    AtlasWebElement itemDescriptionName();

    @FindBy(".//span[contains(@class,'old-price')] | .//span[contains(@class,'price special-price')] | .//span[@class='price']/span[@class='price']")
    AtlasWebElement itemPrice();

}
