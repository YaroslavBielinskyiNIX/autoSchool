package api.serializationClasses.userInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class UserInfoResponse {

    private List<Data> data;

    @Getter
    public class Data {

        private Attributes attributes = new Attributes();
        private String id;
        private String type;

        @Getter
        @Setter
        public class Attributes {

            @SerializedName("favorite-building-id")
            private int favoriteBuildingId;

            @SerializedName("favorite-floor-id")
            private int favoriteFloorId;

            private int id;
        }
    }
}