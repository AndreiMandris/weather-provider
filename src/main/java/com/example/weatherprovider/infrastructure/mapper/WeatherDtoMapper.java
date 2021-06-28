package com.example.weatherprovider.infrastructure.mapper;

import com.example.weatherprovider.api.WeatherDto;
import com.example.weatherprovider.api.openweather.WeatherResponse;

public class WeatherDtoMapper {

    public static WeatherDto toWeatherDto(WeatherResponse weatherResponse) {
        return WeatherDto.builder()
                .base(weatherResponse.getBase())
                .temperature(weatherResponse.getMain().getTemp())
                .pressure(weatherResponse.getMain().getPressure())
                .humidity(weatherResponse.getMain().getHumidity())
                .windSpeed(weatherResponse.getWind().getSpeed())
                .windDeg(weatherResponse.getWind().getDeg())
                .cloudsAll(weatherResponse.getClouds().getAll())
                .dt(weatherResponse.getDt())
                .cityName(weatherResponse.getName())
                .country(weatherResponse.getSys().getCountry())
                .visibility(weatherResponse.getVisibility())
                .build();
    }
}
