package com.semicolon.africa.House.Management.System.data.repository;

import com.semicolon.africa.House.Management.System.data.models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
}
