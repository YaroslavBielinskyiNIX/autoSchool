package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class InDescendingOrdering extends TypeSafeMatcher<List<Double>> {

    public InDescendingOrdering() {

    }

    @Override
    protected boolean matchesSafely(List<Double> list) {
        System.out.println(list);
        {
            for(int i = 0 ; i < list.size() - 1; i++) {
                if(list.get(i) <= list.get(i + 1))
                    return false;
            }

            return true;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("List not sorted");
    }

    public static Matcher<List<Double>> isSortedDescending() {
        return new InDescendingOrdering();
    }

}
