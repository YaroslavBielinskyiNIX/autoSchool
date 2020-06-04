package api.serializationClasses;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class BookIdentity {

    private List<BookIdentity.Data> data;

    @Getter
    public class Data {

        private BookIdentity.Data.Attributes attributes;

        @Getter
        public class Attributes {

            @Getter
            @SerializedName("start-time")
            private String startTime;

        }
    }
}