package matchers;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class StringMatcher extends TypeSafeMatcher<AtlasWebElement> {

    private final String str;

    public StringMatcher(final String str) {
        this.str = str;
    }

    public static StringMatcher hasText(final String str) {
        return new StringMatcher(str);
    }

    @Override
    protected boolean matchesSafely(final AtlasWebElement atlasWebElement) {
        return atlasWebElement.getText().toLowerCase().contains(this.str.toLowerCase());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches string = " + str);
    }
}