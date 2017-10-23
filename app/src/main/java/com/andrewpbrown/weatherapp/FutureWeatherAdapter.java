package com.andrewpbrown.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FutureWeatherAdapter extends ArrayAdapter<WeatherForecastDto.WeatherInfoDto> {

    public FutureWeatherAdapter(Context context, ArrayList<WeatherForecastDto.WeatherInfoDto> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WeatherForecastDto.WeatherInfoDto dto = getItem(position);
        WeatherForecastDto.Weather weatherInfo = dto.weather.get(0);
        // Check if an existing view is being reused, otherwise inflate the view
        Context context = getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.future_weather, parent, false);
        }

        ImageView weatherIcon = (ImageView) convertView.findViewById(R.id.weather_icon);
        weatherIcon.setImageDrawable(CustomUtils.getImageResource(context, weatherInfo.getIcon()));

        TextView dateTextView = (TextView) convertView.findViewById(R.id.date);
        dateTextView.setText(dto.dateTimeString);
        TextView weatherTextView = (TextView) convertView.findViewById(R.id.weather);
        weatherTextView.setText(weatherInfo.description);
        TextView windSpeedTextView = (TextView) convertView.findViewById(R.id.wind_speed);
        windSpeedTextView.setText(String.format("%.2fmph", CustomUtils.convertWeatherToMph(dto.wind.speed)));
        TextView temperatureTextView = (TextView) convertView.findViewById(R.id.temperature);
        temperatureTextView.setText(String.format("%.0f°C - %.0f°C", dto.main.minTemp, dto.main.maxTemp));

        return convertView;
    }
}