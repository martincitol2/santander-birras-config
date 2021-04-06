package com.challenge.santander.controller;

import com.challenge.santander.model.WeatherDomainResponseDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Weather API: ", description = "The Weather Api Solution")
public interface WeatherDomainController {

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    ResponseEntity<WeatherDomainResponseDTO> currentWeather(@RequestParam Double longitude, @RequestParam Double latitude);
}
