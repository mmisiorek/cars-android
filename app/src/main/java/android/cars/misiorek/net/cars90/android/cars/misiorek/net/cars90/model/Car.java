package android.cars.misiorek.net.cars90.android.cars.misiorek.net.cars90.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by marcin on 09.04.16.
 */
public class Car extends RealmObject {

    private String brand;

    private String model;

    @SerializedName("manufactured_date")
    private Date manufacturedDate;

    @SerializedName("registration_number")
    private String registrationNumber;

    @SerializedName("is_available")
    private Boolean isAvailable;

    @SerializedName("photo")
    private CarPhoto carPhoto;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(Date manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public CarPhoto getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(CarPhoto carPhoto) {
        this.carPhoto = carPhoto;
    }
}
