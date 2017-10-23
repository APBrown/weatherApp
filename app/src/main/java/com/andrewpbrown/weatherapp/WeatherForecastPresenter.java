package com.andrewpbrown.weatherapp;

import java.util.ArrayList;

class WeatherForecastPresenter {

    private WeatherForecastView view;
    WeatherForecastProvider provider;
    AppSchedulerManager appSchedulerManager;

    WeatherForecastPresenter(WeatherForecastView view, AppSchedulerManager appSchedulerManager) {
        this.view = view;
        provider = new WeatherForecastProvider();
        this.appSchedulerManager = appSchedulerManager;
    }

    void getWeatherReport() {
        provider.getWeatherForecast()
                .subscribeOn(appSchedulerManager.io)
                .observeOn(appSchedulerManager.mainThread)
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
        ArrayList<WeatherForecastDto.WeatherInfoDto> futureWeather = dto.getWeatherInfo();
        futureWeather.remove(0);
        return futureWeather;
    }
}
