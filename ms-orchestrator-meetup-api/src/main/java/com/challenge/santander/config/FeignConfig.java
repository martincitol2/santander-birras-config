package com.challenge.santander.config;

import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.okhttp.OkHttpClient;

public class FeignConfig {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}