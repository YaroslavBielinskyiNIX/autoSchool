package page;

import elements.DropDownMenu;
import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import layout.WithHeader;
import layout.WithItemAddedPopUp;
import layout.WithItemInfo;

public interface ItemPage extends WebPage, WithHeader, WithItemAddedPopUp, WithItemInfo {

    @FindBy("//span[contains(text(),'Add to cart')]")
    AtlasWebElement addToCartButton();

    @FindBy("//select[@id='group_1']")
    DropDownMenu sizeDropDownMenu();
}
