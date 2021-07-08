package com.example.weatherprovider;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
@EnableEncryptableProperties
@ConfigurationPropertiesScan("com.example.weatherprovider.config")
public class WeatherProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherProviderApplication.class, args);
    }

}
