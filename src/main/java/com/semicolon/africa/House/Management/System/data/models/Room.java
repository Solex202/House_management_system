package com.semicolon.africa.House.Management.System.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private int roomNumber;
    private RoomType roomType;
    private List<User> roomMembers = new ArrayList<>();

    public Room(int roomNumber, RoomType type) {

        this.roomNumber = roomNumber;
        this.roomType = type;
    }





}
