package com.challenge.santander.mapper;

import com.challenge.santander.model.dto.UserDTO;
import com.challenge.santander.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO sourceToDestination(UserEntity userEntity);
    UserEntity sourceToDestination(UserDTO userDTO);
}
