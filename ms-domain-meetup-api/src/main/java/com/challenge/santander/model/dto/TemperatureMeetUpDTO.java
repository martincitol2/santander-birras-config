package com.challenge.santander.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureMeetUpDTO {

    @NotNull
    private MeetUpDTO meetUp;

    @NotNull
    private Double minTemperature;

    @NotNull
    private Double maxTemperature;

    @NotNull
    private Integer amountBeers;

}
