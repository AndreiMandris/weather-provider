package com.example.weatherprovider.service;

import com.example.weatherprovider.api.RequestCommand;
import com.example.weatherprovider.api.WeatherDto;
import com.example.weatherprovider.infrastructure.WeatherPersistenceRequestSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeferredResultManager {

    private final WeatherProvider weatherProvider;
    private final WeatherPersistenceRequestSender weatherPersistenceRequestSender;

    public void supplyAsyncWeatherDto(DeferredResult<ResponseEntity<WeatherDto>> deferredResult, RequestCommand command) {
        try {
            WeatherDto weatherDto = weatherProvider.getWeatherByCity(command.getCityName());
            weatherPersistenceRequestSender.sendRequest(weatherDto);
            deferredResult.setResult(ResponseEntity.ok(weatherDto));
        } catch (Exception e) {
            log.error("Exception thrown while trying to retrieve weather data.", e);
            deferredResult.setErrorResult(e);
        }
    }
}
