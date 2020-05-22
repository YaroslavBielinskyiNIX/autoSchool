package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

public class StringMatcher extends TypeSafeMatcher<WebElement> {

    private final String str;

    public StringMatcher(final String str) {
        this.str = str;
    }

    public static StringMatcher hasText(final String str) {
        return new StringMatcher(str);
    }

    @Override
    protected boolean matchesSafely(final WebElement webElement) {
        return webElement.getText().toLowerCase().contains(this.str.toLowerCase());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches string = " + str);
    }
}