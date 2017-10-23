package com.andrewpbrown.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
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
    TextView todaysWeather;

    @BindView(R.id.wind_speed)
    TextView todaysWindSpeed;

    @BindView(R.id.humidity)
    TextView todaysHumidity;

    @BindView(R.id.temperature_max)
    TextView todaysMinTemp;

    @BindView(R.id.temperature_min)
    TextView todaysMaxTemp;

    @BindView(R.id.future_weather_list)
    ListView futureWeatherListView;

    private WeatherForecastPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        presenter = new WeatherForecastPresenter(this);
        presenter.getWeatherReport();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_forcast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayError() {

    }

    @Override
    public void displayTodaysWeather(WeatherForecastDto dto) {
        WeatherForecastDto.WeatherInfoDto weatherDto = dto.weatherInformation.get(0);
        todaysWeather.setText(weatherDto.weather.get(0).description);
        todaysWindSpeed.setText(String.format("%.2fmph", CustomUtils.convertWeatherToMph(weatherDto.wind.speed)));
        todaysHumidity.setText(weatherDto.main.humidity + "%");
        todaysMinTemp.setText(weatherDto.main.minTemp + "°C");
        todaysMaxTemp.setText(weatherDto.main.maxTemp + "°C");
        weatherIcon.setImageDrawable(CustomUtils.getImageResource(this, weatherDto.weather.get(0).getIcon()));
    }

    @Override
    public void displayFutureWeather(ArrayList<WeatherForecastDto.WeatherInfoDto> futureWeatherList) {
        FutureWeatherAdapter adapter = new FutureWeatherAdapter(this, futureWeatherList);
        futureWeatherListView.setAdapter(adapter);
    }
}
