package com.andrewpbrown.weatherapp;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class WeatherForecastProvider {

    private WeatherForecastRestClient restClient;

    WeatherForecastProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .client(new OkHttpClient.Builder().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restClient = retrofit.create(WeatherForecastRestClient.class);
    }

    Observable<WeatherForecastDto> getWeatherForecast() {
        return restClient.getWeatherForecast();
    }
}
