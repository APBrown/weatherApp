package com.andrewpbrown.weatherapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastActivity extends AppCompatActivity implements WeatherForecastView {

    @BindView(R.id.weather_icon)
    ImageView weatherIcon;

    @BindView(R.id.todays_date)
    TextView todaysDate;

    @BindView(R.id.weather)
    TextView todaysWeatherTextView;

    @BindView(R.id.wind_speed)
    TextView todaysWindSpeedTextView;

    @BindView(R.id.humidity)
    TextView todaysHumidityTextView;

    @BindView(R.id.temperature_max)
    TextView todaysMinTempTextView;

    @BindView(R.id.temperature_min)
    TextView todaysMaxTempTextView;

    @BindView(R.id.future_weather_list)
    ListView futureWeatherListView;

    @BindView(R.id.todays_weather)
    View todaysWeather;

    @BindView(R.id.spinner)
    ProgressBar progressBar;

    private WeatherForecastPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        presenter = new WeatherForecastPresenter(this, AppSchedulerManager.getMainSchedulerManager());
        presenter.getWeatherReport();
    }

    @Override
    public void displayError() {
        hideProgressBar();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Something has gone Wrong")
                .setPositiveButton("OK", (dialog, id) -> finish());
        builder.create().show();
    }

    @Override
    public void displayTodaysWeather(WeatherForecastDto dto) {
        WeatherForecastDto.WeatherInfoDto weatherDto = dto.weatherInformation.get(0);
        todaysWeatherTextView.setText(weatherDto.weather.get(0).description);
        todaysWindSpeedTextView.setText(String.format("%.2fmph", CustomUtils.convertWeatherToMph(weatherDto.wind.speed)));
        todaysHumidityTextView.setText(weatherDto.main.humidity + "%");
        todaysMinTempTextView.setText(weatherDto.main.minTemp + "°C");
        todaysMaxTempTextView.setText(weatherDto.main.maxTemp + "°C");
        weatherIcon.setImageDrawable(CustomUtils.getImageResource(this, weatherDto.weather.get(0).getIcon()));
        todaysWeather.setVisibility(View.VISIBLE);
        hideProgressBar();
    }

    @Override
    public void displayFutureWeather(ArrayList<WeatherForecastDto.WeatherInfoDto> futureWeatherList) {
        FutureWeatherAdapter adapter = new FutureWeatherAdapter(this, futureWeatherList);
        futureWeatherListView.setAdapter(adapter);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
