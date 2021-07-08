package com.example.weatherprovider.service;

import com.example.weatherprovider.api.RequestCommand;
import com.example.weatherprovider.infrastructure.WeatherPersistenceRequestSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.async.DeferredResult;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class DeferredResultManagerTest {

    private static final String CITY_NAME = "city";

    @InjectMocks
    private DeferredResultManager deferredResultManager;

    @Mock
    private WeatherProvider weatherProvider;

    @Mock
    private WeatherPersistenceRequestSender weatherPersistenceRequestSender;

    @Test
    public void testSupplyAsyncWeatherDto() {
        deferredResultManager.supplyAsyncWeatherDto(new DeferredResult<>(), RequestCommand.builder().cityName(CITY_NAME).build());
        Mockito.verify(weatherProvider).getWeatherByCity(CITY_NAME);
        Mockito.verify(weatherPersistenceRequestSender).sendRequest(any());
    }
}