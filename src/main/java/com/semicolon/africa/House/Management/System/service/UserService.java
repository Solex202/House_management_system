package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.User;
import com.semicolon.africa.House.Management.System.dtos.UserDto;
import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.House.Management.System.dtos.response.AssignRoomResponse;

import java.util.List;

public interface UserService {
    UserDto createUser(CreateUserRequest createUserRequest);

    List<User> getAllUsers();

    void deleteAll();

    AssignRoomResponse assignRoom(AssignRoomRequest assignRoomRequest);
}
