package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.response.FindBookingResponse;

public interface AdminService {
    String assignRoom(AssignRoomRequest assignRoomRequest);

//    String evictTenant(String email);

    FindBookingResponse searchBookingByEmail(String email);

    String evictTenant(String email, Room room);
}
