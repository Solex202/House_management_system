package com.semicolon.africa.House.Management.System.data.repository;

import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findRoomByRoomNumber(int roomNumber);

//    List<Room> findAllRooms();
}
