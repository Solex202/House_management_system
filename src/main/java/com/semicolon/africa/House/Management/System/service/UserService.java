package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.User;
import com.semicolon.africa.House.Management.System.dtos.UserDto;
import com.semicolon.africa.House.Management.System.dtos.request.BookRoomRequest;

import java.util.List;

public interface UserService {
    UserDto bookRoom(BookRoomRequest bookRoomRequest);

    List<User> getAllUsers();

    void deleteAll();

}
