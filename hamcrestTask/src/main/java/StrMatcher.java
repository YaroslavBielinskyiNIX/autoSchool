import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

public class StrMatcher extends TypeSafeMatcher<WebElement> {

    private final String str;

    public StrMatcher(final String str) {
        this.str = str;
    }

    public static StrMatcher hasText(final String str) {
        return new StrMatcher(str);
    }

    @Override
    protected boolean matchesSafely(final WebElement webElement) {
        return webElement.getText().matches(this.str);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches string =" + str);
    }
}
