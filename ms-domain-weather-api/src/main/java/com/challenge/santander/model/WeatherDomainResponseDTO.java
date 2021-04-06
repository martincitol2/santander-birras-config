package com.challenge.santander.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherDomainResponseDTO {

    public List<WeatherInformationDTO> data;
    public String city_name;
    public double lon;
    public String timezone;
    public double lat;
    public String country_code;
    public String state_code;
}
