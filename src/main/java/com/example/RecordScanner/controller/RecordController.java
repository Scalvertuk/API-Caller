package com.example.RecordScanner.controller;


import com.example.RecordScanner.Service.recordService;
import com.example.RecordScanner.model.recordResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class RecordController {


    private final recordService recordService;


    @GetMapping(path = "/retrieve-users")
    @ApiOperation(value = "Handles retrieving users for a given URL identifier")
    // Gives values to the responses the API  can give on a success or failure
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "Request was successful and users were retrieved",
                    response = recordResponse.class),
            @ApiResponse(code = 404, message = "No resources found"),
    @ApiResponse(code = 500, message = "There is an internal error.")})
    //Retrieves users based on the RecordService
    public ResponseEntity<recordResponse> retrieveUsers(
            ) throws IOException {
        return ResponseEntity.ok(
                recordResponse.builder()
                        .record(recordService.retrieveUsersInArea("https://bpdts-test-app.herokuapp.com/users",
                                "https://bpdts-test-app.herokuapp.com/city/London/users"))

                        .status(HttpStatus.OK.value())
                        .build()
        );
    }
}
