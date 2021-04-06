package com.challenge.santander.service;

import com.challenge.santander.model.WeatherDomainResponseDTO;

public interface WeatherDomainService {

    public WeatherDomainResponseDTO currentWeather(Double longitude, Double latitude);
}
