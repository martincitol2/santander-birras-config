package com.challenge.santander.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class WeatherConfig {

    @Value("${weather.path}")
    private String weatherPath;

    @Value("${weather.config.x-rapidapi-key}")
    private String rapidapiKey;

    @Value("${weather.config.x-rapidapi-host}")
    private String rapidapiHost;
}
