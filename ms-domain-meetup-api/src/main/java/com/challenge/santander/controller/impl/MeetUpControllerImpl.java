package com.challenge.santander.controller.impl;

import com.challenge.santander.controller.MeetUpController;
import com.challenge.santander.exception.MeetUpException;
import com.challenge.santander.exception.MeetUpUserException;
import com.challenge.santander.exception.TemperatureMeetUpException;
import com.challenge.santander.model.dto.*;
import com.challenge.santander.service.MeetUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/meetUp")
public class MeetUpControllerImpl implements MeetUpController {

    private MeetUpService meetUpService;

    @Override
    @PostMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO response = meetUpService.addUser(userDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("USERNAME ALREADY EXIST", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllMeetUp() {
        try {
            List<MeetUpDTO> response = meetUpService.getAllMeetUp();
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addMeetUp(@RequestBody MeetUpDTO meetUpDTO) {
        try{
            MeetUpDTO response = meetUpService.addMeetUp(meetUpDTO);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("MEETUP ALREADY EXIST", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value = "/amount-user-subscriptions", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> amountUserSubscriptions(@RequestBody MeetUpDTO meetUpDTO) {
        try{
            MeetUpUsersAmountDTO response = meetUpService.amountUserSubscriptions(meetUpDTO);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("MEETUP DOESNT EXISTS", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping(value = "/")
    public ResponseEntity<?> getMeetUp(@RequestBody MeetUpDTO meetUpDTO) {
        try{
            MeetUpDTO response = meetUpService.getMeetUp(meetUpDTO);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("MEETUP NOT EXIST", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping(value = "/user/{userName}")
    public ResponseEntity<?> getUser(@PathVariable("userName") String userName) {
        try{
            UserDTO response = meetUpService.getUser(userName);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("USER NOT EXIST", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value = "/registration", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addMeetUpUser(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO) {
        try {
            MeetUpUserDTO response = meetUpService.addMeetUpUser(meetUpUserCreateDTO);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("MEETUP OR USER NOT EXISTS", HttpStatus.BAD_REQUEST);
            }
        } catch (MeetUpUserException e) {
            return new ResponseEntity<>("MEETUP USER ALREADY EXISTS", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping(value = "/temperature-meet-up")
    public ResponseEntity<?> getTemperatureMeetUp(@RequestBody MeetUpDTO meetUpDTO) {
        try {
            TemperatureMeetUpDTO response = meetUpService.getTemperatureMeetUp(meetUpDTO);
            if(null != response){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (MeetUpException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping(value = "/temperature-parametry")
    public ResponseEntity<?> getTemperatureParametry() {
        try {
            TemperatureParametryDTO response = meetUpService.getTemperatureParametry();
            if(null != response){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value = "/temperature-meet-up", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addTemperatureMeetUp(@RequestBody TemperatureMeetUpDTO temperatureMeetUpDTO) {
        try {
            TemperatureMeetUpDTO response = meetUpService.addTemperatureMeetUp(temperatureMeetUpDTO);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (TemperatureMeetUpException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value = "/temperature-parametry", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addTemperatureParametry(@RequestBody TemperatureParametryDTO temperatureParametryDTO) {
        try {
            TemperatureParametryDTO response = meetUpService.addTemperatureParametry(temperatureParametryDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value = "/check-in")
    public ResponseEntity<?> checkIn(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO) {
        try{
            MeetUpUserDTO response = meetUpService.checkIn(meetUpUserCreateDTO);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("MEETUP OR USER NOT EXIST", HttpStatus.BAD_REQUEST);
            }
        }catch (MeetUpUserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Autowired
    public void setMeetUpService(MeetUpService meetUpService) {
        this.meetUpService = meetUpService;
    }
}
