package com.semicolon.africa.House.Management.System.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private Gender gender;


}
