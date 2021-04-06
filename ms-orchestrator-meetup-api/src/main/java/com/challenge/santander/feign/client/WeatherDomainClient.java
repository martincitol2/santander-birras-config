package com.challenge.santander.feign.client;

import com.challenge.santander.config.FeignConfig;
import com.challenge.santander.model.WeatherDomainResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="weatherClient", value = "weatherClient", url = "${feign.path.weather}", configuration = FeignConfig.class)
public interface WeatherDomainClient {

    @GetMapping("/weather/16-day-forecast")
    public ResponseEntity<WeatherDomainResponseDTO> sixteenDaysForecast(@RequestParam Double longitude,@RequestParam Double latitude);
}
