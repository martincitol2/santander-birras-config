package com.challenge.santander.mapper;

import com.challenge.santander.model.WeatherInformationDTO;
import com.challenge.santander.model.WeatherMeetUpInformationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherInformationMapper {

    WeatherMeetUpInformationDTO sourceToDestination(WeatherInformationDTO weatherInformationDTO);
}
