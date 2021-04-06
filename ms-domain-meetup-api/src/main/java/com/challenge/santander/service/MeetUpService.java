package com.challenge.santander.service;

import com.challenge.santander.exception.MeetUpException;
import com.challenge.santander.exception.MeetUpUserException;
import com.challenge.santander.exception.TemperatureMeetUpException;
import com.challenge.santander.model.dto.*;

import java.util.List;

public interface MeetUpService {

    public UserDTO addUser(UserDTO userDTO);

    public UserDTO getUser(String userName);

    public MeetUpDTO addMeetUp(MeetUpDTO meetUpDTO);

    public MeetUpDTO getMeetUp(MeetUpDTO meetUpDTO);

    public MeetUpUserDTO addMeetUpUser(MeetUpUserCreateDTO meetUpUserCreateDTO) throws MeetUpUserException;

    public TemperatureMeetUpDTO addTemperatureMeetUp(TemperatureMeetUpDTO temperatureMeetUpDTO) throws MeetUpException, TemperatureMeetUpException;

    public List<MeetUpDTO> getAllMeetUp();

    public MeetUpUserDTO checkIn(MeetUpUserCreateDTO meetUpUserCreateDTO) throws MeetUpUserException;

    public TemperatureMeetUpDTO getTemperatureMeetUp(MeetUpDTO meetUpDTO) throws MeetUpException;

    public TemperatureParametryDTO addTemperatureParametry(TemperatureParametryDTO temperatureParametryDTO) throws MeetUpException, TemperatureMeetUpException;

    public TemperatureParametryDTO getTemperatureParametry();

    public MeetUpUsersAmountDTO amountUserSubscriptions(MeetUpDTO meetUpDTO);
}
