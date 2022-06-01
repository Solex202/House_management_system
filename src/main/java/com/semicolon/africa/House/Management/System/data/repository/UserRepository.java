package com.semicolon.africa.House.Management.System.data.repository;

import com.semicolon.africa.House.Management.System.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
