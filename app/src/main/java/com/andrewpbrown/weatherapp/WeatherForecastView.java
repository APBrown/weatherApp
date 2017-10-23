package com.andrewpbrown.weatherapp;

import java.util.ArrayList;

interface WeatherForecastView {

    void displayError();

    void displayTodaysWeather(WeatherForecastDto dto);

    void displayFutureWeather(ArrayList<WeatherForecastDto.WeatherInfoDto> futureWeatherList);
}
