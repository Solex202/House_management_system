package com.semicolon.africa.House.Management.System.service;


import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.data.models.User;
import com.semicolon.africa.House.Management.System.data.repository.RoomRepository;
import com.semicolon.africa.House.Management.System.data.repository.UserRepository;
import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.request.BookRoomRequest;
import com.semicolon.africa.House.Management.System.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

//    @Autowired
//    public AdminServiceImpl(UserRepository userRepository, RoomRepository roomRepository){
//        this.userRepository = userRepository;
//        this.roomRepository = roomRepository;
//    }

    @Override
    public String assignRoom(AssignRoomRequest assignRoomRequest) {
        User user = userRepository.findByEmail(assignRoomRequest.getNewOccupantEmail()).orElseThrow(()-> new UserNotFoundException("user not found"));

        validateRoomAssignment(assignRoomRequest, user);

        Optional<Room> existingRoom = roomRepository.findRoomByRoomNumber(assignRoomRequest.getRoom().getRoomNumber());
        if (existingRoom.isPresent()){
            existingRoom.get().getRoomMembers().add(user);
            log.info("Room exists, members are: "+ existingRoom.get().getRoomMembers() );
            roomRepository.save(existingRoom.get());
        }else{
            Room room = new Room();
            room.setRoomNumber(assignRoomRequest.getRoom().getRoomNumber());
            room.setRoomType(assignRoomRequest.getRoom().getRoomType());
            room.getRoomMembers().add(user);
//            log.info("new room members are: "+ room.getRoomMembers() );
            roomRepository.save(room);
        }



        return "room successfully assigned";
    }

    private void validateRoomAssignment(AssignRoomRequest assignRoomRequest, User user) {
        boolean isNotValidRoomNumber = assignRoomRequest.getRoom().getRoomNumber() > 60 || assignRoomRequest.getRoom().getRoomNumber() < 1;
        if(isNotValidRoomNumber) throw new RoomNumberDoesNotExistException("room " + assignRoomRequest.getRoom().getRoomNumber() + " not available");

        boolean isNotFemaleRoom = user.getGender().equals(Gender.FEMALE) && (assignRoomRequest.getRoom().getRoomNumber() > 30 || assignRoomRequest.getRoom().getRoomNumber() < 1);
        if(isNotFemaleRoom) throw new MaleWingException("cannot add " + user.getEmail() + " to male wing");

        boolean isNotMaleRoom = user.getGender().equals(Gender.MALE) && (assignRoomRequest.getRoom().getRoomNumber() < 30 || assignRoomRequest.getRoom().getRoomNumber() > 60);
        if(isNotMaleRoom) throw new FemaleWingException("cannot add " + user.getEmail() + " to female wing");
    }

    @Override
    public void evictTenant(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("user not found"));
        userRepository.delete(user);
    }

    @Override
    public String findBookingByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("booking not found"));
        return null;
    }
}
