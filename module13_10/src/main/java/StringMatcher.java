import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class StringMatcher extends TypeSafeMatcher<String> {

    private final String str;

    public StringMatcher(final String str) {
        this.str = str;
    }

    public static StringMatcher hasText(final String str) {
        return new StringMatcher(str);
    }

    @Override
    protected boolean matchesSafely(final String str) {
        return str.contains(this.str);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches string = " + str);
    }
}
