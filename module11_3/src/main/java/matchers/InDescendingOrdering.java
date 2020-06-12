package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;
import static java.util.Collections.reverseOrder;

public class InDescendingOrdering extends TypeSafeMatcher<List<Double>> {

    private List<Double> list;

    public InDescendingOrdering() {
    }

    @Override
    protected boolean matchesSafely(List<Double> list) {
        {
            this.list = new ArrayList<>(list);

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

        description.appendText(valueOf(list));
    }

    public static InDescendingOrdering isSortedDescending() {
        return new InDescendingOrdering();
    }
}
