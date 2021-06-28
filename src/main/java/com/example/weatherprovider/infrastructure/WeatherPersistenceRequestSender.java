package com.example.weatherprovider.infrastructure;

import com.example.weatherprovider.api.WeatherDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherPersistenceRequestSender {
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.weather-queue}")
    private String weatherQueue;

    public void sendRequest(WeatherDto weatherDto) {
        queueMessagingTemplate.convertAndSend(weatherQueue, weatherDto);
        log.info("Requested weather persistence for object: {}", weatherDto);
    }
}
