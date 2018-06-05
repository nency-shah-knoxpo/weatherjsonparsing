package com.example.tejas.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherFragment extends Fragment {

    private RecyclerView mWeatherRV;

    private List<Weather> mWeather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);

        init(v);

        mWeatherRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();

        return v;
    }

    public void setupAdapter() {
        WeatherAdapter adapter = new WeatherAdapter(mWeather);
        mWeatherRV.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startFetching();
    }

    public void init(View v) {
        mWeather = new ArrayList<>();
        mWeatherRV = v.findViewById(R.id.recycler_view);

    }

    public void startFetching() {

        String api_key = "75f7635dba3a7ad7dd2a7462e1da4c99";
        Retrofit retrofit = ReturnRetroFit.getRetrofitInstance();
        WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);
        Call<WeatherResponse> call = weatherInterface.getWeatherJSON("524901", api_key);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                for (int i = 0; i < response.body().mWeathers.size(); i++) {
                    Weather weather = new Weather();

                    String description;
                    String min_temp = response.body().mWeathers.get(i).mMainDetails.getMinimumTemperature();
                    String max_temp = response.body().mWeathers.get(i).mMainDetails.getMaximumTemperature();
                    String humidity = response.body().mWeathers.get(i).mMainDetails.getHumidity();
                    for (int j = 0; j < response.body().mWeathers.get(j).details.size(); j++) {
                        description = response.body().mWeathers.get(i).details.get(j).getDescription();
                        weather.setDescription(description);
                    }
                    weather.setMinimumTemperature(min_temp);
                    weather.setMaximumTemperature(max_temp);
                    weather.setHumidity(humidity);
                    mWeather.add(weather);

                }

                setupAdapter();
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });
    }


    public class WeatherHolder extends RecyclerView.ViewHolder {

        private Weather mWeather;
        private TextView mMinTempTV, mMaxTempTV, mHumidityTV, mDescTV;

        public WeatherHolder(View itemView) {

            super(itemView);
            mMinTempTV = itemView.findViewById(R.id.min_temp);
            mMaxTempTV = itemView.findViewById(R.id.max_temp);
            mHumidityTV = itemView.findViewById(R.id.humidity);
            mDescTV = itemView.findViewById(R.id.desc);

        }

        public void bindWeather(Weather weather) {
            mWeather = weather;
            mMinTempTV.setText("Minimum Temprature:" + mWeather.getMinimumTemperature());
            mMaxTempTV.setText("Maximum Temprature:" + mWeather.getMaximumTemperature());
            mDescTV.setText("Description:" + mWeather.getDescription());
            mHumidityTV.setText("Humidity:" + mWeather.getHumidity());
        }
    }

    private class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder> {


        public WeatherAdapter(List<Weather> weathers) {

            mWeather = weathers;
        }

        @NonNull
        @Override
        public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_weather, parent, false);
            return new WeatherHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {
            Weather weather = mWeather.get(position);
            holder.bindWeather(weather);
        }

        @Override
        public int getItemCount() {
            return mWeather.size();
        }
    }
}
