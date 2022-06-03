package com.semicolon.africa.House.Management.System.controller;

import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.request.BookRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.response.ApiResponse;
import com.semicolon.africa.House.Management.System.exception.*;
import com.semicolon.africa.House.Management.System.service.AdminService;
import com.semicolon.africa.House.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/houseApp")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @PostMapping("users/bookRoom")
    public ResponseEntity<?> bookRoom(@RequestBody BookRoomRequest request){
        try{
            ApiResponse response = ApiResponse
                    .builder()
                    .message("" + userService.bookRoom(request))
                    .isSuccessful(true)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (EmailAlreadyExistsException | PasswordMustMatchException ex) {

            ApiResponse response = ApiResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("assignRoom")
    public ResponseEntity<?> assignRoom(@RequestBody AssignRoomRequest request){
        try{
            ApiResponse response = ApiResponse
                    .builder()
                    .message("" + adminService.assignRoom(request))
                    .isSuccessful(true)
                    .build();

            return  new ResponseEntity<>(response, HttpStatus.OK);

        }catch (RoomNumberDoesNotExistException | MaleWingException | FemaleWingException ex){
            ApiResponse response = ApiResponse
                    .builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();

            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
