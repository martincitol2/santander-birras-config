package com.challenge.santander.service;

import com.challenge.santander.model.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface MeetUpDomainService {

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public Object addUser(UserDTO userDTO);

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public List<MeetUpDTO> getAllMeetUp();

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public Object addMeetUp(MeetUpDTO meetUpDTO);

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public MeetUpDTO getMeetUp(MeetUpDTO meetUpDTO);

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public Object addMeetUpUser(MeetUpUserCreateDTO meetUpUserCreateDTO);

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public TemperatureMeetUpDTO getTemperatureMeetUp(MeetUpDTO meetUpDTO);

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public TemperatureParametryDTO getTemperatureParametry();

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public TemperatureMeetUpDTO addTemperatureMeetUp(TemperatureMeetUpDTO temperatureMeetUpDTO);

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public TemperatureParametryDTO addTemperatureParametry(TemperatureParametryDTO temperatureParametryDTO);

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public Object checkIn(MeetUpUserCreateDTO meetUpUserCreateDTO);

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public MeetUpUsersAmountDTO getAmountUserSubscriptions(MeetUpDTO meetUpDTO);

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.meetup.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.meetup.delay}}", multiplierExpression = "#{${meetup.domain-api.meetup.multiplier}}"))
    public UserDTO getUser(String userName);

    @Recover
    public Object recover(Exception e, String userName);

    @Recover
    public Object recover(Exception e, UserDTO userDTO);

    @Recover
    public Object recover(Exception e,MeetUpDTO meetUpDTO);

    @Recover
    public Object recover(Exception e,MeetUpUserCreateDTO meetUpUserCreateDTO);

    @Recover
    public Object recover(Exception e,TemperatureMeetUpDTO temperatureMeetUpDTO);

    @Recover
    public Object recover(Exception e,TemperatureParametryDTO temperatureParametryDTO);
}
