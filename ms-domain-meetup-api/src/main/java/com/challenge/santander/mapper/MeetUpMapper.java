package com.challenge.santander.mapper;

import com.challenge.santander.model.dto.MeetUpDTO;
import com.challenge.santander.persistence.entity.MeetUpEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeetUpMapper {

    MeetUpDTO sourceToDestination(MeetUpEntity meetUpEntity);
    MeetUpEntity sourceToDestination(MeetUpDTO meetUpDTO);
    List<MeetUpDTO> sourceToDestination(List<MeetUpEntity> meetUpEntities);
}
