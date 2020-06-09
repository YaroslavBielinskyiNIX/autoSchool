package api.serializationClasses;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class RoomsResponseData {

    private List<Data> data;
    private List<Included> included;

    @Getter
    public class Included {

        private Attributes attributes;
        private String id;
        private String type;

        @Getter
        public class Attributes {

            @SerializedName("building-id")
            private String buildingId;

        }
    }

    @Getter
    public class Data {

        private Attributes attributes;

        @Getter
        public class Attributes {

            @SerializedName("floor-id")
            private String floorId;

            private String size;
            private String name;
            private String id;
        }
    }
}
