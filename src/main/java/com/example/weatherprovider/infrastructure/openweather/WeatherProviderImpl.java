package com.example.weatherprovider.infrastructure.openweather;

import com.example.weatherprovider.api.WeatherDto;
import com.example.weatherprovider.api.openweather.WeatherResponse;
import com.example.weatherprovider.config.OpenWeatherConfigProp;
import com.example.weatherprovider.service.WeatherProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.weatherprovider.infrastructure.mapper.WeatherDtoMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherProviderImpl implements WeatherProvider {

    private final OpenWeatherConfigProp openWeatherConfigProp;
    private final RestTemplate restTemplate;

    @Override
    @Cacheable(value = "weather-cache", key = "#city")
    @Retryable(value = HttpServerErrorException.class,
            backoff = @Backoff(delay = 100L, maxDelay = 1500L, random = true, multiplier = 0.3))
    public WeatherDto getWeatherByCity(String city) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(openWeatherConfigProp.getUrl())
                .queryParam("q", city)
                .queryParam("appid", openWeatherConfigProp.getKey())
                .queryParam("units", "metric");

        log.info("Calling {} ", uriComponentsBuilder.toUriString());
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(
                uriComponentsBuilder.toUriString(),
                WeatherResponse.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            WeatherDto weatherDto = WeatherDtoMapper.toWeatherDto(response.getBody());
            log.info("Received {} ", weatherDto);
            return weatherDto;
        } else {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
