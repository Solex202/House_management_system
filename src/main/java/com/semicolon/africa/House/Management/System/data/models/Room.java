package com.semicolon.africa.House.Management.System.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Builder
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
public class Room {

    @Id
    private int roomNumber;
    private final int TOTAL_ROOMS = 60;
    private RoomType roomType;

    public Room(int roomNumber, RoomType type) {
        this.roomNumber = roomNumber;
        this.roomType = type;

    }





}
