package api.serializationClasses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static freemarker.template.utility.Collections12.singletonList;

@Getter
@Setter
public class EmailRemainder {

    private int eventId;
    private List<String> groupIds = new ArrayList<>();
    private String recEventId = "";
    private List<String> addresses = new ArrayList<String>(singletonList("testee@gmail.com"));
}
