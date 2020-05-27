package elements;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface Item extends AtlasWebElement<Item> {

    @FindBy(".//span[contains(text(),'Add to cart')]")
    AtlasWebElement addToCartButton();

    @FindBy(".//span[contains(text(),'More')]")
    AtlasWebElement moreButton();

    @FindBy(".//a[@class='product-name']")
    AtlasWebElement itemName();

    @FindBy(".//div[@class='right-block']//div[count(span)=1]/span[contains(@class,'price')] | .//div[@class='right-block']//div[count(span)>1]/span[contains(@class,'old-price')]")
    AtlasWebElement itemPriceWithoutDiscount();

    @FindBy(".//div[@class='right-block']//span[@class='price product-price']")
    AtlasWebElement currentPrice();
}
