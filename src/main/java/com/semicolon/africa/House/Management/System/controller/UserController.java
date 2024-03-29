package com.semicolon.africa.House.Management.System.controller;

import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.request.BookRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.response.ApiResponse;
import com.semicolon.africa.House.Management.System.exception.*;
import com.semicolon.africa.House.Management.System.service.AdminService;
import com.semicolon.africa.House.Management.System.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/house-app")
public class UserController {

    @Autowired
    private BookingService bookingService;


    @PostMapping("/user/book-room")
    public ResponseEntity<?> bookRoom(@RequestBody BookRoomRequest request){
        try{
            ApiResponse response = ApiResponse
                    .builder()
                    .message("" + bookingService.bookRoom(request))
                    .isSuccessful(true)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (EmailAlreadyExistsException | PasswordMustMatchException | PaymentException ex) {

            ApiResponse response = ApiResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

//https://github.com/koushikkothagal/spring-boot-security


}
