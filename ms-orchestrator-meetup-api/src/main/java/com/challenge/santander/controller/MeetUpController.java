package com.challenge.santander.controller;

import com.challenge.santander.model.MeetUpDTO;
import com.challenge.santander.model.MeetUpUserCreateDTO;
import com.challenge.santander.model.UserDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "MeetUp Orchestrator API: ", description = "The Orchestrator MeetUp Api Solution")
public interface MeetUpController {

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<?> getMeetUpWeather(@RequestBody MeetUpDTO meetUpDTO);

    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO);

    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<?> addMeetUp(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO);

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<?> getAllMeetUp();

    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<?> addMeetUpUser(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO);

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<?> checkIn(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO);

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<?> calculateBeerBoxesByMeetUp(@RequestBody MeetUpUserCreateDTO meetUpUserCreateDTO);
}
