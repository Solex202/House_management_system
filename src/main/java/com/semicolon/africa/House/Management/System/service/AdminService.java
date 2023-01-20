package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.dtos.request.AdminLoginRequest;
import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.response.FindBookingResponse;

import java.util.List;

public interface AdminService {
    String assignRoom(AssignRoomRequest assignRoomRequest);


    FindBookingResponse searchBookingByEmail(String email);

    String evictTenant(String newOccupantEmail, Room room);

    List<Room> viewAllRooms();


    FindBookingResponse searchBookingByBookingId(String id);

    void login(AdminLoginRequest loginRequest);
}
