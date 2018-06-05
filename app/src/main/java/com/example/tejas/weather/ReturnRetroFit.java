package com.example.tejas.weather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReturnRetroFit {

    public static Retrofit getRetrofitInstance(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://samples.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }


}
