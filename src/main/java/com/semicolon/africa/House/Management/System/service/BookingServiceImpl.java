package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.Payment;
import com.semicolon.africa.House.Management.System.data.models.RentDuration;
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

        if(bookRoomRequest.getPayment() == null) throw new PaymentException("Cannot book room,please make your payment");

        User user = userDetails(bookRoomRequest);


        if(bookRoomRequest.getPayment() == Payment.TWO_HUNDRED_THOUSAND){
            user.setRentalDuration(RentDuration.THREE_MONTHS);
            LocalDateTime threeMonths = LocalDateTime.of(bookRoomRequest.getBookingTime().getYear(), bookRoomRequest.getBookingTime().getMonth().getValue() + 3, bookRoomRequest.getBookingTime().getDayOfMonth(), bookRoomRequest.getBookingTime().getHour(), bookRoomRequest.getBookingTime().getMinute(), bookRoomRequest.getBookingTime().getSecond());
            user.setRentExpirationDate(threeMonths);
        }else
            if(bookRoomRequest.getPayment() == Payment.THREE_HUNDRED_THOUSAND){
                user.setRentalDuration(RentDuration.SIX_MONTHS);
                LocalDateTime sixMonths = LocalDateTime.of(bookRoomRequest.getBookingTime().getYear(), bookRoomRequest.getBookingTime().getMonth().getValue() + 6, bookRoomRequest.getBookingTime().getDayOfMonth(), bookRoomRequest.getBookingTime().getHour(), bookRoomRequest.getBookingTime().getMinute(), bookRoomRequest.getBookingTime().getSecond());
                user.setRentExpirationDate(sixMonths);

            }else
                if(bookRoomRequest.getPayment() == Payment.SIX_HUNDRED_THOUSAND){
                user.setRentalDuration(RentDuration.TWELVE_MONTHS);
                    LocalDateTime twelveMonths = LocalDateTime.of(bookRoomRequest.getBookingTime().getYear() + 1, bookRoomRequest.getBookingTime().getMonth().getValue() , bookRoomRequest.getBookingTime().getDayOfMonth() - 1, bookRoomRequest.getBookingTime().getHour(), bookRoomRequest.getBookingTime().getMinute(), bookRoomRequest.getBookingTime().getSecond());
                    user.setRentExpirationDate(twelveMonths);
            }

        user.setPaymentStatus(true);
        User savedUser =  bookingRepository.save(user);

        return mapper.map(savedUser, UserDto.class);
    }

    private User userDetails(BookRoomRequest bookRoomRequest) {
        User user =  new User();
        user.setFirstName(bookRoomRequest.getFirstName());
        user.setLastName(bookRoomRequest.getLastName());
        user.setId(bookRoomRequest.getId());
        user.setEmail(bookRoomRequest.getEmail());
        user.setGender(bookRoomRequest.getGender());
        user.setPayment(bookRoomRequest.getPayment());
        user.setBookingTime(bookRoomRequest.getBookingTime());
        return user;
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
