package com.semicolon.africa.House.Management.System.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private String roomNumber;
    private final int TOTAL_ROOMS = 60;
    private RoomType type;




}
