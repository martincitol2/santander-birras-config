package com.challenge.santander.service.impl;

import com.challenge.santander.domain.util.EncryptUtil;
import com.challenge.santander.exception.MeetUpException;
import com.challenge.santander.exception.MeetUpUserException;
import com.challenge.santander.exception.TemperatureMeetUpException;
import com.challenge.santander.mapper.MeetUpMapper;
import com.challenge.santander.mapper.TemperatureMeetUpMapper;
import com.challenge.santander.mapper.TemperatureParametryMapper;
import com.challenge.santander.mapper.UserMapper;
import com.challenge.santander.model.dto.*;
import com.challenge.santander.persistence.entity.*;
import com.challenge.santander.persistence.repository.*;
import com.challenge.santander.service.MeetUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MeetUpServiceImpl implements MeetUpService {

    private MeetUpRepository meetUpRepository;

    private MeetUpUserRepository meetUpUserRepository;

    private UserRepository userRepository;

    private TemperatureMeetUpRepository temperatureMeetUpRepository;

    private UserMapper userMapper;

    private MeetUpMapper meetUpMapper;

    private TemperatureMeetUpMapper temperatureMeetUpMapper;

    private TemperatureParametryRepository temperatureParametryRepository;

    private TemperatureParametryMapper temperatureParametryMapper;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        userDTO.setPassword(EncryptUtil.encode(userDTO.getPassword()));
       UserEntity userEntity = userRepository.save(userMapper.sourceToDestination(userDTO));
       return userMapper.sourceToDestination(userEntity);
    }

    @Override
    public UserDTO getUser(String userName) {
        UserEntity userEntity = userRepository.findUserEntityByUserName(userName);
        return null != userEntity ? userMapper.sourceToDestination(userEntity) : null;
    }

    @Override
    public MeetUpDTO addMeetUp(MeetUpDTO meetUpDTO) {
        MeetUpEntity meetUpEntity = meetUpRepository.findMeetUpEntityByDirectionAndMeetUpDate(meetUpDTO.getDirection(),meetUpDTO.getMeetUpDate());
        if(null == meetUpEntity){
            meetUpEntity = meetUpRepository.save(meetUpMapper.sourceToDestination(meetUpDTO));
            return meetUpMapper.sourceToDestination(meetUpEntity);
        }
        return null;
    }

    @Override
    public MeetUpDTO getMeetUp(MeetUpDTO meetUpDTO) {
        MeetUpEntity meetUpEntity = meetUpRepository.findMeetUpEntityByDirectionAndMeetUpDate(meetUpDTO.getDirection(),meetUpDTO.getMeetUpDate());
        return null != meetUpEntity ? meetUpMapper.sourceToDestination(meetUpEntity) : null;
    }

    @Override
    public MeetUpUserDTO addMeetUpUser(MeetUpUserCreateDTO meetUpUserCreateDTO) throws MeetUpUserException {
        MeetUpEntity meetUpEntity = meetUpRepository.findMeetUpEntityByDirectionAndMeetUpDate
                (meetUpUserCreateDTO.getMeetUp().getDirection(),meetUpUserCreateDTO.getMeetUp().getMeetUpDate());
        UserEntity userEntity = userRepository.findUserEntityByUserName(meetUpUserCreateDTO.getUserName());
        if(null != userEntity && null != meetUpEntity){
            Optional<MeetUpUserEntity> meetUpUserEntityOptional = meetUpUserRepository
                    .findMeetUpUserEntityByUserAndMeetUp(userEntity,meetUpEntity);
            if(meetUpUserEntityOptional.isEmpty()){
                Date now = new Date();
                MeetUpUserEntity meetUpUserEntity = new MeetUpUserEntity(null,meetUpEntity,userEntity, now,false);
                meetUpUserRepository.save(meetUpUserEntity);
                return new MeetUpUserDTO(meetUpEntity.getId(),userEntity.getId(),now,false);
            }else{
                throw new MeetUpUserException("MeetUpUser Already Exists");
            }
        }
        return null;
    }

    @Override
    public TemperatureMeetUpDTO addTemperatureMeetUp(TemperatureMeetUpDTO temperatureMeetUpDTO) throws MeetUpException, TemperatureMeetUpException {
        MeetUpEntity meetUpEntity = meetUpRepository.findMeetUpEntityByDirectionAndMeetUpDate
                (temperatureMeetUpDTO.getMeetUp().getDirection(),temperatureMeetUpDTO.getMeetUp().getMeetUpDate());
        if(null != meetUpEntity){
            TemperatureMeetUpDTO temperatureMeetUp = getTemperatureMeetUp(meetUpMapper.sourceToDestination(meetUpEntity));
            if(null != temperatureMeetUp){
                throw new TemperatureMeetUpException("TEMPERATURE MEET UP ALREADY EXISTS");
            }else{
                TemperatureMeetUpEntity temperatureMeetUpEntity = new TemperatureMeetUpEntity(null,meetUpEntity,temperatureMeetUpDTO.getMinTemperature()
                        ,temperatureMeetUpDTO.getMaxTemperature(),temperatureMeetUpDTO.getAmountBeers());
                temperatureMeetUpRepository.save(temperatureMeetUpEntity);
                return temperatureMeetUpMapper.sourceToDestination(temperatureMeetUpEntity);
            }
        }else{
            throw new MeetUpException("MEET UP NOT EXISTS");
        }
    }

    @Override
    public TemperatureParametryDTO addTemperatureParametry(TemperatureParametryDTO temperatureParametryDTO) {
        Optional<TemperatureParametryEntity> temperatureParametry = temperatureParametryRepository.findById(1L);
        if(temperatureParametry.isPresent()) {
            TemperatureParametryEntity temperatureParametryEntity = temperatureParametry.get();
            temperatureParametryEntity.setMinimumTemperature(temperatureParametryDTO.getMinimumTemperature());
            temperatureParametryEntity.setMaximumTemperature(temperatureParametryDTO.getMaximumTemperature());
            temperatureParametryEntity.setBeersByPerson(temperatureParametryDTO.getBeersByPerson());
            temperatureParametryRepository.save(temperatureParametryEntity);
            return temperatureParametryMapper.sourceToDestination(temperatureParametryEntity);
        }else{
            temperatureParametryRepository.save(temperatureParametryMapper.sourceToDestination(temperatureParametryDTO));
            return temperatureParametryDTO;
        }
    }

    @Override
    public List<MeetUpDTO> getAllMeetUp(){
        List<MeetUpEntity> meetUpEntities = meetUpRepository.findAll();
        return meetUpEntities.isEmpty() ? null : meetUpMapper.sourceToDestination(meetUpEntities);
    }

    @Override
    public MeetUpUserDTO checkIn(MeetUpUserCreateDTO meetUpUserCreateDTO) throws MeetUpUserException {
        UserEntity userEntity = userRepository.findUserEntityByUserName(meetUpUserCreateDTO.getUserName());
        MeetUpEntity meetUpEntity = meetUpRepository.findMeetUpEntityByDirectionAndMeetUpDate(meetUpUserCreateDTO.getMeetUp().getDirection(),meetUpUserCreateDTO.getMeetUp().getMeetUpDate());
        if(null == userEntity || null == meetUpEntity){
            throw new MeetUpUserException("MEETUP OR USER DOESNT EXISTS");
        }
        Optional<MeetUpUserEntity> meetUpUser = meetUpUserRepository.findMeetUpUserEntityByUserAndMeetUp(userEntity,meetUpEntity);
        if(meetUpUser.isPresent()){
            if(!meetUpUser.get().isUserAttended()){
                MeetUpUserEntity meetUpUserEntity = meetUpUser.get();
                meetUpUserEntity.setUserAttended(true);
                meetUpUserRepository.save(meetUpUserEntity);
                return new MeetUpUserDTO(meetUpEntity.getId(),userEntity.getId(),meetUpUser.get().getUserMeetUpDate(),meetUpUser.get().isUserAttended());
            }
            throw new MeetUpUserException("MEETUP USER ALREADY CHECK IN");
        }
        return null;
    }

    @Override
    public TemperatureMeetUpDTO getTemperatureMeetUp(MeetUpDTO meetUpDTO) throws MeetUpException {
        MeetUpEntity meetUpEntity = meetUpRepository.findMeetUpEntityByDirectionAndMeetUpDate(meetUpDTO.getDirection(),meetUpDTO.getMeetUpDate());
        if(null != meetUpEntity){
            TemperatureMeetUpEntity temperatureMeetUpEntity = temperatureMeetUpRepository.findTemperatureMeetUpEntityByMeetUp(meetUpEntity);
            if(null != temperatureMeetUpEntity){
                return temperatureMeetUpMapper.sourceToDestination(temperatureMeetUpEntity);
            }
            return null;
        }else{
            throw new MeetUpException("MEET UP NOT EXISTS");
        }
    }

    @Override
    public TemperatureParametryDTO getTemperatureParametry() {
        Optional<TemperatureParametryEntity> temperatureParametryEntity = temperatureParametryRepository.findById(1L);
        return temperatureParametryEntity.map(parametryEntity -> temperatureParametryMapper.sourceToDestination(parametryEntity)).orElse(null);
    }

    @Override
    public MeetUpUsersAmountDTO amountUserSubscriptions(MeetUpDTO meetUpDTO) {
        MeetUpEntity meetUpEntity = meetUpRepository.findMeetUpEntityByDirectionAndMeetUpDate(meetUpDTO.getDirection(),meetUpDTO.getMeetUpDate());
        return null != meetUpEntity ? new MeetUpUsersAmountDTO(meetUpEntity.getMeetUpUsers().size()) : null;
    }

    @Autowired
    public void setMeetUpRepository(MeetUpRepository meetUpRepository) {
        this.meetUpRepository = meetUpRepository;
    }

    @Autowired
    public void setMeetUpUserRepository(MeetUpUserRepository meetUpUserRepository) {
        this.meetUpUserRepository = meetUpUserRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setMeetUpMapper(MeetUpMapper meetUpMapper) {
        this.meetUpMapper = meetUpMapper;
    }

    @Autowired
    public void setTemperatureMeetUpMapper(TemperatureMeetUpMapper temperatureMeetUpMapper) {
        this.temperatureMeetUpMapper = temperatureMeetUpMapper;
    }

    @Autowired
    public void setTemperatureMeetUpRepository(TemperatureMeetUpRepository temperatureMeetUpRepository) {
        this.temperatureMeetUpRepository = temperatureMeetUpRepository;
    }

    @Autowired
    public void setTemperatureParametryRepository(TemperatureParametryRepository temperatureParametryRepository) {
        this.temperatureParametryRepository = temperatureParametryRepository;
    }

    @Autowired
    public void setTemperatureParametryMapper(TemperatureParametryMapper temperatureParametryMapper) {
        this.temperatureParametryMapper = temperatureParametryMapper;
    }
}
