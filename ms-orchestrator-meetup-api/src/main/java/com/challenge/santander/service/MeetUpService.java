package com.challenge.santander.service;

import com.challenge.santander.exception.MeetUpUserException;
import com.challenge.santander.exception.TemperatureParametryException;
import com.challenge.santander.exception.WeatherException;
import com.challenge.santander.model.*;

import java.util.List;

public interface MeetUpService {
    public WeatherMeetUpInformationDTO getMeetUpWeather(MeetUpDTO meetUpDTO);

    public Object addUser(UserDTO user);

    public Object addMeetUp(MeetUpDTO meetUpDTO);

    public List<MeetUpDTO> getAllMeetUp();

    public Object addMeetUpUser(MeetUpUserCreateDTO meetUpUserCreateDTO) throws MeetUpUserException;

    public Object checkIn(MeetUpUserCreateDTO meetUpUserCreateDTO);

    public AmountBeerCratesDTO calculateBeerBoxesByMeetUp(MeetUpDTO meetUpDTO) throws WeatherException, TemperatureParametryException, MeetUpUserException;

    public TemperatureParametryDTO getTemperatureParametryDTO();

    public UserDTO getUser(String userName);
}
