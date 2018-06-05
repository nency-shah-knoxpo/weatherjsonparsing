package com.example.tejas.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("list")
    @Expose
    List<WeatherMainDetails> mWeathers;


    public class WeatherMainDetails {

        @SerializedName("main")
        @Expose
        Weather mMainDetails;

        @SerializedName("weather")
        List<WeatherDetails> details;


    }

    public class WeatherDetails {

        @SerializedName("description")
        String description;


        public String getDescription(){
            return description;
        }
    }



}
