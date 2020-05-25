package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

import static java.util.Collections.reverseOrder;

public class InDescendingOrdering extends TypeSafeMatcher<List<Double>> {

    final private List<Double> list;

    public InDescendingOrdering(final List<Double> list) {
        this.list = list;
    }

    @Override
    protected boolean matchesSafely(List<Double> list) {
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
        list.sort(reverseOrder());

        description.appendText(String.valueOf(list));
    }

    public static InDescendingOrdering isSortedDescending(List<Double> list) {
        return new InDescendingOrdering(list);
    }

}
