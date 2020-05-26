package elements;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;

public interface Header extends AtlasWebElement {

    @FindBy("//input[@id='search_query_top']")
    AtlasWebElement searchInput();

    @FindBy("//b[contains(text(),'Cart')]")
    AtlasWebElement cartButton();
}
