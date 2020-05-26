package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

public class InnerHtmlMatcher extends TypeSafeMatcher<WebElement> {

    private final String str;

    public InnerHtmlMatcher(final String str) {
        this.str = str;
    }

    public static InnerHtmlMatcher hasInnerHtml(final String str) {
        return new InnerHtmlMatcher(str);
    }

    @Override
    protected boolean matchesSafely(final WebElement webElement) {
        return webElement.getAttribute("innerHTML").toLowerCase().contains(this.str.toLowerCase());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches string = " + str);
    }
}
