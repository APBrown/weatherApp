package com.andrewpbrown.weatherapp;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

class WeatherForecastProvider {

    private WeatherForecastRestClient restClient;

    WeatherForecastProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .client(new OkHttpClient.Builder().build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restClient = retrofit.create(WeatherForecastRestClient.class);
    }

    Observable<WeatherForecastDto> getWeatherForecast() {
        return restClient.getWeatherForecast();
    }
}
