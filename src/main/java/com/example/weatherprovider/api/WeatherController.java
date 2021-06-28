package com.example.weatherprovider.api;

import com.example.weatherprovider.service.DeferredResultManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final DeferredResultManager deferredResultManager;

    @GetMapping
    public DeferredResult<ResponseEntity<WeatherDto>> getWeatherByCity(@RequestParam("cityName") String cityName) {
        DeferredResult<ResponseEntity<WeatherDto>> deferredResult = new DeferredResult<>();
        RequestCommand.builder().cityName(cityName).build();
        CompletableFuture.runAsync(() -> deferredResultManager.supplyAsyncWeatherDto(deferredResult,
                RequestCommand.builder().cityName(cityName).build()));
        return deferredResult;
    }
}
