package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.response.AssignRoomResponse;

public interface AdminService {
    AssignRoomResponse assignRoom(AssignRoomRequest assignRoomRequest);
}
