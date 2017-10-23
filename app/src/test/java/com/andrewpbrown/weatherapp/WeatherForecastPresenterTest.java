package com.andrewpbrown.weatherapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import rx.Observable;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherForecastPresenterTest {

    @Mock
    WeatherForecastView mockView;

    @Mock
    WeatherForecastProvider mockProvider;

    @Mock
    WeatherForecastDto mockDto;

    @Mock
    ArrayList<WeatherForecastDto.WeatherInfoDto> mockInfoDto;

    WeatherForecastPresenter presenter;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        presenter = new WeatherForecastPresenter(mockView, AppSchedulerManager.getTestSchedulerManager());
        presenter.provider = mockProvider;
        when(mockProvider.getWeatherForecast()).thenReturn(Observable.just(mockDto));
        when(mockDto.getWeatherInfo()).thenReturn(mockInfoDto);
    }

    @Test
    public void givenSuccess_whenGetWeatherReport_thenTodaysWeatherSet() {
        presenter.getWeatherReport();

        verify(mockView).displayTodaysWeather(Matchers.eq(mockDto));
    }

    @Test
    public void givenSuccess_whenGetWeatherReport_thenFutureWeatherSet() {
        presenter.getWeatherReport();

        verify(mockView).displayFutureWeather(Matchers.eq(mockInfoDto));
    }

    @Test
    public void givenError_whenGetWeatherReport_thenErrorShownAndWeatherNotLoaded() {
        when(mockProvider.getWeatherForecast()).thenReturn(Observable.error(new IllegalStateException()));
        presenter.getWeatherReport();

        verify(mockView, never()).displayFutureWeather(any());
        verify(mockView, never()).displayTodaysWeather(any());
        verify(mockView).displayError();
    }
}