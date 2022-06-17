package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.User;
import com.semicolon.africa.House.Management.System.data.repository.UserRepository;
import com.semicolon.africa.House.Management.System.dtos.UserDto;
import com.semicolon.africa.House.Management.System.dtos.request.BookRoomRequest;
import com.semicolon.africa.House.Management.System.exception.*;
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
    public UserDto bookRoom(BookRoomRequest bookRoomRequest) {
        if(emailAlreadyExists(bookRoomRequest.getEmail())) throw new EmailAlreadyExistsException("Email already exist");

        boolean passwordsDoesNotMatch = !bookRoomRequest.getPassword().matches(bookRoomRequest.getConfirmPassword());
        if(passwordsDoesNotMatch) throw new PasswordMustMatchException("Passwords must match");

        if(bookRoomRequest.getPayment() == null) throw new PaymentException("Cannot book room,please make your payment");

        User user = User.builder()
                .firstName(bookRoomRequest.getFirstName())
                .lastName(bookRoomRequest.getLastName())
                .email(bookRoomRequest.getEmail())
                .password(bookRoomRequest.getPassword())
                .confirmPassword(bookRoomRequest.getConfirmPassword())
                .gender(bookRoomRequest.getGender())
                .payment(bookRoomRequest.getPayment())
                .build();

        user.setPaymentStatus(true);
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

}
