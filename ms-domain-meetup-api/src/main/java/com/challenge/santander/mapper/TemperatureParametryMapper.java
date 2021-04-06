package com.challenge.santander.mapper;

import com.challenge.santander.model.dto.TemperatureParametryDTO;
import com.challenge.santander.persistence.entity.TemperatureParametryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemperatureParametryMapper {

    TemperatureParametryDTO sourceToDestination(TemperatureParametryEntity temperatureParametryEntity);
    TemperatureParametryEntity sourceToDestination(TemperatureParametryDTO temperatureParametryDTO);
}
