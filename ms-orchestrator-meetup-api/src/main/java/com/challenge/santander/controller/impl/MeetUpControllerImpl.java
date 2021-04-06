package com.challenge.santander.controller.impl;

import com.challenge.santander.controller.MeetUpController;
import com.challenge.santander.domain.util.Validations;
import com.challenge.santander.model.*;
import com.challenge.santander.service.MeetUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetUp")
public class MeetUpControllerImpl implements MeetUpController {

    private MeetUpService meetUpService;

    @Override
    @PostMapping(value = "/getWeather", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getMeetUpWeather(@RequestBody MeetUpDTO meetUpDTO) {
        try {
            WeatherMeetUpInformationDTO response = meetUpService.getMeetUpWeather(meetUpDTO);
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
    @PostMapping(value="/addUser", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        try{
            Object response = meetUpService.addUser(userDTO);
            if(null != response){
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value="/addMeetUp", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addMeetUp(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO){
        try{
            if(!Validations.isAdmin(meetUpUserCreateDTO.getUserName())){
                return new ResponseEntity<>("THIS USER DOESN'T HAVE PERMISSION",HttpStatus.FORBIDDEN);
            }
            Object response = meetUpService.addMeetUp(meetUpUserCreateDTO.getMeetUp());
            if(null != response){
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value = "/meetup/registration", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addMeetUpUser(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO) {
        try {
            Object response = meetUpService.addMeetUpUser(meetUpUserCreateDTO);
            if (response != null) {
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value = "/check-in", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> checkIn(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO) {
        try {
            Object response = meetUpService.checkIn(meetUpUserCreateDTO);
            if (response != null) {
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping(value="/allMeetUp")
    public ResponseEntity<?> getAllMeetUp(){
        try{
            List<MeetUpDTO> response = meetUpService.getAllMeetUp();
            if(null != response){
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(value = "/calculate/boxes", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> calculateBeerBoxesByMeetUp(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO) {
        if(!Validations.isAdmin(meetUpUserCreateDTO.getUserName())){
            return new ResponseEntity<>("THIS USER DOESN'T HAVE PERMISSION",HttpStatus.FORBIDDEN);
        }
        try {
            return new ResponseEntity<>(meetUpService.calculateBeerBoxesByMeetUp(meetUpUserCreateDTO.getMeetUp()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    public void setMeetUpService(MeetUpService meetUpService) {
        this.meetUpService = meetUpService;
    }
}
