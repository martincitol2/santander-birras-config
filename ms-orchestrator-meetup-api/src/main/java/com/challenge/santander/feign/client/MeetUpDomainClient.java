package com.challenge.santander.feign.client;

import com.challenge.santander.config.FeignConfig;
import com.challenge.santander.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="meetUpClient", value = "meetUpClient", url = "${feign.path.meetup}", configuration = FeignConfig.class)
public interface MeetUpDomainClient {

    @PostMapping(value = "/meetUp/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO);

    @GetMapping(value = "/meetUp/user/{userName}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userName") String userName);

    @GetMapping(value = "/meetUp/all")
    public ResponseEntity<List<MeetUpDTO>> getAllMeetUp();

    @PostMapping(value = "/meetUp/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MeetUpDTO> addMeetUp(@RequestBody MeetUpDTO meetUpDTO);

    @GetMapping(value = "/meetUp/")
    public ResponseEntity<MeetUpDTO> getMeetUp(@RequestBody MeetUpDTO meetUpDTO);

    @PostMapping(value = "/meetUp/registration", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MeetUpUserDTO> addMeetUpUser(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO);

    @GetMapping(value = "/meetUp/temperature-meet-up")
    public ResponseEntity<TemperatureMeetUpDTO> getTemperatureMeetUp(@RequestBody MeetUpDTO meetUpDTO);

    @GetMapping(value = "/meetUp/temperature-parametry")
    public ResponseEntity<TemperatureParametryDTO> getTemperatureParametry();

    @PostMapping(value = "/meetUp/temperature-meet-up", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TemperatureMeetUpDTO> addTemperatureMeetUp(@RequestBody TemperatureMeetUpDTO temperatureMeetUpDTO);

    @PostMapping(value = "/meetUp/temperature-parametry", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TemperatureParametryDTO> addTemperatureParametry(@RequestBody TemperatureParametryDTO temperatureParametryDTO);

    @PostMapping(value = "/meetUp/check-in")
    public ResponseEntity<MeetUpUserDTO> checkIn(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO);

    @PostMapping(value = "/meetUp/amount-user-subscriptions", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MeetUpUsersAmountDTO> amountUserSubscriptions(@RequestBody MeetUpDTO meetUpDTO);
}
