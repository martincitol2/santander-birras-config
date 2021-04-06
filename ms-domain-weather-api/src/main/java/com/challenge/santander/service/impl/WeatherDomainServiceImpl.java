package com.challenge.santander.service.impl;

import com.challenge.santander.config.WeatherConfig;
import com.challenge.santander.config.WebClientComponent;
import com.challenge.santander.domain.utils.Constants;
import com.challenge.santander.model.WeatherDomainResponseDTO;
import com.challenge.santander.service.WeatherDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Consumer;

@Service
public class WeatherDomainServiceImpl implements WeatherDomainService {

    private WeatherConfig weatherConfig;

    private WebClientComponent webClientComponent;

    @Override
    public WeatherDomainResponseDTO currentWeather(Double longitude, Double latitude) {
        WebClient webClient = webClientComponent.webClientBuilder(currentPath(longitude,latitude), weatherHeaders(), Constants.SANTANDER);
        ClientResponse clientResponse = webClientComponent.getResponse(webClient,currentPath(longitude,latitude),5000);
        if(clientResponse.statusCode().is2xxSuccessful()){
            return clientResponse.bodyToMono(WeatherDomainResponseDTO.class).block();
        }
        return null;
    }

    private String currentPath(Double longitude, Double latitude){
        return weatherConfig.getWeatherPath()
                .concat("?lon=")
                .concat(String.valueOf(longitude)).concat("&lat=")
                .concat(String.valueOf(latitude));
    }

    private Consumer<HttpHeaders> weatherHeaders() {
        return headers -> {
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            headers.set(Constants.RAPIDAPI_KEY, weatherConfig.getRapidapiKey());
            headers.set(Constants.RAPIDAPI_HOST, weatherConfig.getRapidapiHost());
        };
    }

    @Autowired
    public void setWeatherConfig(WeatherConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
    }

    @Autowired
    public void setWebClientComponent(WebClientComponent webClientComponent) {
        this.webClientComponent = webClientComponent;
    }

}
