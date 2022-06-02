package com.semicolon.africa.House.Management.System.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Room {

    @Id
    private int roomNumber;
    private final int TOTAL_ROOMS = 60;
    private RoomType roomType;
    @Email
    private String email;

    public Room(int roomNumber, RoomType type) {
//        if(roomNumber > TOTAL_ROOMS){
//
//        }
        this.roomNumber = roomNumber;
        this.roomType = type;

    }





}
