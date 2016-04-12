package android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by marcin on 09.04.16.
 */
public class CarPhoto extends RealmObject {

    @SerializedName("id")
    private Integer networkId;

    private String token;

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
