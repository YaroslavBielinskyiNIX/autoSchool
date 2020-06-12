package layout;

import elements.ItemInfo;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface WithItemInfo {

    @FindBy("//div[contains(@class,'primary_block')]")
    ItemInfo itemInfo();
}
