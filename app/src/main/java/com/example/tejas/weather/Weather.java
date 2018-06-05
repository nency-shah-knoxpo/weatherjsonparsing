package com.example.tejas.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class Weather {


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    private String mDescription;
    private UUID Id;

    @SerializedName("temp_min")
    @Expose
    private String mMinimumTemperature;

    @SerializedName("temp_max")
    @Expose
    private String mMaximumTemperature;


    @SerializedName("humidity")
    @Expose
    private String mHumidity;

    public Weather() {

        Id = UUID.randomUUID();
    }

    public UUID getId() {
        return Id;
    }


    public String getMinimumTemperature() {
        return mMinimumTemperature;
    }



    public String getMaximumTemperature() {
        return mMaximumTemperature;
    }



    public String getHumidity() {
        return mHumidity;
    }


    public void setMinimumTemperature(String minimumTemperature) {
        mMinimumTemperature = minimumTemperature;
    }

    public void setMaximumTemperature(String maximumTemperature) {
        mMaximumTemperature = maximumTemperature;
    }

    public void setHumidity(String humidity) {
        mHumidity = humidity;
    }
}
