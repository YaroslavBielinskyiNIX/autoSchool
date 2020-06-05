package api.serializationClasses.recurring.request;

import lombok.Getter;

import java.util.List;

@Getter
public class RecurringWeeklyBookRequestInfo {

    private Weekly weekly;
    private String dateEnd = "2020-07-04T18:38:08";
    private String dateStart = "2020-06-04T18:38:08";
    private String description = "";
    private String oneTimeEventId = "";
    private String roomId = "2";
    private String timeEnd = "19:00:00";
    private String timeStart = "18:45:00";
    private String title = "";

    @Getter
    public class Weekly {

        private List<String> daysOfWeek;
        private String weekInterval = "1";
    }
}
