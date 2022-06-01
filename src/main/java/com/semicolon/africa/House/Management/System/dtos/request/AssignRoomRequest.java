package com.semicolon.africa.House.Management.System.dtos.request;

import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.data.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoomRequest {

    private Room room;
    private String user;
}
