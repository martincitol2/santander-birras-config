package com.challenge.santander.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureParametryDTO {

    @NotNull
    private Double minimumTemperature;

    @NotNull
    private Double maximumTemperature;

    @NotNull
    private List<Double> beersByPerson;

    @NotNull
    private Integer beersPerBox;
}
