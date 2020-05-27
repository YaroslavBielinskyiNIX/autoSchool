package matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class DisplayedMatcher extends TypeSafeMatcher<AtlasWebElement> {

    private DisplayedMatcher() {
    }

    @Override
    protected boolean matchesSafely(AtlasWebElement atlasWebElement) {
        return atlasWebElement.isDisplayed();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("WebElement displayed");
    }

    public static DisplayedMatcher isDisplayed() {
        return new DisplayedMatcher();
    }
}