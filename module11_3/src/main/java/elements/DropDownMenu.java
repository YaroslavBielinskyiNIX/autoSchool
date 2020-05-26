package elements;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import org.openqa.selenium.support.ui.Select;

public interface DropDownMenu extends AtlasWebElement<DropDownMenu>{

    @FindBy("//select[@id='selectProductSort']")
    AtlasWebElement dropDownMenu();

    default void selectByValue(String str) {
        Select dropDownMenu = new Select(dropDownMenu());
        dropDownMenu.selectByVisibleText(str);
    }
}
