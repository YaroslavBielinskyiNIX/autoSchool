package site;

import io.qameta.atlas.webdriver.WebSite;
import io.qameta.atlas.webdriver.extension.Page;
import page.CartPage;
import page.ItemPage;
import page.MainPage;
import page.SearchResultPage;

public interface AutomationPractice extends WebSite {

    @Page
    MainPage onMainPage();

    @Page
    SearchResultPage onSearchResultPage();

    @Page
    CartPage onCartPage();

    @Page
    ItemPage onItemPage();
}
