package com.example.weatherprovider.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RequestCommand {
    String cityName;
}
