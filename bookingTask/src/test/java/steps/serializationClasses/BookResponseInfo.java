package steps.serializationClasses;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class BookResponseInfo {

    private Data data;

    @Getter
    public class Data {

        private Attributes attributes;

        @Getter
        public class Attributes {

            @SerializedName("room-id")
            private String roomId;

            @SerializedName("recurring-event-id")
            private String recurringEventId;

            @SerializedName("start-time")
            private String startTime;

            @SerializedName("end-time")
            private String endTime;

            @SerializedName("is-deleted")
            private String isDeleted;

            private String id;
            private String description;
            private String title;
        }
    }
}
