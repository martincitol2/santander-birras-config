package com.challenge.santander.service.impl;

import com.challenge.santander.feign.client.MeetUpDomainClient;
import com.challenge.santander.model.*;
import com.challenge.santander.service.MeetUpDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MeetUpDomainServiceImpl implements MeetUpDomainService {

    private MeetUpDomainClient meetUpDomainClient;

    @Override
    public Object addUser(UserDTO userDTO) {
        ResponseEntity<?> responseEntity = meetUpDomainClient.addUser(userDTO);
        return responseEntity.getStatusCode().is2xxSuccessful() ? null : responseEntity.getBody();
    }

    @Override
    public List<MeetUpDTO> getAllMeetUp() {
        ResponseEntity<List<MeetUpDTO>> responseEntity = meetUpDomainClient.getAllMeetUp();
        return responseEntity.getStatusCode().is2xxSuccessful() ? responseEntity.getBody() : null;
    }

    @Override
    public Object addMeetUp(MeetUpDTO meetUpDTO) {
        ResponseEntity<?> responseEntity = meetUpDomainClient.addMeetUp(meetUpDTO);
        return responseEntity.getStatusCode().is2xxSuccessful() ? null : responseEntity.getBody();
    }

    @Override
    public MeetUpDTO getMeetUp(MeetUpDTO meetUpDTO) {
        return null;
    }

    @Override
    public Object addMeetUpUser(MeetUpUserCreateDTO meetUpUserCreateDTO) {
        ResponseEntity<?> responseEntity = meetUpDomainClient.addMeetUpUser(meetUpUserCreateDTO);
        return responseEntity.getStatusCode().is2xxSuccessful() ? null : responseEntity.getBody();
    }

    @Override
    public TemperatureMeetUpDTO getTemperatureMeetUp(MeetUpDTO meetUpDTO) {
        return null;
    }

    @Override
    public TemperatureParametryDTO getTemperatureParametry() {
        return meetUpDomainClient.getTemperatureParametry().getBody();
    }

    @Override
    public TemperatureMeetUpDTO addTemperatureMeetUp(TemperatureMeetUpDTO temperatureMeetUpDTO) {
        return null;
    }

    @Override
    public TemperatureParametryDTO addTemperatureParametry(TemperatureParametryDTO temperatureParametryDTO) {
        return null;
    }

    @Override
    public MeetUpUsersAmountDTO getAmountUserSubscriptions(MeetUpDTO meetUpDTO){
        return meetUpDomainClient.amountUserSubscriptions(meetUpDTO).getBody();
    }

    @Override
    public UserDTO getUser(String userName){
        return meetUpDomainClient.getUser(userName).getBody();
    }

    @Override
    public Object recover(Exception e, String userName) {
        return null;
    }

    @Override
    public Object checkIn(MeetUpUserCreateDTO meetUpUserCreateDTO) {
        ResponseEntity<?> responseEntity = meetUpDomainClient.addMeetUpUser(meetUpUserCreateDTO);
        return responseEntity.getStatusCode().is2xxSuccessful() ? null : responseEntity.getBody();
    }

    @Override
    public Object recover(Exception e, UserDTO userDTO){
        log.error("exception.meetup.recover.call", e);
        return e.getMessage();
    }

    @Override
    public Object recover(Exception e,MeetUpDTO meetUpDTO) {
        log.error("exception.meetup.recover.call", e);
        return e.getMessage();
    }

    @Override
    public Object recover(Exception e,MeetUpUserCreateDTO meetUpUserCreateDTO) {
        log.error("exception.meetup.recover.call", e);
        return e.getMessage();
    }

    @Override
    public TemperatureMeetUpDTO recover(Exception e,TemperatureMeetUpDTO temperatureMeetUpDTO) {
        log.error("exception.meetup.recover.call", e);
        return null;
    }

    @Override
    public TemperatureParametryDTO recover(Exception e,TemperatureParametryDTO temperatureParametryDTO) {
        log.error("exception.meetup.recover.call", e);
        return null;
    }

    @Autowired
    public void setMeetUpDomainClient(MeetUpDomainClient meetUpDomainClient) {
        this.meetUpDomainClient = meetUpDomainClient;
    }
}
