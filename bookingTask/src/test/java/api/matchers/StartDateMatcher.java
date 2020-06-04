package api.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import api.serializationClasses.BookIdentity;

import java.util.List;

public class StartDateMatcher extends TypeSafeMatcher<List<BookIdentity.Data>> {

    private final String str;

    public StartDateMatcher(final String str) {
        this.str = str;
    }

    public static StartDateMatcher containsBookWithStartDate(final String str) {
        return new StartDateMatcher(str);
    }

    @Override
    protected boolean matchesSafely(List<BookIdentity.Data> data) {
        return data.stream().anyMatch(a -> a.getAttributes().getStartTime().equals(str));
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText(str);
    }
}
