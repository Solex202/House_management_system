package com.semicolon.africa.House.Management.System.service;

import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.RoomType;
import com.semicolon.africa.House.Management.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.House.Management.System.exception.PasswordMustMatchException;
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

    @AfterEach
    void tearDown() {
    }
}