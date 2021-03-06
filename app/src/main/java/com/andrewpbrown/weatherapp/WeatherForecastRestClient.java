package com.andrewpbrown.weatherapp;

import retrofit2.http.GET;
import rx.Observable;

interface WeatherForecastRestClient {

    @GET("data/2.5/forecast?q=london,uk&units=metric&APPID=ac692dd7b17227abc731ff850a5d636c")
    Observable<WeatherForecastDto> getWeatherForecast();
}
