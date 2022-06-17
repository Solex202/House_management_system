package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.response.FindBookingResponse;

public interface AdminService {
    String assignRoom(AssignRoomRequest assignRoomRequest);

    void evictTenant(String email);

    FindBookingResponse searchBookingByEmail(String email);
}
