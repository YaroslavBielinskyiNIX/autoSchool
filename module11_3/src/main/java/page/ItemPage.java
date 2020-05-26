package page;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import layout.WithHeader;
import layout.WithItemAddedPopUp;

public interface ItemPage extends WebPage, WithHeader, WithItemAddedPopUp {

    @FindBy("//span[contains(text(),'Add to cart')]")
    AtlasWebElement addToCartButton();
}
