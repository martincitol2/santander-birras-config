package com.challenge.santander.service;

import com.challenge.santander.exception.WeatherException;
import com.challenge.santander.model.WeatherDomainResponseDTO;
import feign.FeignException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface WeatherDomainService {

    @Retryable(value = {WeatherException.class, FeignException.class, Exception.class}, maxAttemptsExpression = "#{${meetup.domain-api.weather.attempts}}", backoff = @Backoff(delayExpression = "#{${meetup.domain-api.weather.delay}}", multiplierExpression = "#{${meetup.domain-api.weather.multiplier}}"))
    public WeatherDomainResponseDTO sixteenDaysForecast(Double longitude, Double latitude) throws WeatherException;

    @Recover
    public WeatherDomainResponseDTO recover(Throwable t);
}
