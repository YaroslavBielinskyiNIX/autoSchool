package layout;

import elements.ItemAddedPopUp;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface WithItemAddedPopUp {

    @FindBy("//div[@id='layer_cart']/div[contains(@class,'clearfix')]")
    ItemAddedPopUp itemAddedPopUp();
}
