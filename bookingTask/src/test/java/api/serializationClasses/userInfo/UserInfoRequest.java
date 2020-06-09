package api.serializationClasses.userInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {

    private Data data = new Data();

    @Getter
    @Setter
    public class Data {

        private Attributes attributes = new Attributes();
        private String id;
        private String type;
        private String relationships;

        @Getter
        @Setter
        public class Attributes {

            @SerializedName("favorite-building-id")
            private int favoriteBuildingId;

            @SerializedName("favorite-floor-id")
            private String favoriteFloorId;

            private int id;
        }
    }

}
