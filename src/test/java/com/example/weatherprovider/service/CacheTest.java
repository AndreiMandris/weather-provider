package com.example.weatherprovider.service;

import com.example.weatherprovider.service.WeatherProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheTest {

    private final static String BUCHAREST = "Bucharest";
    private final static String OSLO = "Oslo";

    @Autowired
    private WeatherProvider weatherProvider;

    @SpyBean
    private RestTemplate restTemplate;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        cacheManager.getCache("weather-cache").clear();
    }

    @Test
    public void testCachingSameCity() {
        weatherProvider.getWeatherByCity(BUCHAREST);
        weatherProvider.getWeatherByCity(BUCHAREST);
        Mockito.verify(restTemplate).getForEntity(anyString(), any());
    }

    @Test
    public void testCachingMultipleCities() {
        weatherProvider.getWeatherByCity(BUCHAREST);
        weatherProvider.getWeatherByCity(OSLO);
        weatherProvider.getWeatherByCity(OSLO);
        weatherProvider.getWeatherByCity(BUCHAREST);
        Mockito.verify(restTemplate, times(2)).getForEntity(anyString(), any());
    }
}
