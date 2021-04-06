package com.challenge.santander.controller.impl;

import com.challenge.santander.controller.WeatherDomainController;
import com.challenge.santander.model.WeatherDomainResponseDTO;
import com.challenge.santander.service.WeatherDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherDomainControllerImpl implements WeatherDomainController {

    private WeatherDomainService weatherDomainService;

    @Override
    @GetMapping("/16-day-forecast")
    public ResponseEntity<WeatherDomainResponseDTO> currentWeather(@RequestParam Double longitude,@RequestParam Double latitude) {
        ResponseEntity<WeatherDomainResponseDTO> ret;
        WeatherDomainResponseDTO response = weatherDomainService.currentWeather(longitude,latitude);
        if(response != null){
            ret = new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            ret = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return ret;
    }

    @Autowired
    public void setWeatherDomainService(WeatherDomainService weatherDomainService) {
        this.weatherDomainService = weatherDomainService;
    }
}
