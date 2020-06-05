package api.serializationClasses;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class RecurringBookIdentity {

    private List<RecurringBookIdentity.Data> data;

    @Getter
    public class Data {

        private RecurringBookIdentity.Data.Attributes attributes;
        private String id;

        @Getter
        public class Attributes {
            @SerializedName("start-time")
            private String startTime;

            @SerializedName("start-date")
            private String dateStart;

            private String title;
        }
    }
}
