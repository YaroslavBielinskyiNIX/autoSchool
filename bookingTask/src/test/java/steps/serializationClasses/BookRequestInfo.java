package steps.serializationClasses;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class BookRequestInfo {

    private Data data = new Data();

    @Getter
    public class Data {

        private Attributes attributes = new Attributes();
        private Relationships relationships = new Relationships();
        private String id = "";
        private String type = "events";

        @Getter
        public class Attributes {

            @SerializedName("end-time")
            private String endTime = "2020-06-16T10:05:00";

            @SerializedName("recurring-event-id")
            private String recurringEventId = "";

            @SerializedName("start-time")
            private String startTime = "2020-06-16T09:05:00";

            private String title = "";
            private String description = "";
            private String id = "";
        }

        @Getter
        public class Relationships {

            private Room room = new Room();

            @Getter
            public class Room {

                @SerializedName("data")
                private DData DData = new DData();

                @Getter
                public class DData {

                    private String id = "9";
                    private String type = "rooms";
                }
            }
        }
    }
}