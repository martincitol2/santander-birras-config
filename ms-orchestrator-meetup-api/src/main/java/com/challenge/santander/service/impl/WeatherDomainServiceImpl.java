package com.challenge.santander.service.impl;

import com.challenge.santander.exception.WeatherException;
import com.challenge.santander.feign.client.WeatherDomainClient;
import com.challenge.santander.model.WeatherDomainResponseDTO;
import com.challenge.santander.service.WeatherDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherDomainServiceImpl implements WeatherDomainService {

    private WeatherDomainClient weatherDomainClient;

    @Override
    public WeatherDomainResponseDTO sixteenDaysForecast(Double longitude, Double latitude) throws WeatherException {
        WeatherDomainResponseDTO responseDTO;

        ResponseEntity<WeatherDomainResponseDTO> response = this.weatherDomainClient.sixteenDaysForecast(longitude, latitude);

        if (null != response && response.getStatusCode().equals(HttpStatus.OK)) {
            responseDTO = response.getBody();
        } else {
            throw new WeatherException("exception.weather.domain.call");
        }

        return responseDTO;
    }

    @Override
    public WeatherDomainResponseDTO recover(Throwable t) {
        log.info("exception.weather.recover.call", t);
        return null;
    }

    @Autowired
    public void setWeatherDomainClient(WeatherDomainClient weatherDomainClient) {
        this.weatherDomainClient = weatherDomainClient;
    }
}
