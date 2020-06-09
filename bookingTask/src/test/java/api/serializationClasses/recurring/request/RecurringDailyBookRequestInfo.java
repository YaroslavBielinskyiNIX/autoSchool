package api.serializationClasses.recurring.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecurringDailyBookRequestInfo {

        private Daily daily = new Daily();
        private String dateEnd = "2020-07-05T18:38:08";
        private String dateStart = "2020-07-04T18:38:08";
        private String description = null;
        private String oneTimeEventId = null;
        private Integer roomId = 1;
        private String timeEnd = "19:00:00";
        private String timeStart = "18:45:00";
        private String title = null;

        @Getter
        public class Daily {

                private boolean everyDay = true;
        }
}
