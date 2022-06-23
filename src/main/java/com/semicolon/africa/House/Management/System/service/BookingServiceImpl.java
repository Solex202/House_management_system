package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.Payment;
import com.semicolon.africa.House.Management.System.data.models.User;
import com.semicolon.africa.House.Management.System.data.repository.BookingRepository;
import com.semicolon.africa.House.Management.System.dtos.UserDto;
import com.semicolon.africa.House.Management.System.dtos.request.BookRoomRequest;
import com.semicolon.africa.House.Management.System.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public UserDto bookRoom(BookRoomRequest bookRoomRequest) {
        if(emailAlreadyExists(bookRoomRequest.getEmail())) throw new EmailAlreadyExistsException("Email already exist");

        boolean passwordsDoesNotMatch = !bookRoomRequest.getPassword().matches(bookRoomRequest.getConfirmPassword());
        if(passwordsDoesNotMatch) throw new PasswordMustMatchException("Passwords must match");

        if(bookRoomRequest.getPayment() == null) throw new PaymentException("Cannot book room,please make your payment");

        if(bookRoomRequest.getPayment() == Payment.TWO_HUNDRED_THOUSAND){

        }


        User user =  new User();
        user.setFirstName(bookRoomRequest.getFirstName());
        user.setLastName(bookRoomRequest.getLastName());
        user.setId(bookRoomRequest.getId());
        user.setEmail(bookRoomRequest.getEmail());
        user.setPassword(bookRoomRequest.getPassword());
        user.setConfirmPassword(bookRoomRequest.getConfirmPassword());
        user.setGender(bookRoomRequest.getGender());
        user.setPayment(bookRoomRequest.getPayment());
        user.setBookingTime(bookRoomRequest.getBookingTime());
//                .id(bookRoomRequest.getId())
//                .firstName(bookRoomRequest.getFirstName())
//                .lastName(bookRoomRequest.getLastName())
//                .email(bookRoomRequest.getEmail())
//                .password(bookRoomRequest.getPassword())
//                .confirmPassword(bookRoomRequest.getConfirmPassword())
//                .gender(bookRoomRequest.getGender())
//                .payment(bookRoomRequest.getPayment())
//                .bookingTime(bookRoomRequest.getBookingTime())
//                .build();
       LocalDateTime result = user.getBookingTime();
       log.info("first time is ==============>" + result);
        user.setPaymentStatus(true);
        User savedUser =  bookingRepository.save(user);


        log.info("second time is =======================>" + user.getBookingTime());
        return mapper.map(savedUser, UserDto.class);
    }

    private boolean emailAlreadyExists(String email) {
        return bookingRepository.findByEmail(email).isPresent();
    }

    @Override
    public List<User> getAllUsers() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteAll() {
        bookingRepository.deleteAll();
    }

}

//what could be worse than being blind all your life?
//Being born with eyes but no vision!!!
//My email to him before the final interview said,
// ”I'm at the stage in my life where the most important thing
// to me is to get in with a company that I care about,
// and then work my ass off to grow with them and learn as much as possible.
// I'm very set on working for Uber and know that I would be an invaluable asset to the company.
// All My Best, Lizzie Lang”
//not every closed door is locked.Push
