package matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class InnerHtmlMatcher extends TypeSafeMatcher<AtlasWebElement> {

    private final String str;

    public InnerHtmlMatcher(final String str) {
        this.str = str;
    }

    public static InnerHtmlMatcher hasInnerHtml(final String str) {
        return new InnerHtmlMatcher(str);
    }

    @Override
    protected boolean matchesSafely(final AtlasWebElement atlasWebElement) {
        return atlasWebElement.getAttribute("innerHTML").toLowerCase().contains(this.str.toLowerCase());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches string = " + str);
    }
}
