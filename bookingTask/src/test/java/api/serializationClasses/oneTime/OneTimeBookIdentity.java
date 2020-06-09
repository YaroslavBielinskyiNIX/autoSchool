package api.serializationClasses.oneTime;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class OneTimeBookIdentity {

    private List<OneTimeBookIdentity.Data> data;

    @Getter
    public class Data {

        private OneTimeBookIdentity.Data.Attributes attributes;

        @Getter
        public class Attributes {

            @Getter
            @SerializedName("start-time")
            private String startTime;

            @Getter
            private String title;
        }
    }
}