package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

public class DisplayedMatcher extends TypeSafeMatcher<WebElement> {

    private DisplayedMatcher() {
    }

    @Override
    protected boolean matchesSafely(WebElement webElement) {
        return webElement.isDisplayed();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("WebElement displayed");
    }

    public static Matcher<WebElement> isDisplayed() {
        return new DisplayedMatcher();
    }
}