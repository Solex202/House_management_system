package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.data.models.RoomType;
import com.semicolon.africa.House.Management.System.data.models.User;
import com.semicolon.africa.House.Management.System.dtos.request.AssignRoomRequest;
import com.semicolon.africa.House.Management.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.House.Management.System.dtos.response.AssignRoomResponse;
import com.semicolon.africa.House.Management.System.exception.EmailAlreadyExistsException;
import com.semicolon.africa.House.Management.System.exception.PasswordMustMatchException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;



@DataMongoTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testThatCanCreateUser(){
        //given
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .build();

        userService.createUser(createUserRequest);

        assertThat(userService.getAllUsers().size(), is(1));
    }

    @Test
    void testThatUserCannotCreateAccount_if_passwordsDontMatch(){
        //given
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota1235")
                .gender(Gender.MALE)
                .build();

        assertThrows(PasswordMustMatchException.class, ()-> userService.createUser(createUserRequest));
    }

    @Test
    void testThatUserCannotCreateAccount_if_emailAlreadyExist(){
        //given
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .build();

        userService.createUser(createUserRequest);

        CreateUserRequest createUserRequest2 = CreateUserRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("lota@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .build();

        assertThrows(EmailAlreadyExistsException.class, ()-> userService.createUser(createUserRequest2));
    }

    @Test
    void testThatAdminUserCamAssignRoom(){
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName("lota")
                .lastName("solomon")
                .email("lota@gmail.com")
                .password("lota123")
                .confirmPassword("lota123")
                .gender(Gender.MALE)
                .build();

        userService.createUser(createUserRequest);

        CreateUserRequest createUserRequest2 = CreateUserRequest.builder()
                .firstName("gina")
                .lastName("dimma")
                .email("gina@gmail.com")
                .password("ginagina")
                .confirmPassword("ginagina")
                .gender(Gender.FEMALE)
                .build();

        userService.createUser(createUserRequest2);

        List<User> users = userService.getAllUsers();
        assertThat(users.size(), equalTo(2));

        Room room = new Room();
        room.setRoomNumber(3);
        room.setRoomType(RoomType.FEMALE_WING);
        AssignRoomRequest assignRoomRequest = new AssignRoomRequest(room, createUserRequest2.getEmail());


        AssignRoomResponse assignRoomResponse = userService.assignRoom(assignRoomRequest);
        assertThat(assignRoomResponse.getMessage(), is("room has been assigned"));

    }


    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }
};