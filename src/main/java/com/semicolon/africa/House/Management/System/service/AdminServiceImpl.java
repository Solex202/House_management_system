package com.semicolon.africa.House.Management.System.service;


import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.data.models.User;
import com.semicolon.africa.House.Management.System.data.repository.RoomRepository;
import com.semicolon.africa.House.Management.System.data.repository.BookingRepository;
import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.response.FindBookingResponse;
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
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

//    @Autowired
//    public AdminServiceImpl(UserRepository userRepository, RoomRepository roomRepository){
//        this.userRepository = userRepository;
//        this.roomRepository = roomRepository;
//    }

    @Override
    public String assignRoom(AssignRoomRequest assignRoomRequest) {
        User user = bookingRepository.findByEmail(assignRoomRequest.getNewOccupantEmail()).orElseThrow(()-> new UserNotFoundException("user not found"));

        validateRoomAssignment(assignRoomRequest, user);

        Optional<Room> existingRoom = roomRepository.findRoomByRoomNumber(assignRoomRequest.getRoom().getRoomNumber());
        if (existingRoom.isPresent()){
            existingRoom.get().getRoomMembers().add(user);
//            log.info("Room exists, members are: "+ existingRoom.get().getRoomMembers() );
            roomRepository.save(existingRoom.get());
        }else{
            Room room = new Room();
            room.setRoomNumber(assignRoomRequest.getRoom().getRoomNumber());
            room.setRoomType(assignRoomRequest.getRoom().getRoomType());
            room.getRoomMembers().add(user);
//            log.info("new room members are: "+ room.getRoomMembers() );
            roomRepository.save(room);
        }



        return "Room successfully assigned";
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
    public FindBookingResponse searchBookingByEmail(String email) {
        User user = bookingRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("booking not found"));

        FindBookingResponse response = new FindBookingResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setGender(user.getGender());
        response.setPayment(user.getPayment());
//        response.setId(user.getId());
        return response;
    }

    @Override
    public String evictTenant(String email, Room room) {
        Room newRoom = roomRepository.findRoomByRoomNumber(room.getRoomNumber()).orElseThrow(()-> new RoomNumberDoesNotExistException("Room not found"));

        List<User> tenants = newRoom.getRoomMembers();
        for (User tenant: tenants) {
            if(tenant.getEmail().matches(email)){

            }

        }
        return null;
    }
}
