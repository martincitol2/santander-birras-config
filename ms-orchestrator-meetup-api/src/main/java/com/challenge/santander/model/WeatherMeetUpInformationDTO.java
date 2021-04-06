package com.challenge.santander.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMeetUpInformationDTO {

    private String datetime;
    private double min_temp;
    private double max_temp;
}
