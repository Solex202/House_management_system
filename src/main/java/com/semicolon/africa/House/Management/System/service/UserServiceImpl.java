package com.semicolon.africa.House.Management.System.service;


import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.data.models.RoomType;
import com.semicolon.africa.House.Management.System.data.models.User;
import com.semicolon.africa.House.Management.System.data.repository.RoomRepository;
import com.semicolon.africa.House.Management.System.data.repository.UserRepository;
import com.semicolon.africa.House.Management.System.dtos.UserDto;
import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.House.Management.System.dtos.response.AssignRoomResponse;
import com.semicolon.africa.House.Management.System.exception.EmailAlreadyExistsException;
import com.semicolon.africa.House.Management.System.exception.PasswordMustMatchException;
import com.semicolon.africa.House.Management.System.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

private ModelMapper mapper = new ModelMapper();


    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        if(emailAlreadyExists(createUserRequest.getEmail())){
            throw new EmailAlreadyExistsException("email already exist");
        }
        if(!createUserRequest.getPassword().matches(createUserRequest.getConfirmPassword())){
            throw new PasswordMustMatchException("passwords must match");
        }
        User user = User.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .confirmPassword(createUserRequest.getConfirmPassword())
                .gender(createUserRequest.getGender())
                .build();

         userRepository.save(user);

        return mapper.map(user, UserDto.class);
    }

    private boolean emailAlreadyExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public AssignRoomResponse assignRoom(AssignRoomRequest assignRoomRequest) {

        User user = userRepository.findByEmail(assignRoomRequest.getNewOccupantEmail()).orElseThrow(()-> new UserNotFoundException("user not found"));

        Room room = new Room();
        room.setEmail(user.getEmail());
        room.setRoomNumber(34);
        room.setRoomType(RoomType.FEMALE_WING);

        roomRepository.save(room);

        AssignRoomResponse assignRoomResponse = new AssignRoomResponse();
        assignRoomResponse.setMessage("room has been assigned");

//        if(user.getGender().equals(Gender.MALE) && assignRoomRequest.getRoom().getRoomNumber() > 60){
//
//        }

        return assignRoomResponse;
    }
}
