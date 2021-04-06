package com.challenge.santander.service.impl;

import com.challenge.santander.exception.MeetUpUserException;
import com.challenge.santander.exception.TemperatureParametryException;
import com.challenge.santander.exception.WeatherException;
import com.challenge.santander.mapper.WeatherInformationMapper;
import com.challenge.santander.model.*;
import com.challenge.santander.service.MailService;
import com.challenge.santander.service.MeetUpDomainService;
import com.challenge.santander.service.MeetUpService;
import com.challenge.santander.service.WeatherDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MeetUpServiceImpl implements MeetUpService {

    private WeatherDomainService weatherDomainService;

    private WeatherInformationMapper weatherInformationMapper;

    private MeetUpDomainService meetUpDomainService;

    private MailService mailService;

    public WeatherMeetUpInformationDTO getMeetUpWeather(MeetUpDTO meetUpDTO) {
        try {
            WeatherDomainResponseDTO weatherDomainResponseDTO = weatherDomainService.sixteenDaysForecast(meetUpDTO.getLongitude(), meetUpDTO.getLatitude());
            List<WeatherInformationDTO> weatherInformationDTOList = weatherDomainResponseDTO.getData();
            Optional<WeatherInformationDTO> weatherInformationDTO = weatherInformationDTOList.stream().filter(p -> p.datetime.equals(meetUpDTO.getMeetUpDate().toString())).findFirst();
            if (weatherInformationDTO.isPresent()) {
                return weatherInformationMapper.sourceToDestination(weatherInformationDTO.get());
            }
        } catch (Exception e) {
            log.error("Exception Call Weather Domain API -> {}", e.getMessage());
        }
        return null;
    }

    @Override
    public Object addUser(UserDTO user) {
        return meetUpDomainService.addUser(user);
    }

    @Override
    public Object addMeetUp(MeetUpDTO meetUpDTO) {
        return meetUpDomainService.addMeetUp(meetUpDTO);
    }

    @Override
    public List<MeetUpDTO> getAllMeetUp() {
        return meetUpDomainService.getAllMeetUp();
    }

    @Override
    public UserDTO getUser(String userName){
        return meetUpDomainService.getUser(userName);
    }

    @Override
    public Object addMeetUpUser(MeetUpUserCreateDTO meetUpUserCreateDTO) throws MeetUpUserException {
        Object response = null;
        UserDTO userDTO = getUser(meetUpUserCreateDTO.getUserName());
        if(null == userDTO){
            throw new MeetUpUserException("USER DOESNT EXISTS");
        }
        try{
            response = meetUpDomainService.addMeetUpUser(meetUpUserCreateDTO);
            mailService.sendMail(userDTO.getEmail(),meetUpUserCreateDTO);
        }catch(MessagingException e){
            log.error("FAILED TO SEND EMAIL {}",e.getMessage());
        }
        return response;
    }

    @Override
    public Object checkIn(MeetUpUserCreateDTO meetUpUserCreateDTO) {
        return meetUpDomainService.checkIn(meetUpUserCreateDTO);
    }

    @Override
    public TemperatureParametryDTO getTemperatureParametryDTO() {
        return meetUpDomainService.getTemperatureParametry();
    }

    @Override
    public AmountBeerCratesDTO calculateBeerBoxesByMeetUp(MeetUpDTO meetUpDTO) throws WeatherException, TemperatureParametryException, MeetUpUserException {
        WeatherMeetUpInformationDTO weatherMeetUpInformationDTO = getMeetUpWeather(meetUpDTO);
        TemperatureParametryDTO temperatureParametryDTO = getTemperatureParametryDTO();
        MeetUpUsersAmountDTO meetUpUsersAmountDTO = meetUpDomainService.getAmountUserSubscriptions(meetUpDTO);
        if (null == weatherMeetUpInformationDTO) throw new WeatherException("CANNOT OBTAIN WEATHER");
        if (null == temperatureParametryDTO) throw new TemperatureParametryException("CANNOT OBTAIN TEMPERATURE PARAMETRY");
        if (null == meetUpUsersAmountDTO) throw new MeetUpUserException("CANNOT OBTAIN MEET UP USERS AMOUNT");

        if (temperatureParametryDTO.getMaximumTemperature() > weatherMeetUpInformationDTO.getMax_temp()) {
            return new AmountBeerCratesDTO(obtainBeerBoxes(meetUpUsersAmountDTO.getAmountUsersSubscriptions() * temperatureParametryDTO.getBeersByPerson().get(2)));
        } else if (temperatureParametryDTO.getMinimumTemperature() > weatherMeetUpInformationDTO.getMax_temp()) {
            return new AmountBeerCratesDTO(obtainBeerBoxes(meetUpUsersAmountDTO.getAmountUsersSubscriptions() * temperatureParametryDTO.getBeersByPerson().get(0)));
        } else {
            return new AmountBeerCratesDTO(obtainBeerBoxes(meetUpUsersAmountDTO.getAmountUsersSubscriptions() * temperatureParametryDTO.getBeersByPerson().get(1)));
        }
    }

    private Integer obtainBeerBoxes(Double value) {
        return (int) Math.ceil(value / 6);
    }

    @Autowired
    public void setWeatherDomainService(WeatherDomainService weatherDomainService) {
        this.weatherDomainService = weatherDomainService;
    }

    @Autowired
    public void setWeatherInformationMapper(WeatherInformationMapper weatherInformationMapper) {
        this.weatherInformationMapper = weatherInformationMapper;
    }

    @Autowired
    public void setMeetUpDomainService(MeetUpDomainService meetUpDomainService) {
        this.meetUpDomainService = meetUpDomainService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
