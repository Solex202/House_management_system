package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.Payment;
import com.semicolon.africa.House.Management.System.dtos.request.BookRoomRequest;
import com.semicolon.africa.House.Management.System.exception.EmailAlreadyExistsException;
import com.semicolon.africa.House.Management.System.exception.PasswordMustMatchException;
import com.semicolon.africa.House.Management.System.exception.PaymentException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;



@DataMongoTest
class UserServiceImplTest {

    @Autowired
    private BookingService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testThatCanUserCanBookRoom(){
        //given
        BookRoomRequest bookRoomRequest = new BookRoomRequest("lota", "solomon", "lota@gmail.com",
                "lota123", "lota123", Gender.MALE, Payment.THREE_HUNDRED_THOUSAND);
        userService.bookRoom(bookRoomRequest);
        assertThat(userService.getAllUsers().size(), is(1));
    }

    @Test
    void testThatUserCannotBookRoomIfPaymentHasNotBeenMade_throwException(){
        //given
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .build();

        assertThrows(PaymentException.class, ()-> userService.bookRoom(bookRoomRequest));
    }

    @Test
    void testThatUserCannotBookRoom_if_passwordsDontMatch_throwException(){
        //given
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota1235")
                .gender(Gender.MALE)
                .build();

        assertThrows(PasswordMustMatchException.class, ()-> userService.bookRoom(bookRoomRequest));
    }

    @Test
    void testThatUserCannotBookRoom_if_emailAlreadyExist_throwException(){
        //given
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .payment(Payment.SIX_HUNDRED_THOUSAND)
                .build();

        userService.bookRoom(bookRoomRequest);

        BookRoomRequest bookRoomRequest2 = BookRoomRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("lota@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .build();

        assertThrows(EmailAlreadyExistsException.class, ()-> userService.bookRoom(bookRoomRequest2));
    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }
}