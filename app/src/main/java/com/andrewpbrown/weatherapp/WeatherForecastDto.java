package com.andrewpbrown.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class WeatherForecastDto {

    @SerializedName("list")
    ArrayList<WeatherInfoDto> weatherInformation;

    class WeatherInfoDto {

        @SerializedName("main")
        Main main;

        @SerializedName("weather")
        List<Weather> weather;

        @SerializedName("wind")
        Wind wind;

        @SerializedName("dt_txt")
        String dateTimeString;
    }

    class Main {

        @SerializedName("humidity")
        int humidity;

        @SerializedName("temp_min")
        double minTemp;

        @SerializedName("temp_max")
        double maxTemp;
    }

    class Weather {

        @SerializedName("description")
        String description;

        @SerializedName("icon")
        String iconImage;

        String getIcon() {
            return "icon_" + iconImage;
        }
    }

    class Wind {

        @SerializedName("speed")
        double speed;
    }
}
/**
 {
 "city":{
    "id":1851632,
    "name":"Shuzenji",
    "coord":{
        "lon":138.933334,
        "lat":34.966671
        },
    "country":"JP",
    "list":[
    {
        "dt":1406106000,
        "main":{
            "temp":298.77,
            "temp_min":298.77,
            "temp_max":298.774,
            "pressure":1005.93,
            "sea_level":1018.18,
            "grnd_level":1005.93,
            "humidity":87
            "temp_kf":0.26
         },
        "weather":[
        {
            "id":804,
            "main":"Clouds",
            "description":"overcast clouds",
            "icon":"04d"
        }],
        "clouds":{
            "all":88
        },
        "wind":{
            "speed":5.71,
            "deg":229.501
        },
        "sys":{
            "pod":"d"
        },
        "dt_txt":"2014-07-23 09:00:00"
    }]
 }
 */

/**
 * code Internal parameter
 message Internal parameter
 city
    city.id City ID
    city.name City name
    city.coord
    city.coord.lat City geo location, latitude
    city.coord.lon City geo location, longitude
    city.country Country code (GB, JP etc.)
    cnt Number of lines returned by this API call
    list
        list.dt Time of data forecasted, unix, UTC
        list.main
            list.main.temp Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
            list.main.temp_min Minimum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
            list.main.temp_max Maximum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
            list.main.pressure Atmospheric pressure on the sea level by default, hPa
            list.main.sea_level Atmospheric pressure on the sea level, hPa
            list.main.grnd_level Atmospheric pressure on the ground level, hPa
            list.main.humidity Humidity, %
            list.main.temp_kf Internal parameter
        list.weather (more info Weather condition codes)
            list.weather.id Weather condition id
            list.weather.main Group of weather parameters (Rain, Snow, Extreme etc.)
            list.weather.description Weather condition within the group
            list.weather.icon Weather icon id
        list.clouds
            list.clouds.all Cloudiness, %
        list.wind
            list.wind.speed Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
            list.wind.deg Wind direction, degrees (meteorological)
        list.rain
            list.rain.3h Rain volume for last 3 hours, mm
        list.snow
            list.snow.3h Snow volume for last 3 hours
        list.dt_txt Data/time of calculation, UTC
 */
