package com.challenge.santander.mapper;

import com.challenge.santander.model.dto.TemperatureMeetUpDTO;
import com.challenge.santander.persistence.entity.TemperatureMeetUpEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemperatureMeetUpMapper {

    TemperatureMeetUpDTO sourceToDestination(TemperatureMeetUpEntity temperatureMeetUpEntity);
}
