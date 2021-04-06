package com.challenge.santander.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetUpUserCreateDTO {

    @NotNull
    private MeetUpDTO meetUp;

    @NotNull
    private String userName;
}
