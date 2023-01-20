package com.semicolon.africa.House.Management.System.controller;

import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.response.ApiResponse;
import com.semicolon.africa.House.Management.System.exception.*;
import com.semicolon.africa.House.Management.System.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/house-app")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/assign-room")
    public ResponseEntity<?> assignRoom(@RequestBody AssignRoomRequest request){
        try{
            ApiResponse response = ApiResponse
                    .builder()
                    .message("" + adminService.assignRoom(request))
                    .isSuccessful(true)
                    .build();

            return  new ResponseEntity<>(response, HttpStatus.OK);

        }catch (RoomNumberDoesNotExistException | MaleWingException | FemaleWingException | PaymentException ex){
            ApiResponse response = ApiResponse
                    .builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();

            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

        @DeleteMapping("/evict-tenant/{email}/{room}")
    public ResponseEntity<?> evict(@PathVariable String email, @PathVariable Room room){
        try{
           return new ResponseEntity<>(adminService.evictTenant(email, room), HttpStatus.OK);

        }catch (UserNotFoundException ex){
            ApiResponse response = ApiResponse
                    .builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();

            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
