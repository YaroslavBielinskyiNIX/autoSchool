package page;

import elements.Item;
import io.qameta.atlas.webdriver.ElementsCollection;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import layout.WithHeader;
import layout.WithItemAddedPopUp;
import org.openqa.selenium.support.ui.Select;

public interface SearchResultPage extends WebPage, WithHeader, WithItemAddedPopUp {

    @FindBy("//ul[contains(@class,'row')]/li/div")
    ElementsCollection<Item> items();

    @FindBy("//select[@id='selectProductSort']")
    Select dropDownMenu();
}
