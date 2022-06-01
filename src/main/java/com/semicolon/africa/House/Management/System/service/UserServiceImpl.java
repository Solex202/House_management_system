package com.semicolon.africa.House.Management.System.service;


import com.semicolon.africa.House.Management.System.data.models.User;
import com.semicolon.africa.House.Management.System.data.repository.UserRepository;
import com.semicolon.africa.House.Management.System.dtos.UserDto;
import com.semicolon.africa.House.Management.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.House.Management.System.exception.PasswordMustMatchException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

private ModelMapper mapper = new ModelMapper();


    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
