package com.andrewpbrown.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

class FutureWeatherAdapter extends ArrayAdapter<WeatherForecastDto.WeatherInfoDto> {

    FutureWeatherAdapter(Context context, ArrayList<WeatherForecastDto.WeatherInfoDto> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WeatherForecastDto.WeatherInfoDto dto = getItem(position);
        WeatherForecastDto.WeatherDto weatherInfo = dto.weather.get(0);
        FutureWeatherViewHolder viewHolder;
        Context context = getContext();
        if (convertView != null) {
            viewHolder = new FutureWeatherViewHolder(convertView);
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.future_weather, parent, false);
            viewHolder = new FutureWeatherViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.weatherIcon.setImageDrawable(CustomUtils.getImageResource(context, weatherInfo.getIcon()));
        viewHolder.dateTextView.setText(dto.dateTimeString);
        viewHolder.weatherTextView.setText(weatherInfo.description);
        viewHolder.windSpeedTextView.setText(String.format("%.2fmph", CustomUtils.convertWeatherToMph(dto.wind.speed)));
        viewHolder.temperatureTextView.setText(String.format("%.0f°C - %.0f°C", dto.main.minTemp, dto.main.maxTemp));

        return convertView;
    }

    class FutureWeatherViewHolder {

        @BindView(R.id.weather_icon)
        ImageView weatherIcon;

        @BindView(R.id.date)
        TextView dateTextView;

        @BindView(R.id.weather)
        TextView weatherTextView;

        @BindView(R.id.wind_speed)
        TextView windSpeedTextView;

        @BindView(R.id.temperature)
        TextView temperatureTextView;

        private FutureWeatherViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}