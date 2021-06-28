package com.example.weatherprovider.service;

import com.example.weatherprovider.api.WeatherDto;

public interface WeatherProvider {
    WeatherDto getWeatherByCity(String city);
}
