package steps;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class BookRequestInfo {

    private Data data;

    @Getter
    public class Data {

        private Attributes attributes;
        private Relationships relationships;
        private String id;
        private String type;

        @Getter
        public class Attributes {

            @SerializedName("end-time")
            private String endTime;

            @SerializedName("recurring-event-id")
            private String recurringEventId;

            @SerializedName("start-time")
            private String startTime;

            private String title;
            private String description;
            private String id;
        }

        @Getter
        public class Relationships {

            private Room room;

            @Getter
            public class Room {

                @SerializedName("data")
                private DData DData;

                @Getter
                public class DData {

                    private String id;
                    private String type;
                }
            }
        }
    }
}
