package elements;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.openqa.selenium.support.ui.Select;

public interface DropDownMenu extends AtlasWebElement<DropDownMenu>{

    default void selectByValue(String str) {
        Select dropDownMenu = new Select(this);
        dropDownMenu.selectByVisibleText(str);
    }
}
