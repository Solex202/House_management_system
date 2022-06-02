package com.semicolon.africa.House.Management.System.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private int roomNumber;
    private RoomType roomType;
    @Email
    private String email;

    public Room(int roomNumber, RoomType type) {

        this.roomNumber = roomNumber;
        this.roomType = type;

    }





}
