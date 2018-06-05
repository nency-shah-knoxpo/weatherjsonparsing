package com.example.tejas.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {

    @GET("forecast")

    Call<WeatherResponse> getWeatherJSON(@Query("id") String Id , @Query("appid") String appId);


}
