package com.example.weatherprovider;

import com.example.weatherprovider.api.WeatherController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class WeatherProviderApplicationTests {

    @Autowired
    private WeatherController weatherController;

    @Test
    void contextLoads() {
        Assert.notNull(weatherController);
    }

}
