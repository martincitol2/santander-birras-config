package com.challenge.santander.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetUpUserDTO {

    @NotNull
    private Long meetUpId;

    @NotNull
    private Long userId;

    @NotNull
    private Date userMeetUpDate;

    @NotNull
    private boolean userAttended;
}
