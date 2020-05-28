package elements;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface ItemInfo extends AtlasWebElement {

    @FindBy("//input[@id='quantity_wanted']")
    AtlasWebElement quantityInputField();

    @FindBy("//i[contains(@class,'icon-plus'])")
    AtlasWebElement increaseQuantityButton();

    @FindBy("//i[contains(@class,'icon-plus'])")
    AtlasWebElement decreaseQuantityButton();

    @FindBy("//h1[contains(@itemprop,'name')]")
    AtlasWebElement itemName();

    @FindBy("//span[@id='our_price_display']")
    AtlasWebElement itemPrice();
}
