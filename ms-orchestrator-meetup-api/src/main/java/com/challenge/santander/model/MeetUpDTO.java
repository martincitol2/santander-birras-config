package com.challenge.santander.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetUpDTO {

    @NotNull
    private LocalDate meetUpDate;

    @NotNull
    private String direction;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
