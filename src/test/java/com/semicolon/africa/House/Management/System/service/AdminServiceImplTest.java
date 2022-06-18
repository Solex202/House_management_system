package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.*;
import com.semicolon.africa.House.Management.System.data.repository.RoomRepository;
import com.semicolon.africa.House.Management.System.data.repository.BookingRepository;
import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.request.BookRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.response.FindBookingResponse;
import com.semicolon.africa.House.Management.System.exception.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
class AdminServiceImplTest {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private AdminService adminService;

    @BeforeEach
    void setUp() {
//        adminService = new AdminServiceImpl(userRepository, roomRepository);
    }

    @Test
    void testThatAdminUserCamAssignRoom(){
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.FEMALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest);

        BookRoomRequest bookRoomRequest2 = BookRoomRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("gina@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest2);

        List<User> users = bookingService.getAllUsers();
        assertThat(users.size(), equalTo(2));

        Room room = new Room();
        room.setRoomNumber(3);
        room.setRoomType(RoomType.FEMALE_ROOM);
        room.setRoomMembers(users);
        AssignRoomRequest assignRoomRequest = new AssignRoomRequest(room, bookRoomRequest2.getEmail());
//        AssignRoomRequest assignRoomRequest2 = new AssignRoomRequest(room, bookRoomRequest.getEmail());

        String assignRoomResponse = adminService.assignRoom(assignRoomRequest);
//        String assignRoomResponse2 = adminService.assignRoom(assignRoomRequest2);
        assertThat(assignRoomResponse, is("Room successfully assigned"));
        assertThat(room.getRoomMembers().size(), is(2));
    }


    @Test
    void testThatAdminUserCannotAssignFemaleToMAleRoom_throwException(){
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest);

        BookRoomRequest bookRoomRequest2 = BookRoomRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("gina@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .payment(Payment.SIX_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest2);

        List<User> users = bookingService.getAllUsers();
        assertThat(users.size(), equalTo(2));

        Room room = new Room();
        room.setRoomNumber(37);
        room.setRoomType(RoomType.FEMALE_ROOM);
        AssignRoomRequest assignRoomRequest = new AssignRoomRequest(room, bookRoomRequest2.getEmail());

        assertThrows(MaleWingException.class, ()-> adminService.assignRoom(assignRoomRequest));
    }

    @Test
    void testThatAdminUserCannotAssignMaleToFemaleRoom_throwException(){
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .payment(Payment.SIX_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest);

        BookRoomRequest bookRoomRequest2 = BookRoomRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("gina@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .payment(Payment.SIX_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest2);

        List<User> users = bookingService.getAllUsers();
        assertThat(users.size(), equalTo(2));

        Room room = new Room();
        room.setRoomNumber(7);
        room.setRoomType(RoomType.MALE_ROOM);
        AssignRoomRequest assignRoomRequest = new AssignRoomRequest(room, bookRoomRequest.getEmail());

        assertThrows(FemaleWingException.class, ()-> adminService.assignRoom(assignRoomRequest));
    }

    @Test
    void testThatAdminUserCannotAssignRoomWhenRoomNumberIsExceeded_throwException(){
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest);

        BookRoomRequest bookRoomRequest2 = BookRoomRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("gina@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest2);

        List<User> users = bookingService.getAllUsers();
        assertThat(users.size(), equalTo(2));

        Room room = new Room();
        room.setRoomNumber(62);
        room.setRoomType(RoomType.FEMALE_ROOM);
        AssignRoomRequest assignRoomRequest = new AssignRoomRequest(room, bookRoomRequest2.getEmail());

        assertThrows(RoomNumberDoesNotExistException.class,()-> adminService.assignRoom(assignRoomRequest));
    }

    @Test
    void testThatAdminUserCannotAssignRoomWhenRoomNumberIsBelow_throwException(){
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest);

        BookRoomRequest bookRoomRequest2 = BookRoomRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("gina@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest2);

        List<User> users = bookingService.getAllUsers();
        assertThat(users.size(), equalTo(2));

        Room room = new Room();
        room.setRoomNumber(-2);
        room.setRoomType(RoomType.FEMALE_ROOM);
        AssignRoomRequest assignRoomRequest = new AssignRoomRequest(room, bookRoomRequest2.getEmail());

        assertThrows(RoomNumberDoesNotExistException.class,()-> adminService.assignRoom(assignRoomRequest));

    }

    @Test
    void testThatAdminUserCannotAssignRoomWhenUserIsNotInDatabase_throwException(){
        Room room = new Room();
        room.setRoomNumber(7);
        room.setRoomType(RoomType.FEMALE_ROOM);
        AssignRoomRequest assignRoomRequest = new AssignRoomRequest(room, "chioma@gmail.com");

        assertThrows(UserNotFoundException.class,()-> adminService.assignRoom(assignRoomRequest));

    }

    @Test
    void testThatAdminUserCanEvictOccupantFromRoom(){
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest);

        BookRoomRequest bookRoomRequest2 = BookRoomRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("gina@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest2);

        List<User> users = bookingService.getAllUsers();
        assertThat(users.size(), equalTo(2));

        Room room = new Room();
        room.setRoomNumber(2);
        room.setRoomType(RoomType.FEMALE_ROOM);
        AssignRoomRequest assignRoomRequest = new AssignRoomRequest(room, bookRoomRequest2.getEmail());

        String assignRoomResponse = adminService.assignRoom(assignRoomRequest);
        assertThat(assignRoomResponse, is("Room successfully assigned"));

        String response = adminService.evictTenant(bookRoomRequest2.getEmail(), room);

        List<User> users2 = bookingService.getAllUsers();
        assertThat(users2.size(), equalTo(1));
        assertThat(response, is("Tenant deleted"));
    }

    @Test
    void testThatAdminUserCanFindBooking(){
        BookRoomRequest bookRoomRequest = BookRoomRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest);

        BookRoomRequest bookRoomRequest2 = BookRoomRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("gina@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .payment(Payment.THREE_HUNDRED_THOUSAND)
                .build();

        bookingService.bookRoom(bookRoomRequest2);

        List<User> users = bookingService.getAllUsers();
        assertThat(users.size(), equalTo(2));

       FindBookingResponse response = adminService.searchBookingByEmail(bookRoomRequest2.getEmail());
       assertThat(response.getFirstName(),is("gina"));
       assertThat(response.getLastName(),is("dimma"));
       assertThat(response.getEmail(),is("gina@gmail.com"));
       assertThat(response.getGender(),is(Gender.FEMALE));
       assertThat(response.getPayment(),is(Payment.THREE_HUNDRED_THOUSAND));
//       assertThat(response.getId(),is(bookRoomRequest2.getId()));
    }


    @AfterEach
    void tearDown() {
        bookingService.deleteAll();
    }
}