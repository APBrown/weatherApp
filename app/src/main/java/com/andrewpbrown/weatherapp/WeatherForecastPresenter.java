package com.andrewpbrown.weatherapp;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class WeatherForecastPresenter {

    private WeatherForecastView view;
    private WeatherForecastProvider provider;

    WeatherForecastPresenter(WeatherForecastView view) {
        this.view = view;
        provider = new WeatherForecastProvider();
    }

    void getWeatherReport() {
        provider.getWeatherForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onWeatherDetailsLoaded, this::onErrorRetrievingWeatherDetails);
    }

    private void onWeatherDetailsLoaded(WeatherForecastDto dto) {
        view.displayTodaysWeather(dto);
        view.displayFutureWeather(getFutureWeatherList(dto));
    }

    private void onErrorRetrievingWeatherDetails(Throwable throwable) {
        view.displayError();
    }

    private ArrayList<WeatherForecastDto.WeatherInfoDto> getFutureWeatherList(WeatherForecastDto dto) {
        ArrayList<WeatherForecastDto.WeatherInfoDto> futureWeather = dto.weatherInformation;
        futureWeather.remove(0);
        return futureWeather;
    }
}
