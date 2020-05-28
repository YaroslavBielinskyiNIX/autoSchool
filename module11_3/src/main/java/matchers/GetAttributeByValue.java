package matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class GetAttributeByValue extends TypeSafeMatcher<AtlasWebElement> {

    private final String str;

    public GetAttributeByValue(final String str) {
        this.str = str;
    }

    public static GetAttributeByValue hasByValue(final String str) {
        return new GetAttributeByValue(str);
    }

    @Override
    protected boolean matchesSafely(final AtlasWebElement atlasWebElement) {
        return atlasWebElement.getAttribute("value").toLowerCase().contains(this.str.toLowerCase());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches string = " + str);
    }
}