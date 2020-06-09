package api.serializationClasses.oneTime;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class OneTimeBookResponse {

    private List<OneTimeBookResponse.Data> data;

    @Getter
    public class Data {

        private OneTimeBookResponse.Data.Attributes attributes = new OneTimeBookResponse.Data.Attributes();
        private OneTimeBookResponse.Data.Relationships relationships = new OneTimeBookResponse.Data.Relationships();
        private int id;
        private String type = "events";

        @Getter
        @Setter
        public class Attributes {

            @SerializedName("end-time")
            private String endTime = "2020-06-16T10:05:00";

            @SerializedName("recurring-event-id")
            private String recurringEventId = null;

            @SerializedName("start-time")
            private String startTime = "2020-06-16T09:05:00";

            private String title = "";
            private String description = "";
            private int id;
        }

        @Getter
        public class Relationships {

            private OneTimeBookResponse.Data.Relationships.Room room = new OneTimeBookResponse.Data.Relationships.Room();

            @Getter
            public class Room {

                @SerializedName("data")
                private OneTimeBookResponse.Data.Relationships.Room.DData DData = new OneTimeBookResponse.Data.Relationships.Room.DData();

                @Getter
                public class DData {

                    private String id = "9";
                    private String type = "rooms";
                }
            }
        }
    }
}
